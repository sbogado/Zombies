package zombie.zombiescene.scenecargandostates;

import zombies.scene.scenes.ZombiesScene;

public class EscenarioCargando extends ZombieSceneCargando {

	public EscenarioCargando(ZombiesScene scene) {
		super(scene);

	}

	@Override
	public void cargarScenario() {
		this.getScene().cargarEscenario();
	}

	@Override
	public ZombieSceneCargando nextState() {
		return new MiraDePersonajeCargando(this.getScene());
	}


}
