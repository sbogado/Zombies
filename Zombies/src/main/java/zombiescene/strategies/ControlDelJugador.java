package zombiescene.strategies;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.events.constants.Key;
import com.uqbar.vainilla.events.constants.MouseButton;

import zombies.scene.components.Personaje;
import zombies.scene.scenes.ZombiesScene;

public class ControlDelJugador  {

	private Key leftKey = Key.A;
	private Key rigthKey = Key.D;
	private Key upKey = Key.W;
	private Key downKey = Key.S;
	private MouseButton shotKey = MouseButton.LEFT;

	public void update(Personaje individuo, ZombiesScene scene,DeltaState deltaState) {
		if(scene.isPaused()){
			if (deltaState.isKeyPressed(Key.ENTER)) {		
				scene.reanudar();
			}
		}
		else{
			individuo.rotate(individuo.getPersonajeState().getMouseRotation(individuo, deltaState));
			
			if (deltaState.isKeyPressed(Key.R) && !individuo.getArma().estaRecargando()) {		
				 individuo.getArma().recargar();
			}
			
			if (deltaState.isKeyPressed(Key.ENTER)) {		
				 scene.pausar();
			}
				
			if (deltaState.isKeyBeingHold(rigthKey)) {
				individuo.derecha(deltaState.getDelta());
			}
			if (deltaState.isKeyBeingHold(leftKey)) {
				individuo.izquierda(deltaState.getDelta());
			}
			if (deltaState.isKeyBeingHold(upKey)) {
				individuo.arriba(deltaState.getDelta());
			}
			if (deltaState.isKeyBeingHold(downKey)) {
				individuo.abajo(deltaState.getDelta());
			}
			
			if (deltaState.isKeyPressed(Key.E)) {
				individuo.setNextWeapon();
			}
			
			if (deltaState.isKeyPressed(Key.Q)) {
				individuo.setPreviousWeapon();
			}
			
			if (individuo.getArma().checkDisparo(deltaState) && individuo.getArma().getCantidadDeBalas() <= 0){
				individuo.recargar();
			}
			
			if(individuo.getArma().puedeDisparar()){
				if (individuo.getArma().checkDisparo(deltaState)) {
						individuo.disparar(deltaState);
						individuo.getArma().setTiempoTranscurridoDesdeUltimoDisparo(0);
					}
			}
			else{
				individuo.getArma().sumarTiempoTranscurridoDesdeUltimoDisparo(deltaState.getDelta());
				if (individuo.getArma().disparaSinBalas(deltaState)){
					individuo.getArma().dispararSinBalas();
				}
				}
			
			if(!individuo.getArma().checkDisparo(deltaState) || individuo.getArma().getCantidadDeBalas() <= 0){
				individuo.permanecerQuieto(deltaState);
			}
		}
	}	

	public Key getUpKey() {
		return upKey;
	}

	public void setUpKey(Key upKey) {
		this.upKey = upKey;
	}

	public Key getDownKey() {
		return downKey;
	}

	public void setDownKey(Key downKey) {
		this.downKey = downKey;
	}

	public Key getLeftKey() {
		return leftKey;
	}

	public void setLeftKey(Key leftKey) {
		this.leftKey = leftKey;
	}

	public Key getRigthKey() {
		return rigthKey;
	}

	public void setRigthKey(Key rigthKey) {
		this.rigthKey = rigthKey;
	}

	public MouseButton getShotKey() {
		return shotKey;
	}

	public void setShotKey(MouseButton shotKey) {
		this.shotKey = shotKey;
	}



}
