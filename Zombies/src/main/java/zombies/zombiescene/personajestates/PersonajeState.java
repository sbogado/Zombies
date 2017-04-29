package zombies.zombiescene.personajestates;

import ar.edu.unq.games.vainillautils.AnimationRotateMoved;
import zombies.scene.components.Personaje;

import com.uqbar.vainilla.DeltaState;

public abstract class PersonajeState {

	public PersonajeState(Personaje personaje){
		this.setPersonaje(personaje);
		AnimationRotateMoved animation = this.getImage();
		personaje.setAppearance(animation);
	}
	
	public PersonajeState(Personaje personaje,DeltaState delta){
		this.setPersonaje(personaje);
		AnimationRotateMoved animation = this.getImage();
		personaje.setAppearanceRotated(animation,getMouseRotation(personaje,delta));
	}
	
	public abstract void setPersonaje(Personaje personaje);

	public abstract void accionar(DeltaState delta);
	
	public abstract void modoDisparo(DeltaState delta);
	
	public abstract void modoQuieto(DeltaState delta);
	
	public abstract AnimationRotateMoved getImage();
	
	public double getMouseRotation(Personaje personaje,DeltaState delta){
		double yMouse = delta.getCurrentMousePosition().getY() -personaje.getDimensionY()/2 ;
		double xMouse = delta.getCurrentMousePosition().getX() -personaje.getDimensionX()/2 ;
		if(xMouse < 0 ){
			return 2*Math.PI -(Math.acos(-yMouse/(Math.sqrt(Math.pow(xMouse,2)+Math.pow(yMouse,2))))); 
		}
		else{
			return (Math.acos(-yMouse/(Math.sqrt(Math.pow(xMouse,2)+Math.pow(yMouse,2)))));
		}	
	}
	
}
