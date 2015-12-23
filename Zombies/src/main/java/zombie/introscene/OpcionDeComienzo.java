package zombie.introscene;

import java.awt.Dimension;

import zombie.Zombies;
import ar.edu.unq.games.vainillautils.AnimationRotateMoved;
import ar.edu.unq.games.vainillautils.SpriteMoved;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.events.constants.Key;

public class OpcionDeComienzo extends GameComponent<IntroScene> {

	IntroScene scene;
	static SpriteMoved[] animaciones = {SpriteMoved.fromImage("presione enter2.png"),SpriteMoved.fromImage("presione enter3.png"),SpriteMoved.fromImage("presione enter4.png"),SpriteMoved.fromImage("presione enter5.png"),SpriteMoved.fromImage("presione enter6.png")};
	
	public OpcionDeComienzo(Dimension dimension,IntroScene scene){
		super(new AnimationRotateMoved(0.1,animaciones),dimension.getHeight()/2+100,dimension.getHeight()/2+200);
		this.setZ(5);
		this.scene = scene;
	}
	
	@Override
	public void update(DeltaState deltaState) {
		if(deltaState.isKeyPressed(Key.ENTER)){
			this.destroy();
			this.scene.goToCargandoNextScene();
		}
		super.update(deltaState);
		
	}
	
}
