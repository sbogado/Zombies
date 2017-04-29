package zombie.endscene;


import java.awt.Color;
import java.awt.Font;

import zombie.Zombies;
import zombies.scene.scenes.ZombiesScene;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Label;
import com.uqbar.vainilla.events.constants.Key;

public class WinOrLoseComponent extends GameComponent<EndScene> {

	public WinOrLoseComponent(ZombiesScene scene, double x, double y) {
		super(new Label(new Font("verdana",  Font.BOLD, 15), Color.red, "Puntaje: "+Double.toString(scene.getPuntaje().getValue()),"Presione Enter para un juego nuevo!"), x, y);
		this.setZ(2);
	}
	
	@Override
	public void update(DeltaState deltaState) {
		if(deltaState.isKeyPressed(Key.ENTER)) {
			((Zombies) this.getGame()).closeWindow();
		}
		super.update(deltaState);
	}

}
