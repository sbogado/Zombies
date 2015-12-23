package zombies.scene;

import zombie.Zombies;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;

public class DetectorDeFinDeOleada extends GameComponent<ZombiesScene> {

	private ZombiesScene scene;

	public DetectorDeFinDeOleada(ZombiesScene scene){
		super();
		this.scene = scene;
	}
	
	@Override
	public void update(DeltaState deltaState){
		if(!this.scene.checkEnd()){	
			if(this.scene.getMonstruos().size() <= 0){
				this.destroy();
				this.scene.siguienteOleada();
			}
		}
		else{
			this.endGame();

		}
	}
	
	public void endGame(){
		((Zombies)this.getScene().getGame()).buildEndScene(true);
	}
}
