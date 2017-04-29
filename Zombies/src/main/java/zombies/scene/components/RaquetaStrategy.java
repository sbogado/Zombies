package zombies.scene.components;

import com.uqbar.vainilla.DeltaState;

import zombies.scene.scenes.ZombiesScene;

public interface RaquetaStrategy {

	public void update(Personaje personaje, ZombiesScene scene, DeltaState deltaState);
}
