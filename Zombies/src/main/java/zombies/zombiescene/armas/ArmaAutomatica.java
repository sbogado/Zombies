package zombies.zombiescene.armas;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.events.constants.MouseButton;

import zombies.scene.components.Personaje;

public abstract class ArmaAutomatica extends Arma{

	public ArmaAutomatica(Personaje personaje,int cantidadBalasMax,int cantidadDeBalasNoCargadas,double punteria) {
		super(personaje, cantidadBalasMax, cantidadDeBalasNoCargadas,punteria);
	}

	public  boolean checkDisparo(DeltaState deltaState){
		return deltaState.isMouseButtonBeingHold(MouseButton.LEFT);
	}
	
	public boolean disparaSinBalas(DeltaState delta) {
		// TODO Auto-generated method stub
		return !this.estaRecargando() && this.getCantidadDeBalas() == 0 && this.getTiempoTranscurridoDesdeUltimoDisparo() >= 1 && this.checkDisparo(delta);
	}

}
