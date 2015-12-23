package zombies.zombiescene.personajestates;

import zombies.scene.Personaje;
import ar.edu.unq.games.vainillautils.AnimationRotateMoved;

import com.uqbar.vainilla.DeltaState;

public class PersonajeStateQuieto extends PersonajeState {

	private Personaje personaje;
	
	public PersonajeStateQuieto(Personaje personaje){
		super(personaje);
	}
	
	public PersonajeStateQuieto(Personaje personaje,DeltaState delta){
		super(personaje,delta);
	}
	
	@Override
	public void accionar(DeltaState delta) {
	}

	@Override
	public void modoDisparo(DeltaState delta) {
		personaje.setPersonajeState(new PersonajeStateDisparando(personaje,delta));
		
	}

	@Override
	public void modoQuieto(DeltaState delta) {
	}


	
	@Override
	public AnimationRotateMoved getImage() {
		return this.personaje.getPersonajeQuietoAnimation();
	}

	@Override
	public void setPersonaje(Personaje personaje) {
		this.personaje = personaje;
	}
	
}
