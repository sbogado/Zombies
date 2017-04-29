package zombie.zombiescene.scenecargandostates;

import zombies.scene.scenes.ZombiesScene;

public class OleadasCargando extends ZombieSceneCargando {

	public OleadasCargando(ZombiesScene scene) {
		super(scene);

	}

	@Override
	public void cargarScenario() {
		this.getScene().cargarOleadas();
	}

	@Override
	public ZombieSceneCargando nextState() {
		return new EscenarioCargando(this.getScene());
	}


}
