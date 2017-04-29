package zombies.scene.components;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;

import zombies.scene.scenes.ZombiesScene;

public class DetectorDeFinDeOleada extends GameComponent<ZombiesScene> {

	private ZombiesScene scene;

	public DetectorDeFinDeOleada(ZombiesScene scene) {
		super();
		this.scene = scene;
	}

	@Override
	public void update(DeltaState deltaState) {
		if (this.scene.getMonstruos().size() <= 0) {
			this.destroy();
			this.scene.siguienteOleada();
		}
	}

}
