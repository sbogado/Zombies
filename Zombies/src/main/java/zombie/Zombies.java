package zombie;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import com.uqbar.vainilla.Game;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.GameScene;

import sound.SoundPlayerZombie;
import zombie.endscene.EndScene;
import zombie.introscene.IntroScene;
import zombie.introscene.ReproductorDeSonidoDeFondo;
import zombies.scene.scenes.ZombiesScene;
import zombies.web.observer.MonsterObserver;
import zombies.web.observer.TimeObserver;
import zombies.web.persistence.MissionObserver;
import zombies.web.persistence.PersistentPlayer;

public class Zombies extends Game {

	private JFrame window;
	private Dimension dimension;
	private int cantidaDePixelesPorVertice;
	private ReproductorDeSonidoDeFondo sonidoDefondo;
	private boolean isPaused = false;
	private IntroScene introScene;
	public static SoundPlayerZombie soundPlayer;
	private PersistentPlayer player;
	private MissionObserver mission;
	private List<TimeObserver> timeObservers;
	private List<MonsterObserver> monsterObservers;
	private List<GameComponent<ZombiesScene>> externalComponents;

	@Override
	protected void initializeResources() {
		dimension = new Dimension(800, 600);

	}
	
	public void readAllData(ZombiesScene scene) {

	}

	public boolean isPaused() {
		return this.isPaused;
	}

	public void pausar() {
		this.isPaused = true;
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
		IntroScene introScene = new IntroScene(this.dimension, this, this.sonidoDefondo);
		this.introScene = introScene;
		this.setCurrentScene(introScene);
	}

	public void buildEndScene(boolean ganaste) {
		EndScene scene = new EndScene((ZombiesScene) this.getCurrentScene(), this.dimension, ganaste);
		this.setCurrentScene(scene);
	}

	public void InitializeNextScene() {
		ZombiesScene zombiesScene = new ZombiesScene(this.dimension, this.getCantidadDePixelesPorVertice(),
				this.sonidoDefondo, this);
		this.introScene.setNextScene(zombiesScene);
	}

	public void startGame(GameScene scene) {
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

	public void closeWindow(){
		getWindow().setVisible(false);
		getWindow().dispose();
	}
	
	public static double calculateRotation(double ubicacionX, double ubicacionY, double destinoX, double destinoY) {
		double yMouse = destinoY - ubicacionY;
		double xMouse = destinoX - ubicacionX;
		double result;
		if (xMouse < 0) {
			result = 2 * Math.PI - (Math.acos(-yMouse / (Math.sqrt(Math.pow(xMouse, 2) + Math.pow(yMouse, 2)))));
		} else {
			result = (Math.acos(-yMouse / (Math.sqrt(Math.pow(xMouse, 2) + Math.pow(yMouse, 2)))));
		}
		return result;
	}

	public ReproductorDeSonidoDeFondo getSonidoDefondo() {
		return sonidoDefondo;
	}

	public void setSonidoDefondo(ReproductorDeSonidoDeFondo sonidoDefondo) {
		this.sonidoDefondo = sonidoDefondo;
	}

	public PersistentPlayer getPlayer() {
		return player;
	}

	public void setPlayer(PersistentPlayer player) {
		this.player = player;
	}

	public List<MonsterObserver> getMonsterObservers() {
		if(monsterObservers == null){
			monsterObservers = new ArrayList<MonsterObserver>();
		}
		return monsterObservers;
	}

	public void setMonsterObservers(List<MonsterObserver> observer) {
		this.monsterObservers = observer;
	}

	public List<GameComponent<ZombiesScene>> getExternalComponents() {
		if(externalComponents == null){
			externalComponents = new ArrayList<GameComponent<ZombiesScene>>();
		}
		return externalComponents;
	}

	public void setExternalComponents(List<GameComponent<ZombiesScene>> externalComponents) {
		this.externalComponents = externalComponents;
	}

	public List<TimeObserver> getTimeObservers() {
		if(timeObservers == null){
			timeObservers = new ArrayList<TimeObserver>();
		}
		return timeObservers;
	}

	public void setTimeObservers(List<TimeObserver> timeObservers) {
		this.timeObservers = timeObservers;
	}

	public MissionObserver getMission() {
		return mission;
	}

	public void setMission(MissionObserver mission) {
		this.mission = mission;
	}

	public JFrame getWindow() {
		return window;
	}

	public void setWindow(JFrame window) {
		this.window = window;
	}

}
