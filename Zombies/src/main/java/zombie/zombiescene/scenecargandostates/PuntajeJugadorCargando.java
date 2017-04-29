package zombie.zombiescene.scenecargandostates;

import zombies.scene.scenes.ZombiesScene;

public class PuntajeJugadorCargando extends ZombieSceneCargando {

	public PuntajeJugadorCargando(ZombiesScene scene) {
		super(scene);

	}

	@Override
	public void cargarScenario() {
		this.getScene().cargarPuntajeJugador();
	}

	@Override
	public ZombieSceneCargando nextState() {
		return new PersonajeCargando(this.getScene());
	}

}
