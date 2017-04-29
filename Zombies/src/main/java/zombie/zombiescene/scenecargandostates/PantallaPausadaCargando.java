package zombie.zombiescene.scenecargandostates;

import zombies.scene.scenes.ZombiesScene;

public class PantallaPausadaCargando extends ZombieSceneCargando {

	public PantallaPausadaCargando(ZombiesScene scene) {
		super(scene);

	}

	@Override
	public void cargarScenario() {
		this.getScene().cargarPantallaPausada();
		
	}

	@Override
	public ZombieSceneCargando nextState() {
		return new MapaCargando(this.getScene());
	}

}
