package zombies.scene.zombiesappearences;

import ar.edu.unq.games.vainillautils.AnimationRotate;

import com.uqbar.vainilla.appearances.Sprite;

public class BalaAppearences {

	
	public static final BalaAppearences INSTANCE = new BalaAppearences();
	private AnimationRotate estallandoAnimation;
	private AnimationRotate estallandoParedAnimation;
	
	public BalaAppearences(){
		Sprite[] sprites1 = {Sprite.fromImage("impactoDeBalaSangre1.png"),Sprite.fromImage("impactoDeBalaSangre2.png"),Sprite.fromImage("impactoDeBalaSangre3.png")};
		this.setEstallandoAnimation(new AnimationRotate(.01,sprites1));
		Sprite[] sprites2 = {Sprite.fromImage("impactoDeBalaPared1.png")};
		this.setEstallandoParedAnimation(new AnimationRotate(.01,sprites2));
	}

	public AnimationRotate getEstallandoAnimation() {
		return estallandoAnimation;
	}

	public void setEstallandoAnimation(AnimationRotate estallandoAnimation) {
		this.estallandoAnimation = estallandoAnimation;
	}

	public AnimationRotate getEstallandoParedAnimation() {
		return estallandoParedAnimation;
	}

	public void setEstallandoParedAnimation(AnimationRotate estallandoParedAnimation) {
		this.estallandoParedAnimation = estallandoParedAnimation;
	}
	
	
}
