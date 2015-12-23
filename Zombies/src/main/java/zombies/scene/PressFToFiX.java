package zombies.scene;

import java.awt.Dimension;

import ar.edu.unq.games.vainillautils.SpriteMoved;

import com.uqbar.vainilla.GameComponent;

public class PressFToFiX extends GameComponent<ZombiesScene>{

	public PressFToFiX(Dimension dimension){
		super( dimension.getWidth()/3, dimension.getHeight()/2);
		SpriteMoved sprite = SpriteMoved.fromImage("press f to fix.png");
		
		this.setAppearance(sprite);
	}
	
}
