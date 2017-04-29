package zombies.zombiescene.armas;

import sound.SoundBuilderZombie;
import zombies.scene.components.Bala;
import zombies.scene.components.Personaje;
import zombies.scene.scenes.ZombiesScene;
import ar.edu.unq.games.vainillautils.Vector2D;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.appearances.Animation;
import com.uqbar.vainilla.sound.Sound;

public abstract class Arma {

	Personaje personaje;
	private int cantidadDeBalas;
	private int cantidadBalasMax;
	private int cantidadDeBalasNoCargadas;
	private Sound sonidoSinBalas;
	private double tiempoDeRecarga = 0;
	private double tiempoTranscurridoDesdeUltimoDisparo = 0;
	private double punteria; 
	
	public Arma(Personaje personaje,int cantidadBalasMax,int cantidadDeBalasNoCargadas,double punteria){
		this.setPunteria(punteria);
		this.personaje = personaje;
		this.cantidadBalasMax = cantidadBalasMax;
		this.cantidadDeBalas = cantidadBalasMax;
		this.cantidadDeBalasNoCargadas = cantidadDeBalasNoCargadas;
		this.sonidoSinBalas = new SoundBuilderZombie(this.getClass().getClassLoader().getResourceAsStream("sonido de arma sin balas.wav")).getSound();

	}
	
	public void disparar(double x, double y, Vector2D direccionPersonaje,double roatation,ZombiesScene scene){
			double random = Math.random() - 0.5;
			Vector2D direccion = new Vector2D(direccionPersonaje.getX(),direccionPersonaje.getY());
			direccion.rotate(random*this.getPunteria()*(scene.getPersonaje().getPunteria()));
			
			this.getSound().play();
			this.descontarBala();
			scene.addBala(new Bala(x,y, direccion,this.getDanio(),roatation));
		

	}

	public void restarTiempoDeRecarga(double tiempo){
		this.tiempoDeRecarga = this.tiempoDeRecarga - tiempo;
	}

	public abstract double getTiempoDeRecarga();
	
	public abstract Sound getSound();

	public abstract Sound getRecargaSound();
	
	public abstract double getDanio();
	
	public abstract double tiempoMuertoDeDisparo();

	public abstract boolean checkDisparo(DeltaState deltaState);
	
	public void recargar(){
		this.getRecargaSound().play(5);
		this.tiempoDeRecarga = this.getTiempoDeRecarga();
		if(this.cantidadDeBalasNoCargadas == -1){ //Balas infinitas :P
			this.cantidadDeBalas = this.cantidadBalasMax;
		}
		else{
			
			if(this.cantidadBalasMax - this.cantidadDeBalas < this.cantidadDeBalasNoCargadas){
				this.cantidadDeBalasNoCargadas = this.cantidadDeBalasNoCargadas - (this.cantidadBalasMax - this.cantidadDeBalas);
				this.cantidadDeBalas = this.cantidadBalasMax ;
				
			}
			else{	
				this.cantidadDeBalas = this.cantidadDeBalas + this.cantidadDeBalasNoCargadas;
				this.cantidadDeBalasNoCargadas = 0;
			}
		}
	}
	
	public void descontarBala(){
		this.cantidadDeBalas = this.cantidadDeBalas - 1;
	}

	public int getCantidadDeBalas() {
		return cantidadDeBalas;
	}

	public int getCantidadBalasMax() {
		return cantidadBalasMax;
	}

	public int getCantidadDeBalasNoCargadas() {
		return cantidadDeBalasNoCargadas;
	}

	public abstract Animation getAnimationScala();

	public boolean estaRecargando() {
		return this.tiempoDeRecarga  > 0;
	}
	
	public boolean puedeDisparar(){
		return !this.estaRecargando() && this.getCantidadDeBalas() > 0 && this.tiempoTranscurridoDesdeUltimoDisparo >= this.tiempoMuertoDeDisparo();
	}

	public double getTiempoTranscurridoDesdeUltimoDisparo() {
		return tiempoTranscurridoDesdeUltimoDisparo;
	}

	public void setTiempoTranscurridoDesdeUltimoDisparo(double tiempoTranscurridoDesdeUltimoDisparo) {
		this.tiempoTranscurridoDesdeUltimoDisparo = tiempoTranscurridoDesdeUltimoDisparo;
	}
	
	public void sumarTiempoTranscurridoDesdeUltimoDisparo(double tiempo) {
		this.tiempoTranscurridoDesdeUltimoDisparo = tiempoTranscurridoDesdeUltimoDisparo +tiempo;
	}

	public boolean disparaSinBalas(DeltaState delta) {
		// TODO Auto-generated method stub
		return !this.estaRecargando() && this.getCantidadDeBalas() == 0 && this.tiempoTranscurridoDesdeUltimoDisparo >= this.tiempoMuertoDeDisparo() && this.checkDisparo(delta);
	}

	
	
	public void dispararSinBalas() {
		this.tiempoTranscurridoDesdeUltimoDisparo = 0;
		this.sonidoSinBalas.play();		
	}

	public double getPunteria() {
		return punteria;
	}

	public void setPunteria(double punteria) {
		this.punteria = punteria;
	}
}
