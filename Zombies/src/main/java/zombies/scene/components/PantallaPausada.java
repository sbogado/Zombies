package zombies.scene.components;

import java.awt.Graphics2D;

import ar.edu.unq.games.vainillautils.SpriteMoved;
import zombies.scene.scenes.ZombiesScene;

import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Sprite;

public class PantallaPausada extends GameComponent<ZombiesScene>{
	Sprite opacadorDePantalla = Sprite.fromImage("fondo pausado.png");
	SpriteMoved cartelDePausa = SpriteMoved.fromImage("Paused.png");
	private ZombiesScene scene;
	
	public PantallaPausada(ZombiesScene scene){
		this.scene = scene;
		this.setZ(50);
	}
	
	public void render(Graphics2D graphics){
		cartelDePausa.renderAt((int)this.scene.getDimension().getWidth()/3+100,(int)this.scene.getDimension().getHeight()/2, graphics);
		
	}
}
