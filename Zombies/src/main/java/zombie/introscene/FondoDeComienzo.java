package zombie.introscene;

import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Sprite;

public class FondoDeComienzo extends GameComponent<IntroScene>{
	
	public FondoDeComienzo(){
		super(Sprite.fromImage("fondo inicio.png"),0,0);
	}
}
