package sound;

import static javax.sound.sampled.AudioSystem.getAudioInputStream;

import java.io.BufferedInputStream;
import java.io.InputStream;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;

import com.uqbar.vainilla.sound.Sound;
import com.uqbar.vainilla.sound.SoundBuilder;




public class SoundBuilderZombie extends SoundBuilder {

	private byte[] normalizedInput;
	private AudioFormat inputFormat;

	public SoundBuilderZombie(InputStream inputStream){
		try {
			AudioInputStream input = getAudioInputStream(new BufferedInputStream(inputStream));
			AudioFormat inputFormat = toNormalizedReadableFormat(input.getFormat());
			AudioInputStream normalizedInput = getAudioInputStream(inputFormat, input);

			this.normalizedInput = readInput(normalizedInput);
			this.inputFormat = inputFormat;
				
			normalizedInput.close();
			
		}
		catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public Sound getSound(){
		Sound sound = inputFormat.getChannels() == 1
				? new MonoSound(this.normalizedInput, this.inputFormat)
				: new StereoSound(this.normalizedInput, this.inputFormat);
		return sound;
	}
}
