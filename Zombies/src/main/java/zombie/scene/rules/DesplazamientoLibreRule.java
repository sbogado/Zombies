package zombie.scene.rules;

import ar.edu.unq.games.vainillautils.Vector2D;
import zombies.scene.scenes.ZombiesScene;

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
