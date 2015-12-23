package sound;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioFormat.Encoding;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.SourceDataLine;

import com.uqbar.vainilla.sound.SoundPlay;

public class SoundPlayerZombie {

	public static final Encoding ENCODING = AudioFormat.Encoding.PCM_SIGNED;
	public static final float SAMPLE_RATE = 44100;
	public static final int SAMPLE_SIZE_IN_BYTES = 2;
	public static final int CHANNELS = 2;

	private static final int NUM_SAMPLES = 44100 * 2;

	private SourceDataLine line;
	private final List<SoundPlay> buffers;
	public static Thread thread;

	// ****************************************************************
	// ** STATICS
	// ****************************************************************

	protected static short toShortSample(float value) {
		return (short) ((value > 1 ? 1 : value < -1 ? -1 : value) * Short.MAX_VALUE);
	}

	// ****************************************************************
	// ** CONSTRUCTORS
	// ****************************************************************

	public SoundPlayerZombie() {
		this.buffers = new ArrayList<SoundPlay>();
		AudioFormat format = new AudioFormat(44100.0f, 16, 2, true, false);

		try {
			this.line = AudioSystem.getSourceDataLine(format);
			this.line.open(format, 4410);
			this.line.start();

			thread = new Thread(new Runnable() {
				@Override
				public void run() {
					SoundPlayerZombie self = SoundPlayerZombie.this;
					while(true) {
						int bytesToWrite = self.line.available();

						if(bytesToWrite > 0) {
							byte[] bytes = self.readBytes(bytesToWrite);

							int writtenBytes = 0;
							while(writtenBytes != bytesToWrite) {
								writtenBytes += self.line.write(bytes, writtenBytes, bytesToWrite - writtenBytes);
							}
						}

						try {
							Thread.sleep(1);
						}
						catch(InterruptedException e) {
							throw new RuntimeException(e);
						}
					}
				}
			});
			thread.setDaemon(true);
			thread.start();
		}
		catch(Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static void pausar(){
		try {
			SoundPlayerZombie.thread.wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	public static void reanudar(){
		thread.notify();
	}
	
	// ****************************************************************
	// ** QUERIES
	// ****************************************************************

	public AudioFormat normalizeFormat(AudioFormat format) {
		return new AudioFormat(
			SoundPlayerZombie.ENCODING,
			SoundPlayerZombie.SAMPLE_RATE,
			SoundPlayerZombie.SAMPLE_SIZE_IN_BYTES * 8,
			format.getChannels(),
			format.getChannels() * SoundPlayerZombie.SAMPLE_SIZE_IN_BYTES,
			SoundPlayerZombie.SAMPLE_RATE,
			false //
		);
	}

	// ****************************************************************
	// ** OPERATIONS
	// ****************************************************************

	protected synchronized void enqueueSound(SoundZombie sound, float volume) {
		try {
			this.buffers.add(new SoundPlay(sound, volume));
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	private byte[] readBytes(int bytesToWrite) {
		int samplesToWrite = bytesToWrite / 2;
		float[] buffer = new float[NUM_SAMPLES];
		byte[] answer = new byte[2 * NUM_SAMPLES];

		synchronized(this) {
			Iterator<SoundPlay> iterator = this.buffers.iterator();

			while(iterator.hasNext()) {
				if(iterator.next().writeSamples(samplesToWrite, buffer)) {
					iterator.remove();
				}
			}
		}

		int bufferSize = this.buffers.size();
		if(bufferSize > 0) {
			for(int i = 0; i < samplesToWrite; i++) {

				short sample = toShortSample(buffer[i]);

				answer[i * 2] = (byte) (sample | 0xff);
				answer[i * 2 + 1] = (byte) (sample >> 8);
			}
		}

		return answer;
	}
}