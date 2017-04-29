package zombies.scene.components;


import sound.SoundBuilderZombie;
import zombies.scene.scenes.ZombiesScene;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.appearances.Appearance;
import com.uqbar.vainilla.appearances.Sprite;
import com.uqbar.vainilla.sound.Sound;

public abstract class Barricada  extends Pared{
	
	private Sound barricadaReparada;
	private Sound barricadaRota;	
	
	public Barricada(int width,int height,double x, double y,ZombiesScene scene){
		super(width,height, x, y, scene);
		this.barricadaReparada = new SoundBuilderZombie(this.getClass().getClassLoader().getResourceAsStream("barricadaReparada.wav")).getSound();
		this.barricadaRota = new SoundBuilderZombie(this.getClass().getClassLoader().getResourceAsStream("golpeando puerta3.wav")).getSound();
		this.setFullLife();
	}
	
	public void setFullLife(){
		this.setVida(500);
	}

	public abstract void setVida(int i);

	public abstract Sprite getBarricadaImpactada();

	public abstract void setBarricadaImpactada(Sprite barricadaImpactada);

	public abstract Appearance getBarricadaRota();

	public abstract Appearance getBarricadaNueva();

	public abstract Appearance getBarricadaMedia() ;

	public abstract void setImpactada(boolean b);

	public abstract boolean isImpactada();
	
	public abstract Appearance getBarricadaMediaImpactada();

	public abstract Appearance getBarricadaNuevaImpactada();
	
	
	public void recibirImpacto(int danio){
		this.setVida( this.getVida() - danio);
		if(this.getVida() > 50){
			this.setAppearance(this.getBarricadaNuevaImpactada());
		}
		else{
			this.setAppearance(this.getBarricadaMediaImpactada());
		}
		if(this.getVida() <= 0){
			this.barricadaRota.play(9);
		}
		
		this.setImpactada(true);
	}

	public abstract int getVida();

	public void fix(){
		this.barricadaReparada.play(10);
		this.setFullLife();
		this.setAppearance(this.getBarricadaNueva());
	}
	
	public void applyY() {
		((Individuo)this).setYBack();
	}

	public void applyX() {
		((Individuo)this).setXBack();
	}
	
	public boolean isNotBroken(){
		return this.getVida() > 0;
	}
	
	public void update(DeltaState deltaState) {	
		
		if(!this.isImpactada()){
			if(this.getVida()>50){
				this.setAppearance(this.getBarricadaNueva());
			}
			if(this.getVida()<=50 && this.getVida() > 0){
				this.setAppearance(this.getBarricadaMedia());
			}
			if(this.getVida()<=0){
				this.setAppearance(this.getBarricadaRota());
			}
		}
		else{this.setImpactada(false);}
	}
}
