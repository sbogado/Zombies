package zombie.zombiescene.scenecargandostates;

import zombies.scene.ZombiesScene;

public class PersonajeCargando extends ZombieSceneCargando {

	public PersonajeCargando(ZombiesScene scene) {
		super(scene);

	}

	@Override
	public void cargarScenario() {
		this.getScene().cargarPersonaje();
	}

	@Override
	public ZombieSceneCargando nextState() {
		return new OleadasCargando(this.getScene());
	}


}
