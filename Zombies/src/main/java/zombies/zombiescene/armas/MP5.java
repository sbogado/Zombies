package zombies.zombiescene.armas;


import sound.SoundBuilderZombie;
import zombies.scene.components.Personaje;
import ar.edu.unq.games.vainillautils.SpriteMoved;

import com.uqbar.vainilla.appearances.Animation;
import com.uqbar.vainilla.sound.Sound;

public class MP5 extends ArmaAutomatica {
	private Sound sound4;
	private Sound soundRecarga;
	private Animation animationScala;
	
	public MP5(Personaje personaje) {
		super(personaje, 30, -1,0.5);
		this.sound4= new SoundBuilderZombie(this.getClass().getClassLoader().getResourceAsStream("sonido de mp5 poderosa.wav")).getSound();
		this.soundRecarga= new SoundBuilderZombie(this.getClass().getClassLoader().getResourceAsStream("recargaMP5.wav")).getSound();
		SpriteMoved[] sprites = {SpriteMoved.fromImage("mp510 a escala.png")};
		this.animationScala = new Animation(.5,sprites);
		
	}
	
	@Override
	public double getTiempoDeRecarga() {
		// TODO Auto-generated method stub
		return 2;
	}

	@Override
	public Sound getSound() {
		return sound4;
	}

	@Override
	public Sound getRecargaSound() {
		return soundRecarga;
	}

	@Override
	public double getDanio() {
		// TODO Auto-generated method stub
		return 20;
	}

	@Override
	public double tiempoMuertoDeDisparo() {
		// TODO Auto-generated method stub
		return 0.1;
	}

	@Override
	public Animation getAnimationScala() {
		return this.animationScala;
	}

}
