package sound;

import javax.sound.sampled.AudioFormat;

import zombie.Zombies;

import com.uqbar.vainilla.sound.Sound;
import com.uqbar.vainilla.sound.SoundPlayer;

public abstract class SoundZombie extends Sound {
	
	protected SoundZombie() {
	}

	public void play(float volume) {
		Zombies.player.enqueueSound(this, volume);
	}
	
	public SoundZombie(byte[] input, AudioFormat inputFormat) {
		this.setFormat(SoundPlayer.INSTANCE.normalizeFormat(inputFormat));
		this.setSamples(this.resample(input, inputFormat));

	}
}
