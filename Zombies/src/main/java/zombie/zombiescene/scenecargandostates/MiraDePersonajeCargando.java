package zombie.zombiescene.scenecargandostates;

import zombie.introscene.CargandoJuego;
import zombies.scene.ZombiesScene;

public class MiraDePersonajeCargando extends ZombieSceneCargando {

	public MiraDePersonajeCargando(ZombiesScene scene) {
		super(scene);

	}

	@Override
	public void cargarScenario() {
		this.getScene().cargarMiraDePersonaje();
	}

	@Override
	public ZombieSceneCargando nextState() {
		return new CargaFinalizada(this.getScene());
	}


}
