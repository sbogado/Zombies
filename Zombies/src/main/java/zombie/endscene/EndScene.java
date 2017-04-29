package zombie.endscene;

import java.awt.Dimension;

import com.uqbar.vainilla.GameScene;
import com.uqbar.vainilla.appearances.Sprite;

import zombies.scene.scenes.ZombiesScene;

public class EndScene extends GameScene {

	
	
	public EndScene(ZombiesScene scene,Dimension dimension,boolean ganaste) {
		super();
		this.addComponent(new WinOrLoseComponent(scene,dimension.getWidth()/3 , dimension.getHeight()/2+300));
		this.addComponent( new FondoDeFin(this.getImagenDeFondo(ganaste)));
	}

	
	public Sprite getImagenDeFondo(boolean ganaste){
		if(ganaste){
			double random = Math.random();
			if(random < 0.3){
				return Sprite.fromImage("win1.png");
			}
			else{
				if(random < 0.6){
					return Sprite.fromImage("win2.png");
				}
				else{
					return Sprite.fromImage("win3.png");
				}
			}
		}
		else{
			return Sprite.fromImage("lose1.png");
		}
	}
}
