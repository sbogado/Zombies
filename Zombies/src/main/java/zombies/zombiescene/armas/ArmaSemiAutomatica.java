package zombies.zombiescene.armas;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.events.constants.MouseButton;

import zombies.scene.components.Personaje;

public abstract class ArmaSemiAutomatica extends Arma{

	public ArmaSemiAutomatica(Personaje personaje,int cantidadBalasMax,int cantidadDeBalasNoCargadas,double punteria) {
		super(personaje, cantidadBalasMax, cantidadDeBalasNoCargadas,punteria);
	}

	public  boolean checkDisparo(DeltaState deltaState){
		return deltaState.isMouseButtonPressed(MouseButton.LEFT);
	}
	
}
