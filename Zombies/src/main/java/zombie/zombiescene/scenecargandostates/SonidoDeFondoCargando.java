package zombie.zombiescene.scenecargandostates;

import zombies.scene.ZombiesScene;

public class SonidoDeFondoCargando extends ZombieSceneCargando {

	public SonidoDeFondoCargando(ZombiesScene scene) {
		super(scene);

	}

	@Override
	public void cargarScenario() {
		this.getScene().cargarSonidoDeFondo();
		
	}

	@Override
	public ZombieSceneCargando nextState() {
		return new PantallaPausadaCargando(this.getScene());
	}

}
