package zombies.zombiescene.armas;

import sound.SoundBuilderZombie;
import zombies.scene.Personaje;
import ar.edu.unq.games.vainillautils.SpriteMoved;

import com.uqbar.vainilla.appearances.Animation;
import com.uqbar.vainilla.sound.Sound;

public class Beretta extends ArmaSemiAutomatica{

	private Sound sound1;
	private Sound sound2;
	private Sound sound3;
	private Sound sound4;
	private Sound soundRecarga;
	private Animation animationScala;
	
	public Beretta(Personaje personaje) {
		super(personaje, 15, -1,0.1);
		this.sound1 = new SoundBuilderZombie(this.getClass().getClassLoader().getResourceAsStream("disparoBeretta1.wav")).getSound();
		this.sound2= new SoundBuilderZombie(this.getClass().getClassLoader().getResourceAsStream("disparoBeretta2.wav")).getSound();
		this.sound3= new SoundBuilderZombie(this.getClass().getClassLoader().getResourceAsStream("disparoBeretta3.wav")).getSound();
		this.sound4= new SoundBuilderZombie(this.getClass().getClassLoader().getResourceAsStream("disparoBeretta4.wav")).getSound();
		this.soundRecarga= new SoundBuilderZombie(this.getClass().getClassLoader().getResourceAsStream("sonido de recarga de beretta.wav")).getSound();
		SpriteMoved[] sprites = {SpriteMoved.fromImage("beretta a escala.png")};
		this.animationScala = new Animation(.5,sprites);
	}

	@Override
	public double getDanio() {
		// TODO Auto-generated method stub
		return 20;
	}

	@Override
	public double tiempoMuertoDeDisparo() {
		// TODO Auto-generated method stub
		return 0.3;
	}
	
	public  Sound getSound(){
		return this.sound4;
	}
	
//	public  Sound getSound(){
//		double random = Math.random();
//		
//		if(random < 0.3){
//			return this.sound1;
//		}
//		if(random >= 0.3 && random < 0.6){
//			return this.sound2;
//		}
//		else{
//			return this.sound3;
//		}
//	}

	@Override
	public Animation getAnimationScala() {
		return this.animationScala;
	}

	@Override
	public Sound getRecargaSound() {
		return this.soundRecarga;
	}

	@Override
	public double getTiempoDeRecarga() {
		return 1;
	}


	
	
}
