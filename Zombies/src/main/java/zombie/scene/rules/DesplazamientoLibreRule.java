package zombie.scene.rules;

import zombies.scene.ZombiesScene;
import ar.edu.unq.games.vainillautils.Vector2D;

import com.uqbar.vainilla.GameComponent;

public class DesplazamientoLibreRule {

	public boolean mustApply(GameComponent<?> component, Vector2D nuevaPosicion,
			ZombiesScene scene) {
		return true;
	}

	public void apply(GameComponent<?> component, Vector2D nuevaPosicion, ZombiesScene scene) {
		component.setX(nuevaPosicion.getX());
		component.setY(nuevaPosicion.getY());
	}

}
