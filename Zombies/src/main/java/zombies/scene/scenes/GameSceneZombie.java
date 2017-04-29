package zombies.scene.scenes;

import java.awt.Graphics2D;
import java.util.ArrayList;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.GameScene;

public class GameSceneZombie extends GameScene{

	public void takeStepPaused(Graphics2D graphics) {
		long now = System.nanoTime();
		double delta = this.getLastUpdateTime() > 0 ? (now - this.getLastUpdateTime()) / 1000000000L : 0;
		if(delta > 1) {
			delta = 0;
		}
		this.setLastUpdateTime(now);

		DeltaState state = this.getEventQueue().takeState(delta);
		
		for(GameComponent<?> component : new ArrayList<GameComponent<?>>(this.getComponents())) {
			if(component.isDestroyPending()) {
				this.removeComponent(component);
			}
			else {
				component.render(graphics);
			}
		}
		
	}

	public void afterUpdateComponents(DeltaState state){
		
	}
	
}
