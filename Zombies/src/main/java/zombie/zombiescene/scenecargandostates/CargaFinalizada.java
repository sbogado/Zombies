package zombie.zombiescene.scenecargandostates;

import zombies.scene.ZombiesScene;

public class CargaFinalizada extends ZombieSceneCargando {

	public CargaFinalizada(ZombiesScene scene) {
		super(scene);

	}

	@Override
	public void cargarScenario() {
		this.getScene().cargaFinalizada();
		
	}

	@Override
	public ZombieSceneCargando nextState() {
		return null;
	}

}
