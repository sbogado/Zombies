package zombies.scene.components;

import ar.edu.unq.games.vainillautils.Vector2D;
import zombies.scene.scenes.ZombiesScene;


public interface ZombiesRule {

	boolean mustApply(Bala bala, Vector2D nuevaPosicion, ZombiesScene scene);
	void apply(Bala bala, Vector2D nuevaPosicion, ZombiesScene scene);
}
