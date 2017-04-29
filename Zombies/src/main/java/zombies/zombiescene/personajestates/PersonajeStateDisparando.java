package zombies.zombiescene.personajestates;

import zombie.Zombies;
import zombies.scene.components.Bala;
import zombies.scene.components.Personaje;
import ar.edu.unq.games.vainillautils.AnimationRotateMoved;
import ar.edu.unq.games.vainillautils.Vector2D;

import com.uqbar.vainilla.DeltaState;

public class PersonajeStateDisparando extends PersonajeState{

	private Personaje personaje;
	
	public PersonajeStateDisparando(Personaje personaje,DeltaState delta) {
		super(personaje,delta);
	}

	
	public void accionar(DeltaState delta) {
		double xCentroDeImagen = personaje.getX();
		double yCentroDeImagen = personaje.getY();
		Vector2D puntoInicio = new Vector2D(10,-10);
		puntoInicio.rotate(personaje.getRotation());

		
		this.personaje.getArma().disparar(xCentroDeImagen+ puntoInicio.getX(),yCentroDeImagen+puntoInicio.getY(), personaje.getDireccion(),personaje.getRotation(),personaje.getScene());


	}

	public void modoDisparo(DeltaState delta) {
		
	}

	public void modoQuieto(DeltaState delta) {
		personaje.setPersonajeState(new PersonajeStateQuieto(personaje,delta));	
	}

	@Override
	public AnimationRotateMoved getImage() {
		return this.personaje.getPersonajeDisparandoAnimation();
	}

	@Override
	public void setPersonaje(Personaje personaje) {
		this.personaje = personaje;
	}
}
