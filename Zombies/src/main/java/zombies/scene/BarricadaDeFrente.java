package zombies.scene;

import java.awt.Graphics2D;

import com.uqbar.vainilla.appearances.Appearance;
import com.uqbar.vainilla.appearances.Sprite;

public class BarricadaDeFrente extends Barricada{
	
	private int vida;
	private Sprite barricadaNueva;
	private Sprite barricadaMedia;
	private Sprite barricadaRota;
	private Sprite barricadaImpactada;
	private boolean impactada= false;
	private Appearance barricadaNuevaImpactada;
	private Appearance barricadaMediaImpactada;
	
	public BarricadaDeFrente( double x, double y,
			ZombiesScene scene) {
		super(100,20, x, y, scene);
		this.barricadaNueva= Sprite.fromImage("barricadaNueva.png");
		this.barricadaMedia=Sprite.fromImage("barricadaMedia.png");
		this.barricadaRota=Sprite.fromImage("barricadaRota.png");
		this.barricadaMediaImpactada=Sprite.fromImage("barricadaMediaImpactada.png");
		this.barricadaNuevaImpactada=Sprite.fromImage("barricadaNuevaImpactada.png");
	}
	
	public void render(Graphics2D graphics){
		this.setX(this.getX() - 10);
		this.setY(this.getY() - 10);
		super.render(graphics);
		this.setX(this.getX() + 10);
		this.setY(this.getY() + 10);
	}
	
	public Sprite getBarricadaImpactada() {
		return barricadaImpactada;
	}

	public void setBarricadaImpactada(Sprite barricadaImpactada) {
		this.barricadaImpactada = barricadaImpactada;
	}


	public int getVida() {
		return vida;
	}


	public void setVida(int vida) {
		this.vida = vida;
	}


	public Sprite getBarricadaNueva() {
		return barricadaNueva;
	}


	public void setBarricadaNueva(Sprite barricadaNueva) {
		this.barricadaNueva = barricadaNueva;
	}


	public Sprite getBarricadaMedia() {
		return barricadaMedia;
	}


	public void setBarricadaMedia(Sprite barricadaMedia) {
		this.barricadaMedia = barricadaMedia;
	}


	public Sprite getBarricadaRota() {
		return barricadaRota;
	}


	public void setBarricadaRota(Sprite barricadaRota) {
		this.barricadaRota = barricadaRota;
	}


	public boolean isImpactada() {
		return impactada;
	}


	public void setImpactada(boolean impactada) {
		this.impactada = impactada;
	}

	public Appearance getBarricadaNuevaImpactada() {
		return barricadaNuevaImpactada;
	}


	public void setBarricadaNuevaImpactada(Appearance barricadaNuevaImpactada) {
		this.barricadaNuevaImpactada = barricadaNuevaImpactada;
	}


	public Appearance getBarricadaMediaImpactada() {
		return barricadaMediaImpactada;
	}


	public void setBarricadaMediaImpactada(Appearance barricadaMediaImpactada) {
		this.barricadaMediaImpactada = barricadaMediaImpactada;
	}

}
