package zombie.scene.rules;

import zombies.scene.ZombiesScene;
import ar.edu.unq.games.vainillautils.Vector2D;

import com.uqbar.vainilla.GameComponent;

public class OutOfGameRule {

	
	public boolean mustApplyHorizontal(GameComponent<?> component, Vector2D nuevaPosicion,
			ZombiesScene scene) {
		return nuevaPosicion.getX() > component.getGame().getDisplayWidth() ||
				nuevaPosicion.getX() < 0;
	}
	public boolean mustApplyVertical(GameComponent<?> component, Vector2D nuevaPosicion,
			ZombiesScene scene) {
		return 	nuevaPosicion.getY() > component.getGame().getDisplayHeight() ||
				nuevaPosicion.getY() > 0;
	}
	
	public void applyHorizontal(GameComponent<?> component, Vector2D nuevaPosicion, ZombiesScene scene) {
		component.setY(component.getY());
	}
	public void applyVertical(GameComponent<?> component, Vector2D nuevaPosicion, ZombiesScene scene) {
		component.setX(component.getX());
	}
	
}
