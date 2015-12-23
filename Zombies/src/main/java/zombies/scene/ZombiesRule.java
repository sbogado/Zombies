package zombies.scene;

import ar.edu.unq.games.vainillautils.Vector2D;


public interface ZombiesRule {

	boolean mustApply(Bala bala, Vector2D nuevaPosicion, ZombiesScene scene);
	void apply(Bala bala, Vector2D nuevaPosicion, ZombiesScene scene);
}
