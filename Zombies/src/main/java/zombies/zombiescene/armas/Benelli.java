package zombies.zombiescene.armas;

import sound.SoundBuilderZombie;

import ar.edu.unq.games.vainillautils.SpriteMoved;
import ar.edu.unq.games.vainillautils.Vector2D;

import com.uqbar.vainilla.appearances.Animation;
import com.uqbar.vainilla.sound.Sound;

import zombies.scene.Bala;
import zombies.scene.Personaje;
import zombies.scene.ZombiesScene;

public class Benelli extends ArmaSemiAutomatica {

	private Sound sound1;
	private Sound soundRecarga;
	private Animation animationScala;

	public Benelli(Personaje personaje) {
		super(personaje, 6, -1,0.5);

		this.sound1 = new SoundBuilderZombie(this.getClass().getClassLoader().getResourceAsStream("benelli disparo.wav")).getSound();
		this.soundRecarga= new SoundBuilderZombie(this.getClass().getClassLoader().getResourceAsStream("benelli recarga.wav")).getSound();
		SpriteMoved[] sprites = {SpriteMoved.fromImage("Benelli a escala.png")};
		this.animationScala = new Animation(.5,sprites);
	}

	public void disparar(double x, double y, Vector2D direccion,double roatation,ZombiesScene scene){
			this.getSound().play();
			this.descontarBala();
			
			double random = Math.random() - 0.5;
			Vector2D vector1 = new Vector2D(direccion.getX(),direccion.getY());
			vector1.rotate(random*this.getPunteria()*(scene.getPersonaje().getPunteria()));
			
			random = Math.random() - 0.5;
			Vector2D vector2 = new Vector2D(direccion.getX(),direccion.getY());
			vector2.rotate(random*this.getPunteria()*(scene.getPersonaje().getPunteria()));
		
			random = Math.random() - 0.5;
			Vector2D vector3 = new Vector2D(direccion.getX(),direccion.getY());
			vector3.rotate(random*this.getPunteria()*(scene.getPersonaje().getPunteria()));
			
			random = Math.random() - 0.5;
			Vector2D vector4 = new Vector2D(direccion.getX(),direccion.getY());
			vector4.rotate(random*this.getPunteria()*(scene.getPersonaje().getPunteria()));
			
			random = Math.random() - 0.5;
			Vector2D vector5 = new Vector2D(direccion.getX(),direccion.getY());
			vector5.rotate(random*this.getPunteria()*(scene.getPersonaje().getPunteria()));
			
			random = Math.random() - 0.5;
			Vector2D vector6 = new Vector2D(direccion.getX(),direccion.getY());
			vector6.rotate(random*this.getPunteria()*(scene.getPersonaje().getPunteria()));
			
			scene.addBala(new Bala(x,y, vector1,this.getDanio(),roatation-0.05));
			scene.addBala(new Bala(x,y, vector2,this.getDanio(),roatation-0.03));
			scene.addBala(new Bala(x,y, vector3,this.getDanio(),roatation-0.01));
			scene.addBala(new Bala(x,y, vector4,this.getDanio(),roatation+0.01));
			scene.addBala(new Bala(x,y, vector5,this.getDanio(),roatation+0.03));
			scene.addBala(new Bala(x,y, vector6,this.getDanio(),roatation+0.05));
		
	}
	
	@Override
	public double getTiempoDeRecarga() {
		// TODO Auto-generated method stub
		return 1.5;
	}

	@Override
	public Sound getSound() {
		return this.sound1;
	}

	@Override
	public Sound getRecargaSound() {
		return this.soundRecarga;
	}

	@Override
	public double getDanio() {
		return 20;
	}

	@Override
	public double tiempoMuertoDeDisparo() {
		// TODO Auto-generated method stub
		return 0.9;
	}

	@Override
	public Animation getAnimationScala() {
		// TODO Auto-generated method stub
		return this.animationScala;
	}

}
