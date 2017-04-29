package zombies.scene.components;

import ar.edu.unq.games.vainillautils.AnimationRotate;
import zombies.scene.scenes.ZombiesScene;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;

public class Mira extends GameComponent<ZombiesScene> {

	public Mira(AnimationRotate animationMira){
		super(animationMira, 500,350);
	}
	
	@Override
	public void update(DeltaState deltaState) {
		this.setX( deltaState.getCurrentMousePosition().getX()-this.getAppearance().getWidth()/3);
		this.setY( deltaState.getCurrentMousePosition().getY()-this.getAppearance().getHeight()/3);
		super.update(deltaState);
	}
	
//	public void render(Graphics2D graphics){
//		double x = this.getX();
//		double y = this.getY();
//		
//		this.setX(this.getX() + this.getScene().getDesplazamientoDePantallaX());
//		this.setY(this.getY() + this.getScene().getDesplazamientoDePantallaY());
//		
//		super.render(graphics);
//		
//		this.setX(x);
//		this.setY(y);
//
//	}
}
