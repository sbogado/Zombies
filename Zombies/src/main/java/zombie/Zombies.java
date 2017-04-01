package zombie;

import java.awt.Dimension;
import java.awt.Graphics2D;

import sound.SoundPlayerZombie;
import zombie.endscene.EndScene;
import zombie.introscene.IntroScene;
import zombie.introscene.ReproductorDeSonidoDeFondo;
import zombies.scene.ZombiesScene;

import com.uqbar.vainilla.DesktopGameLauncher;
import com.uqbar.vainilla.Game;
import com.uqbar.vainilla.GameScene;



public class Zombies extends Game {

	private Dimension dimension;
	private int cantidaDePixelesPorVertice;
	private ReproductorDeSonidoDeFondo sonidoDefondo;
	private boolean isPaused = false;
	private IntroScene introScene;
	public static SoundPlayerZombie  player;
	
	@Override
	protected void initializeResources() {
		dimension = new Dimension(800, 600);

	}

	public void takeStep(Graphics2D graphics) {
		if(!this.isPaused){
			this.getCurrentScene().takeStep(graphics);
		}
		else{
			((ZombiesScene) this.getCurrentScene()).takeStepPaused(graphics);
		}
	}

	public void readAllData(ZombiesScene scene){
		
		
	}
	
	public boolean isPaused(){
		return this.isPaused;
	}
	
	public void pausar() {
		this.isPaused  = true;
	}
	public void reanudar() {
		this.isPaused = false;
	}
	
	@Override
	protected void setUpScenes() {
		this.setSonidoDefondo(new ReproductorDeSonidoDeFondo());
		this.cantidaDePixelesPorVertice = 10; 
		this.buildIntroScene();
	}

	public void buildIntroScene() {
		IntroScene introScene = new IntroScene(this.dimension,this,this.sonidoDefondo);
		this.introScene = introScene;
		this.setCurrentScene(introScene);
	}

	public void buildEndScene(boolean ganaste) {
		EndScene scene = new EndScene((ZombiesScene)this.getCurrentScene(),this.dimension,ganaste);
		this.setCurrentScene(scene);
	}
	
	public void InitializeNextScene() {
		ZombiesScene zombiesScene = new ZombiesScene(this.dimension,this.getCantidadDePixelesPorVertice(),this.sonidoDefondo);
		this.introScene.setNextScene(zombiesScene);
	}
	
	public void startGame(GameScene scene){
		this.setCurrentScene(scene);
	}
	
	private int getCantidadDePixelesPorVertice() {
		return this.cantidaDePixelesPorVertice;
	}

	@Override
	public Dimension getDisplaySize() {
		return dimension;
	}

	@Override
	public String getTitle() {
		return "Zombies";
	}
	
	public static double calculateRotation(double ubicacionX, double ubicacionY,double destinoX, double destinoY){
		double yMouse = destinoY -ubicacionY;
		double xMouse = destinoX -ubicacionX;
		double result;
		if(xMouse < 0 ){
			result =  2*Math.PI -(Math.acos(-yMouse/(Math.sqrt(Math.pow(xMouse,2)+Math.pow(yMouse,2))))); 
		}
		else{
			result = (Math.acos(-yMouse/(Math.sqrt(Math.pow(xMouse,2)+Math.pow(yMouse,2)))));
		}	
		return result;
	}
	



	public ReproductorDeSonidoDeFondo getSonidoDefondo() {
		return sonidoDefondo;
	}

	public void setSonidoDefondo(ReproductorDeSonidoDeFondo sonidoDefondo) {
		this.sonidoDefondo = sonidoDefondo;
	}
	
	
	public static void main(String[] args) {
		Zombies game = new Zombies();
		SonidoAparte sonido = new SonidoAparte();
		new DesktopGameLauncherNoVisible(sonido).launch();
		
		Zombies.player = sonido.getSoundPlayer();
		
		new DesktopGameLauncher(game).launch();
	}
	
	
}
