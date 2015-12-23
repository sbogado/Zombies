package ar.edu.unq.games.vainillautils;


public class AnimationLowCost extends AnimationRotateMoved {

	private AnimationRotateMovedLowCost animations;
	
	public AnimationLowCost(AnimationRotateMovedLowCost animations) {
		super(.4, animations.getSpritesIniciales());
		this.animations = animations;
	}
	
	public void rotate(double radians){
		this.setSprites( animations.imageRotated(radians));
	}

}
