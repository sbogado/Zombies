package zombie.introscene;

import java.awt.Dimension;

import zombie.Zombies;
import zombies.scene.ZombiesScene;

import com.uqbar.vainilla.GameScene;

public class IntroScene extends GameScene{

	private Dimension dimension;
	private ZombiesScene scene;
	
	public IntroScene (Dimension dimension,Zombies game,ReproductorDeSonidoDeFondo sonido){
		super();
		this.dimension = dimension;
//		this.addComponent(sonido);
		this.addComponent(new FondoDeComienzo());
		this.addComponent(new OpcionDeComienzo(this.dimension,this));
		
	}

	public int getPorcentajeDeCargado() {
		return this.getNextScene().getPorcentajeDeCargado();
	}

	public ZombiesScene getNextScene() {
		return scene;
	}

	public void setNextScene(ZombiesScene scene) {
		this.scene = scene;
	}

	public String currentComponentLoading() {
		return this.getNextScene().currentComponentLoading();
	}

	public void goToNextScene() {
		((Zombies) this.getGame()).startGame(this.getNextScene());
		this.getNextScene().siguienteOleada();
	}

	public void goToCargandoNextScene() {
		((Zombies) this.getGame()).InitializeNextScene();
		this.addComponent(new CargandoJuego(this.dimension));
	}
	
	
	
	
}
