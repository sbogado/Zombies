package zombies.scene;

import com.uqbar.vainilla.DeltaState;

public interface RaquetaStrategy {

	public void update(Personaje personaje, ZombiesScene scene, DeltaState deltaState);
}
