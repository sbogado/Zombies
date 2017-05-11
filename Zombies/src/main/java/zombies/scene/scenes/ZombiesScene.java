package zombies.scene.scenes;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.GameScene;
import com.uqbar.vainilla.appearances.Sprite;

import ar.edu.unq.games.vainillautils.AnimationRotate;
import ar.edu.unq.games.vainillautils.AnimationRotateMoved;
import ar.edu.unq.games.vainillautils.SpriteMoved;
import ar.edu.unq.games.vainillautils.Tuning;
import ar.edu.unq.games.vainillautils.Vector2D;
import sound.SoundBuilderZombie;
import zombie.Zombies;
import zombie.introscene.ReproductorDeSonidoDeFondo;
import zombie.zombiescene.scenecargandostates.SonidoDeFondoCargando;
import zombie.zombiescene.scenecargandostates.ZombieSceneCargando;
import zombies.scene.components.Bala;
import zombies.scene.components.BarraDeVida;
import zombies.scene.components.Barricada;
import zombies.scene.components.BarricadaDeCostado;
import zombies.scene.components.BarricadaDeFrente;
import zombies.scene.components.Camino;
import zombies.scene.components.EstadoDeArmaEquipada;
import zombies.scene.components.GolpeDeMonstruo;
import zombies.scene.components.Mapa;
import zombies.scene.components.Marcador;
import zombies.scene.components.Mira;
import zombies.scene.components.Oleada;
import zombies.scene.components.PantallaPausada;
import zombies.scene.components.Pared;
import zombies.scene.components.Personaje;
import zombies.scene.components.Pool;
import zombies.scene.components.PressFToFiX;
import zombies.scene.components.PuntajeJugador;
import zombies.scene.monstruos.FastZombie;
import zombies.scene.monstruos.FatZombie;
import zombies.scene.monstruos.Monstruo;
import zombies.scene.monstruos.SlowZombie;
import zombies.web.observer.AmountOfZombiesKilledObserver;
import zombies.web.observer.MonsterKilledObserver;
import zombies.web.observer.TimeObserver;
import zombies.web.observer.TimeSubject;
import zombiescene.strategies.ControlDelJugador;

public class ZombiesScene extends GameScene implements TimeSubject {

	private List<Bala> balas = new ArrayList<Bala>();
	private Marcador marcadorComputer;
	private Marcador marcadorPlayer;
	private PuntajeJugador rotation;
	private Personaje personaje;
	private int maxScore = 0;
	private PuntajeJugador puntaje;
	private List<Monstruo> monstruos = new ArrayList<Monstruo>();
	private Mapa mapa;
	private List<Barricada> barricadas = new ArrayList<Barricada>();
	private List<Pared> paredes = new ArrayList<Pared>();
	private List<Camino> caminos = new ArrayList<Camino>();
	private double desplazamientoDePantallaX = 0;
	private double desplazamientoDePantallaY = 0;
	private List<GolpeDeMonstruo> golpesDeMonstruos = new ArrayList<GolpeDeMonstruo>();
	private List<Oleada> oleadas;
	private SoundBuilderZombie nuevaOleadaSound = new SoundBuilderZombie(
			this.getClass().getClassLoader().getResourceAsStream("nueva oleada.wav"));
	private Dimension dimension;
	private PantallaPausada pantallaPausada;
	private ReproductorDeSonidoDeFondo sonidoDeFondo;
	private int procentajeDeCargado = 0;
	private String currentComponentLoadig = "";
	private ZombieSceneCargando cargandoState;
	private int cantidadDePixelesPorVertice;
	private Oleada currentOleada;
	private List<Bala> balasRestantes = new ArrayList<Bala>();
	private PressFToFiX pressFToFix;
	private boolean pressFToFixAdded = false;
	private Pool pool;
	private List<Personaje> multiplayerPersonajes;
	private List<TimeObserver> timeObservers;

	public ZombiesScene(Dimension dimension, int cantidadDePixelesPorVertice, ReproductorDeSonidoDeFondo sonido,
			Zombies game) {
		super();
		this.setGame(game);
		this.dimension = dimension;
		this.sonidoDeFondo = sonido;
		this.cantidadDePixelesPorVertice = cantidadDePixelesPorVertice;

		this.pool = new Pool(this);

		cargarPuntajeMonstruosMuertos();
		setObserversToMonsters();
		setTimeObservers(getGame().getTimeObservers());
		addExternalComponents();

		this.setCargandoState(new SonidoDeFondoCargando(this));
		this.pressFToFix = new PressFToFiX(this.dimension);
		this.multiplayerPersonajes = new ArrayList<Personaje>();
	}

	private void addExternalComponents() {
		for (GameComponent<ZombiesScene> component : getGame().getExternalComponents()) {
			addComponent(component);
		}
	}

	private void setObserversToMonsters() {
		List<Monstruo> monstruos = getPool().getAllZombies();

		for (MonsterKilledObserver observer : getGame().getMonsterKilledObservers()) {
			setObserverToMonster(monstruos, observer);
		}
	}

	private void setObserverToMonster(List<Monstruo> monstruos, MonsterKilledObserver observer) {

		for (Monstruo monstruo : monstruos) {
			monstruo.addMonsterKilledObserver(observer);
		}
	}

	public void checkEnd() {
		if (getGame() != null && getGame().getMission().isAcomplished(getGame().getMissionObserver())) {
			winGame();

		}
	}

	public void winGame() {
		getGame().buildEndScene(true);
		getGame().addRewardToPlayer();
		getGame().updatePlayer();
	}

	public void cargaFinalizada() {
		this.procentajeDeCargado = 100;
		this.currentComponentLoadig = "Iniciando Juego";
	}

	public void cargarMiraDePersonaje() {
		this.procentajeDeCargado = 95;
		this.currentComponentLoadig = "Mira de personaje";

		Sprite[] spritesMira = { Sprite.fromImage("mira1.png"), Sprite.fromImage("mira2.png"),
				Sprite.fromImage("mira3.png") };
		AnimationRotate animationMira = new AnimationRotate(.01, spritesMira);
		this.setMira(new Mira(animationMira));
	}

	public void cargarEscenario() {
		this.procentajeDeCargado = 81;
		this.currentComponentLoadig = "Escenario";

		List<Pared> paredes = new ArrayList<Pared>();
		paredes.add(new Pared(130, 20, 335, 10, this));
		paredes.add(new Pared(215, 20, 560, 10, this));
		paredes.add(new Pared(20, 475, 335, 10, this));
		paredes.add(new Pared(200, 20, 870, 10, this));
		paredes.add(new Pared(40, 20, 305, 350, this));
		paredes.add(new Pared(170, 20, 40, 350, this));
		paredes.add(new Pared(20, 140, 40, 350, this));
		paredes.add(new Pared(20, 100, 40, 580, this));
		paredes.add(new Pared(100, 20, 40, 680, this));
		paredes.add(new Pared(120, 20, 230, 680, this));
		paredes.add(new Pared(20, 100, 330, 585, this));
		paredes.add(new Pared(90, 20, 330, 585, this));
		paredes.add(new Pared(340, 20, 520, 585, this));
		paredes.add(new Pared(120, 20, 955, 585, this));
		paredes.add(new Pared(20, 100, 1055, 10, this));
		paredes.add(new Pared(20, 380, 1055, 220, this));
		paredes.add(new Pared(20, 60, 700, 10, this));
		paredes.add(new Pared(20, 250, 700, 155, this));
		paredes.add(new Pared(20, 85, 700, 510, this));
		paredes.add(new Pared(130, 20, 700, 310, this));
		paredes.add(new Pared(90, 20, 970, 310, this));

		this.addBarricada(new BarricadaDeFrente(220, 350, this));
		this.addBarricada(new BarricadaDeFrente(470, 10, this));
		this.addBarricada(new BarricadaDeFrente(780, 10, this));
		this.addBarricada(new BarricadaDeFrente(150, 680, this));
		this.addBarricada(new BarricadaDeFrente(870, 585, this));
		this.addBarricada(new BarricadaDeFrente(430, 585, this));

		this.addBarricada(new BarricadaDeCostado(40, 490, this));
		this.addBarricada(new BarricadaDeCostado(1055, 120, this));

		List<Camino> caminos = new ArrayList<Camino>();
		caminos.add(new Camino(49, 49, 1, 1, this));
		caminos.add(new Camino(49, 50, 51, 51, this));
		caminos.add(new Camino(49, 49, 101, 101, this));
		caminos.add(new Camino(49, 49, 151, 151, this));
		caminos.add(new Camino(49, 49, 201, 201, this));
		caminos.add(new Camino(49, 49, 226, 226, this));
		caminos.add(new Camino(49, 49, 226, 251, this));
		caminos.add(new Camino(49, 49, 226, 301, this));
		caminos.add(new Camino(49, 49, 226, 351, this));
		caminos.add(new Camino(49, 49, 226, 401, this));
		caminos.add(new Camino(49, 49, 226, 451, this));
		caminos.add(new Camino(49, 49, 226, 501, this));
		caminos.add(new Camino(49, 49, 226, 526, this));
		caminos.add(new Camino(49, 49, 276, 526, this));
		caminos.add(new Camino(49, 49, 326, 526, this));
		caminos.add(new Camino(49, 49, 376, 526, this));
		caminos.add(new Camino(49, 49, 426, 526, this));
		caminos.add(new Camino(49, 49, 476, 526, this));
		caminos.add(new Camino(49, 49, 526, 526, this));
		caminos.add(new Camino(49, 49, 576, 526, this));

		caminos.add(new Camino(49, 49, 176, 576, this));
		caminos.add(new Camino(49, 49, 176, 626, this));
		caminos.add(new Camino(49, 49, 176, 676, this));
		caminos.add(new Camino(49, 49, 176, 526, this));
		caminos.add(new Camino(49, 49, 126, 526, this));
		caminos.add(new Camino(49, 49, 76, 526, this));
		caminos.add(new Camino(49, 49, 26, 526, this));

		caminos.add(new Camino(49, 49, 456, 576, this));
		caminos.add(new Camino(49, 49, 456, 626, this));

		caminos.add(new Camino(49, 49, 796, 50, this));
		caminos.add(new Camino(49, 49, 796, 1, this));

		caminos.add(new Camino(49, 49, 496, 100, this));
		caminos.add(new Camino(49, 49, 496, 50, this));
		caminos.add(new Camino(49, 49, 496, 1, this));

		caminos.add(new Camino(49, 49, 900, 670, this));
		caminos.add(new Camino(49, 49, 900, 620, this));
		caminos.add(new Camino(49, 49, 900, 570, this));
		caminos.add(new Camino(49, 49, 900, 520, this));

		caminos.add(new Camino(250, 450, 401, 101, this));
		caminos.add(new Camino(200, 49, 651, 101, this));
		caminos.add(new Camino(300, 49, 851, 151, this));
		caminos.add(new Camino(200, 200, 801, 51, this));
		caminos.add(new Camino(49, 300, 851, 201, this));
		caminos.add(new Camino(200, 150, 801, 401, this));
		caminos.add(new Camino(200, 49, 651, 451, this));

		this.setParedes(paredes);
		this.setCaminos(caminos);
	}

	public void cargarOleadas() {
		this.procentajeDeCargado = 54;
		this.currentComponentLoadig = "Monstruos";
		this.oleadas = new ArrayList<Oleada>();

		this.addOleadasLight();
		// this.addOleadasMedium();
		// this.addOleadasHard();
	}

	public void addOleadasLight() {
		this.oleadas.add(new Oleada(this, 1, -1, 1, 10));
		// this.oleadas.add(new Oleada(this,2,-1,0.5,15));
		// this.oleadas.add(new Oleada(this,3,-1,0.5,20));
		// this.oleadas.add(new Oleada(this,5,-1,0.5,25));
		// this.oleadas.add(new Oleada(this,6,-1,0.5,30));
		// this.oleadas.add(new Oleada(this,7,-1,1,35));
		// this.oleadas.add(new Oleada(this,8,-1,0.5,40));
		// this.oleadas.add(new Oleada(this,9,-1,0.5,50));
		// this.oleadas.add(new Oleada(this,1,0,0.5,60));
		// this.oleadas.add(new Oleada(this,1,1,0.5,80));
	}

	public void cargarPersonaje() {
		this.procentajeDeCargado = 40;
		this.currentComponentLoadig = "Personaje";
		double personajeX = dimension.getWidth() / 2;
		double personajeY = dimension.getHeight() / 2;

		SpriteMoved[] sprites2 = { SpriteMoved.fromImage("personajeQuieto.png"),
				SpriteMoved.fromImage("personajeQuieto.png"), SpriteMoved.fromImage("personajeQuieto.png") };
		AnimationRotateMoved personajeParado = new AnimationRotateMoved(.01, sprites2);
		Personaje personaje = new Personaje(((Zombies) getGame()).getPlayer(), personajeParado, personajeX, personajeY,
				0, dimension.getWidth(), 0, dimension.getHeight(),
				Tuning.newInstance("player.strategy", ControlDelJugador.class));
		this.setPersonaje(personaje);

		EstadoDeArmaEquipada armaEscala = new EstadoDeArmaEquipada(personaje, dimension);
		this.addComponent(armaEscala);

		BarraDeVida barraDeVida = new BarraDeVida(personaje, dimension);
		this.addBarraDeVida(barraDeVida);
	}

	public void cargarPuntajeJugador() {
		this.procentajeDeCargado = 35;
		this.currentComponentLoadig = "Puntaje de jugador";
		PuntajeJugador puntajeJugador = new PuntajeJugador((dimension.getWidth() / 2) - 250,
				(dimension.getWidth() / 2) - 400);
		this.setPuntaje(puntajeJugador);
	}

	public void cargarMapa() {
		this.procentajeDeCargado = 18;
		this.currentComponentLoadig = "Mapa";
		Mapa mapa = new Mapa(108, 71, this.cantidadDePixelesPorVertice);
		this.setMapa(mapa);
	}

	public void cargarPantallaPausada() {
		this.procentajeDeCargado = 13;
		this.currentComponentLoadig = "Pantalla pausada";
		this.pantallaPausada = new PantallaPausada(this);
		this.setDimension(dimension);
	}

	public void cargarSonidoDeFondo() {
		this.procentajeDeCargado = 5;
		this.currentComponentLoadig = "Sonido de fondo";
		this.addComponent(this.sonidoDeFondo);
	}

	public void cargarPuntajeMonstruosMuertos() {
		AmountOfZombiesKilledObserver puntajeMonstruosMuertos = new AmountOfZombiesKilledObserver(
				(dimension.getWidth() / 2) - 400, (dimension.getWidth() / 2) - 400);
		getGame().getMonsterKilledObservers().add(puntajeMonstruosMuertos);
		addComponent(puntajeMonstruosMuertos);
	}

	public void takeStep(Graphics2D graphics) {
		long now = System.nanoTime();
		double delta = this.getLastUpdateTime() > 0 ? (now - this.getLastUpdateTime()) / 1000000000L : 0;
		if (delta > 1) {
			delta = 0;
		}
		this.setLastUpdateTime(now);

		DeltaState state = this.getEventQueue().takeState(delta);

		if (getGame() != null && getGame().isPaused()) {
			updateComponentsPaused(graphics, state);
			this.getPersonaje().getStrategy().update(this.getPersonaje(), this, state);
		} else {
			updateComponents(graphics, state);
		}

		eliminarBalasUsadas();
		notifyTimeElapsedToObservers(state.getDelta());
		checkEnd();

	}

	private void updateComponents(Graphics2D graphics, DeltaState state) {
		for (GameComponent<?> component : new ArrayList<GameComponent<?>>(this.getComponents())) {
			if (component.isDestroyPending()) {
				this.removeComponent(component);
			} else {
				component.render(graphics);
				component.update(state);
			}
		}
	}

	private void updateComponentsPaused(Graphics2D graphics, DeltaState state) {
		for (GameComponent<?> component : new ArrayList<GameComponent<?>>(this.getComponents())) {
			if (component.isDestroyPending()) {
				this.removeComponent(component);
			} else {
				component.render(graphics);
			}
		}
	}

	private void eliminarBalasUsadas() {
		this.balasRestantes.clear();
		for (Bala bala : this.balas) {
			if (!bala.isDestroyPending()) {
				balasRestantes.add(bala);
			}
		}
		this.balas.clear();
		this.balas.addAll(this.balasRestantes);
	}

	public void siguienteOleada() {
		if (this.oleadas.size() > 0) {
			this.currentOleada = this.oleadas.remove(0);
			this.addComponent(this.currentOleada);
		}
	}

	// Get monstruos

	public List<Monstruo> getSlowZombies(int cantidad) {
		return this.pool.getSlowZombies(cantidad);
	}

	public List<Monstruo> getFastZombies(int cantidad) {
		return this.pool.getFastZombies(cantidad);
	}

	public List<Monstruo> getFatZombies(int cantidad) {
		return this.pool.getFatZombies(cantidad);
	}

	public List<Barricada> getBarricadas() {
		return barricadas;
	}

	public void addBarricada(Barricada barricada) {
		this.barricadas.add(barricada);
		this.addComponent(barricada);
	}

	public List<Pared> getParedes() {
		return paredes;
	}

	public void setParedes(List<Pared> paredes) {
		for (Pared pared : paredes) {
			this.addComponent(pared);
		}
		this.paredes = paredes;
	}

	public List<Camino> getCaminos() {
		return this.caminos;
	}

	public void setCaminos(List<Camino> caminos) {
		// for(Camino camino : caminos){
		// this.addComponent(camino);
		// }
		this.caminos = caminos;
	}

	public Mapa getMapa() {
		return mapa;
	}

	public void setMapa(Mapa mapa) {
		this.addComponent(mapa);
		this.mapa = mapa;
	}

	public PuntajeJugador getPuntaje() {
		return puntaje;
	}

	public Marcador getMarcadorComputer() {
		return marcadorComputer;
	}

	public Marcador getMarcadorPlayer() {
		return marcadorPlayer;
	}

	public Personaje getPersonaje() {
		return personaje;
	}

	public void setMarcadorPlayer(Marcador marcadorPlayer) {
		this.addComponent(marcadorPlayer);
		this.marcadorPlayer = marcadorPlayer;
	}

	public void setMarcadorComputer(Marcador marcadorComputer) {
		this.addComponent(marcadorComputer);
		this.marcadorComputer = marcadorComputer;
	}

	public void setPuntaje(PuntajeJugador puntaje) {
		this.addComponent(puntaje);
		this.puntaje = puntaje;
	}

	public void setPersonaje(Personaje player) {
		this.personaje = player;
		this.addComponent(player);
	}

	public void setMultiplayerPersonaje(Personaje player) {
		this.multiplayerPersonajes.add(player);
		this.addComponent(player);
	}

	public int getMaxScore() {
		return maxScore;
	}

	public List<Bala> getBalas() {
		return this.balas;
	}

	public void addBala(Bala bala) {
		this.addComponent(bala);
		this.balas.add(bala);
	}

	public void setNullPersonaje() {
		this.personaje = null;
	}

	public void setBalas(List<Bala> balasRestantes) {
		this.balas = balasRestantes;
	}

	public List<Monstruo> getMonstruos() {
		return monstruos;
	}

	public void addMonstruo(Monstruo monstruo) {
		this.monstruos.add(monstruo);
		this.addComponent(monstruo);
	}

	// public void addZombie() {
	// SpriteMoved[] sprites =
	// {SpriteMoved.fromImage("slowZombie.png"),SpriteMoved.fromImage("slowZombie.png"),SpriteMoved.fromImage("slowZombie.png")};
	// AnimationRotateMoved animation = new AnimationRotateMoved(.01,sprites);
	// Monstruo monstruo = new SlowZombie(animation,new Vector2D(0,-1),50, 50,
	// this);
	// this.monstruos.add(monstruo);
	// this.addComponent(monstruo);
	// }

	public Vector2D direccionRandom() {
		double xvector = -1 + Math.random() * 2;
		double yvector = -1 + Math.random() * 2;

		return new Vector2D(xvector, yvector);
	}

	public double velocidadRandom() {
		return 200 + Math.random() * 300;
	}

	public double coordenadaXRandom() {
		return Math.random() * this.getGame().getDisplayWidth();
	}

	public double coordenadaYRandom() {
		return Math.random() * this.getGame().getDisplayHeight();
	}

	public double radiansRandom() {
		return Math.random() * 3.14;
	}

	public void setMira(Mira mira) {
		this.addComponent(mira);
	}

	public PuntajeJugador getRotation() {
		return rotation;
	}

	public void setRotation(PuntajeJugador rotation) {
		this.rotation = rotation;
		this.addComponent(rotation);
	}

	public int getCantidadDeVerticesPorFilaGrafo() {
		return this.getMapa().getVerticesPorFila();
	}

	public int getCantidadDePixelesPorVerticeX() {
		return this.getMapa().getCantidadDePixelesPorVerticeX();
	}

	public int getCantidadDePixelesPorVerticeY() {
		return this.getMapa().getCantidadDePixelesPorVerticeY();
	}

	public double getDesplazamientoDePantallaX() {
		return desplazamientoDePantallaX;
	}

	public void setDesplazamientoDePantallaX(double desplazamientoDePantallaX) {
		this.desplazamientoDePantallaX = desplazamientoDePantallaX;
	}

	public double getDesplazamientoDePantallaY() {
		return desplazamientoDePantallaY;
	}

	public void setDesplazamientoDePantallaY(double desplazamientoDePantallaY) {
		this.desplazamientoDePantallaY = desplazamientoDePantallaY;
	}

	public void addBarraDeVida(BarraDeVida barraDeVida) {
		this.addComponent(barraDeVida);
	}

	public void addGolpeDeMonstruo(GolpeDeMonstruo golpeDeMonstruo) {
		this.golpesDeMonstruos.add(golpeDeMonstruo);
		this.addComponent(golpeDeMonstruo);
	}

	public List<GolpeDeMonstruo> getGolpesDeMonstruos() {
		return this.golpesDeMonstruos;
	}

	public SoundBuilderZombie getNuevaOleadaSound() {
		return nuevaOleadaSound;
	}

	public void setNuevaOleadaSound(SoundBuilderZombie nuevaOleada) {
		this.nuevaOleadaSound = nuevaOleada;
	}

	public Dimension getDimension() {
		return dimension;
	}

	public void setDimension(Dimension dimension){
		this.dimension = dimension;
	}

	public boolean isPaused() {
		return this.getGame() != null ? ((Zombies) this.getGame()).isPaused() : false;
	}

	public void pausar() {
		// this.sonidoDeFondo.pausar();
		this.addComponent(this.pantallaPausada);
		((Zombies) this.getGame()).pausar();
	}

	public void reanudar() {
		// this.sonidoDeFondo.reanudar();
		this.removeComponent(this.pantallaPausada);
		((Zombies) this.getGame()).reanudar();
	}

	public int getPorcentajeDeCargado() {
		return this.procentajeDeCargado;
	}

	public String currentComponentLoading() {
		return this.currentComponentLoadig;
	}

	public void setState(ZombieSceneCargando nextState) {
		this.setCargandoState(nextState);
	}

	public ZombieSceneCargando getCargandoState() {
		return cargandoState;
	}

	public void setCargandoState(ZombieSceneCargando cargandoState) {
		this.cargandoState = cargandoState;
	}

	private Oleada currentOleada() {
		return this.currentOleada;
	}

	public void addPressFToFix() {
		if (!this.pressFToFixAdded) {
			this.addComponent(this.pressFToFix);
			this.pressFToFixAdded = true;
		}
	}

	public void removePressFToFix() {
		if (this.pressFToFixAdded) {
			this.removeComponent(this.pressFToFix);
			this.pressFToFixAdded = false;
		}
	}

	public void removeSlowZombie(SlowZombie monstruo) {
		this.monstruos.remove(monstruo);
		this.pool.returnSlowZombie(monstruo);

	}

	public void removeFastZombie(FastZombie fastZombie) {
		this.monstruos.remove(fastZombie);
		this.pool.returnFastZombie(fastZombie);

	}

	public void removeFatZombie(FatZombie fatZombie) {
		this.monstruos.remove(fatZombie);
		this.pool.returnFatZombie(fatZombie);
	}

	public Zombies getGame() {
		return (Zombies) super.getGame();
	}
	
	public Pool getPool() {
		return pool;
	}

	public void setPool(Pool pool) {
		this.pool = pool;
	}

	@Override
	public void add(TimeObserver observer) {
		getTimeObservers().add(observer);
	}

	@Override
	public void remove(TimeObserver observer) {
		getTimeObservers().remove(observer);
	}

	@Override
	public void notifyTimeElapsedToObservers(double time) {
		for (TimeObserver observer : getTimeObservers()) {
			observer.notifyTimeElapsedToObserver(time);
		}
	}

	public List<TimeObserver> getTimeObservers() {
		return timeObservers;
	}

	public void setTimeObservers(List<TimeObserver> timeObservers) {
		this.timeObservers = timeObservers;
	}

}
