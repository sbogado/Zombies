package zombie.zombiescene.scenecargandostates;

import zombies.scene.scenes.ZombiesScene;

public class MapaCargando extends ZombieSceneCargando {

	public MapaCargando(ZombiesScene scene) {
		super(scene);

	}

	@Override
	public void cargarScenario() {
		this.getScene().cargarMapa();
		
	}

	@Override
	public ZombieSceneCargando nextState() {
		return new PuntajeJugadorCargando(this.getScene());
	}

}
