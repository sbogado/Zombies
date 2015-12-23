package zombie.zombiescene.scenecargandostates;

import zombies.scene.ZombiesScene;

public abstract class ZombieSceneCargando {

	private ZombiesScene scene;

	public ZombieSceneCargando(ZombiesScene scene){
		this.scene = scene;
	}
	
	public abstract void cargarScenario();
	
	public  void next(){
		this.scene.setState(this.nextState());
	}
	
	public abstract ZombieSceneCargando nextState();

	public ZombiesScene getScene() {
		return scene;
	}
	
	
}

