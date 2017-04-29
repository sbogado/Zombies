package zombies.scene.monstruos;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;

import ar.edu.unq.games.vainillautils.AnimationLowCost;
import ar.edu.unq.games.vainillautils.AnimationRotateMoved;
import ar.edu.unq.games.vainillautils.Vector2D;
import zombie.Zombies;
import zombie.scene.rules.ChoqueBarricadaRule;
import zombie.scene.rules.ChoqueParedRule;
import zombie.scene.rules.ColisionRule;
import zombie.scene.rules.DesplazamientoLibreRule;
import zombie.zombiescene.algoritmosdemonstruos.AlgoritmoDeBusqueda;
import zombie.zombiescene.algoritmosdemonstruos.BusquedaPorHabitacionFarmHouse;
import zombie.zombiescene.monstruostates.MonstruoState;
import zombie.zombiescene.monstruostates.MonstruoStateCaminando;
import zombie.zombiescene.monstruostates.MonstruoStateEntrando;
import zombies.scene.components.Bala;
import zombies.scene.components.Barricada;
import zombies.scene.components.GolpeDeMonstruo;
import zombies.scene.components.Mapa;
import zombies.scene.components.Personaje;
import zombies.scene.components.ZombiesRule;
import zombies.scene.scenes.ZombiesScene;
import zombies.web.observer.MonsterObserver;
import zombies.web.observer.MonsterSubject;

public abstract class Monstruo extends GameComponent<ZombiesScene> implements MonsterSubject{

	private Vector2D posicionAnterior;
	private double tiempoDeMuerte;
	private double xInicial;
	private double yInicial;
	private MonstruoState monstruoState;
	private Vector2D puntoDeAtaque;
	private double rotation;
	private Vector2D direccion;
	private boolean animacionMueriendoseActivada = false;
	private boolean estaTiradoEnElPiso = false;
	private double tiempoTiradoEnElPiso = 5;
	private boolean animacionEnElPisoActivada = false;
	private AlgoritmoDeBusqueda algoritmoDeBusqueda;
	private List<Point> caminoHaciaPersonaje;
	private boolean isMoveSetted;
	private double partialRotation;
	private int velocidad;
	private int points;
	private ZombiesScene scene;
	private double tiempoDePersecucion;
	private double tiempoDeRecuperacionDeImpacto;
	private double tiempoMuertoDeGolpe;
	private int danio;
	private double vida;
	private double tiempoMuertoDeGolpeMax;
	private List<MonsterObserver> observers;

	public Monstruo(double xInicial, double yInicial, double puntoAtaqueX, double puntoAtaqueY, Mapa mapa,
			int velocidad, int puntos, double recuperacionDeImpacto, int danio, int vida, double tiempoDeGolpe,
			ZombiesScene scene) {
		super(xInicial, yInicial);
		this.setPosicionAnterior(new Vector2D(this.getX(), this.getY()));
		this.caminoHaciaPersonaje = new ArrayList<Point>();
		this.velocidad = velocidad;
		this.points = puntos;
		this.scene = scene;
		this.tiempoDePersecucion = 5;
		this.tiempoDeRecuperacionDeImpacto = recuperacionDeImpacto;
		this.danio = danio;
		this.vida = vida;
		this.tiempoDeMuerte = 0.6;
		this.tiempoMuertoDeGolpe = tiempoDeGolpe;
		this.tiempoMuertoDeGolpeMax = tiempoDeGolpe;
		this.xInicial = xInicial;
		this.yInicial = yInicial;
		this.setZ(-1);
		this.algoritmoDeBusqueda = BusquedaPorHabitacionFarmHouse.INSTANCE;
		// this.algoritmoDeBusqueda = new Shadow();
		// this.algoritmoDeBusqueda = new Dijkstra(( (108*71)+1),10000, mapa);
		this.direccion = new Vector2D(0, -1);
		this.rotation = 0;
		this.puntoDeAtaque = new Vector2D(puntoAtaqueX, puntoAtaqueY);
	}

	/*******************************
	 * OBSERVER - SUBJECT METHODS
	 *****************************/

	@Override
	public void add(MonsterObserver observer) {
		this.getObservers().add(observer);
	}

	@Override
	public void remove(MonsterObserver observer) {
		this.getObservers().remove(observer);
	}
	
	@Override
	public void notifyMonsterKilledToObservers() {
		for(MonsterObserver observer : getObservers()){
			observer.notifyMonsterKilledToObserver();
		}
	}

	/***************************************************************************************/

	public void reset() {
		this.setX(this.xInicial);
		this.setY(this.yInicial);
	}

	public void initState() {
		this.monstruoState = new MonstruoStateEntrando(this);
	}

	public abstract void sonidoDeMuerte();

	public abstract AnimationLowCost getImagenCaminando();

	public abstract AnimationLowCost getImagenImpactado();

	public abstract AnimationLowCost getImagenGolpeandoPersonaje();

	public abstract AnimationLowCost getImagenGolpeandoBarricada();

	public abstract AnimationLowCost getQuedarseTiradoEnElPisoAnimation();

	public abstract AnimationLowCost getEnElPisoAnimation();

	public abstract AnimationLowCost getCallendoDespacioAnimation();

	public abstract List<ZombiesRule> getRules();

	public abstract DesplazamientoLibreRule getDesplazamientoLibreRule();

	public abstract ColisionRule getColisionRule();

	public abstract void mitosis();

	public abstract void setColisionRule(ColisionRule colisionRule);

	public abstract void setColisionBarricadeRule(ChoqueBarricadaRule colisionRule);

	public abstract ChoqueBarricadaRule getColisionBarricadeRule();

	public abstract void addRule(ZombiesRule asteroidRule);

	public abstract void setDesplazamientoRule(DesplazamientoLibreRule desplazamientoLibreRule);

	public abstract ChoqueParedRule getColisionParedRule();

	public abstract void setColisionParedRule(ChoqueParedRule choqueParedRule);

	public abstract ChoqueParedRule getChoqueParedRule();

	public abstract void setChoqueParedRule(ChoqueParedRule choqueParedRule);

	public abstract void sonidoDeSalida();

	public abstract void sonidoGolpeandoBarricada();

	public boolean getEstaVivo() {
		return this.getVida() > 0;
	}

	public Vector2D nuevaPosicion(DeltaState deltaState) {
		return (this.getDireccion().asVersor().producto(this.getVelocidad() * deltaState.getDelta())
				.suma(new Vector2D(this.getX(), this.getY())));
	}

	public void obtenerCaminoHaciaPersonaje(Personaje personaje) {
		this.setCaminoHaciaPersonaje(this.getAlgoritmoDeBusqueda().getCamino((int) this.getX(), (int) this.getY(),
				(int) personaje.getX(), (int) personaje.getY()));
	}

	private AlgoritmoDeBusqueda getAlgoritmoDeBusqueda() {
		return this.algoritmoDeBusqueda;
	}

	public void moverAlaSiguientePosicionSimple(int x, int y, DeltaState delta) {

		while (!((this.getX() >= x - 25 && this.getX() <= x + 25)
				&& (this.getY() >= y - 25 && this.getY() <= y + 25))) {
			double rotation = Zombies.calculateRotation(this.getX(), this.getY(), x, y);
			this.rotate(rotation - this.getRotation());
			this.getDesplazamientoLibreRule().apply(this, this.nuevaPosicion(delta), this.getScene());
		}
	}

	public void moverAlaSiguientePosicion(int x, int y, DeltaState delta) {

		if ((this.getX() >= x - 25 && this.getX() <= x + 25) && (this.getY() >= y - 25 && this.getY() <= y + 25)) {
			((MonstruoStateCaminando) this.monstruoState).aumentarIndiceCamino();
		} else {
			double rotation = Zombies.calculateRotation(this.getX(), this.getY(), x, y);
			this.rotate(rotation - this.getRotation());
			this.getDesplazamientoLibreRule().apply(this, this.nuevaPosicion(delta), this.getScene());

		}
	}

	public void golpear(Personaje personaje) {
		double rotation = Zombies.calculateRotation(this.getX(), this.getY(), personaje.getX(), personaje.getY());
		this.rotate(rotation - this.getRotation());
		this.monstruoState.modoGolpeando();
		this.getScene().addGolpeDeMonstruo(new GolpeDeMonstruo(this, this.getScene()));
		this.setTiempoMuertoToMax();

	}

	public void golpear(Barricada barricada) {
		// double rotation =
		// Zombies.calculateRotation(this.getX(),this.getY(),barricada.getX()+barricada.getWidth()/2,barricada.getY()+barricada.getHeight()/2);
		this.sonidoGolpeandoBarricada();
		// this.rotate(rotation - this.getRotation());
		this.monstruoState.modoGolpeando();
		this.setTiempoMuertoToMax();
	}

	public void update(DeltaState deltaState) {
		this.setPosicionAnterior(this.getX(), this.getY());

		if (this.getEstaVivo()) {
			this.getMonstruoState().accionar(deltaState);
		} else {
			this.morirse();
			this.setTiempoDeMuerte(this.getTiempoDeMuerte() - deltaState.getDelta());
			if (this.getTiempoDeMuerte() <= 0) {
				this.destroy();
			}
		}

		super.update(deltaState);
	}

	public void morirse() {
		if (this.animacionMueriendoseActivada == false) {
			this.animacionMueriendoseActivada = true;
			this.mitosis();
			this.sonidoDeMuerte();
			AnimationRotateMoved animation = this.getQuedarseTiradoEnElPisoAnimation();
			animation.rotate(this.getRotation());
			this.setAppearance(animation);
			this.notifyMonsterKilledToObservers();
		}
	}

	// private void tirarseAlPiso() {
	// if(this.estaTiradoEnElPiso == false){
	// this.estaTiradoEnElPiso = true;
	// AnimationRotateMoved animation = this.getEnElPisoAnimation();
	// animation.rotate(this.getRotation());
	// this.setAppearance(animation);
	// }
	// }

	// private void quedarseTiradoEnElPiso() {
	// if(this.animacionEnElPisoActivada == false){
	// this.animacionEnElPisoActivada = true;
	// AnimationRotateMoved animation =
	// this.getQuedarseTiradoEnElPisoAnimation();
	//
	// animation.rotate(this.getRotation());
	// this.setAppearance(animation);
	// }
	// }

	public void render(Graphics2D graphics) {
		double x = this.getX();
		double y = this.getY();

		this.setX(this.getX() + this.getScene().getDesplazamientoDePantallaX());
		this.setY(this.getY() + this.getScene().getDesplazamientoDePantallaY());

		super.render(graphics);

		// this.label.setText("x: "+x+"\n"+"y: "+y);
		// this.label.render(this, graphics);

		this.setX(x);
		this.setY(y);

	}

	protected void initRules() {
		this.setDesplazamientoRule(new DesplazamientoLibreRule());
		this.setColisionRule(new ColisionRule(this, "fx_0051.wav"));
		this.setColisionBarricadeRule(new ChoqueBarricadaRule());
	}

	public void rotate(double radians) {

		this.calcularNuevaRotation(radians);

		((AnimationLowCost) this.getAppearance()).rotate(this.getRotation());

		this.getDireccion().rotate(radians);
	}

	public void calcularNuevaRotation(double radians) {
		double diferenciaDeRotation = this.getRotation() + radians;

		if (diferenciaDeRotation < 0) {
			this.setRotation(Math.PI * 2 + diferenciaDeRotation);
		} else {
			this.setRotation(diferenciaDeRotation % (Math.PI * 2));
		}
	}

	public int getCenterX() {
		return (int) (this.getX() + this.getAppearance().getWidth() / 2);
	}

	public int getCenterY() {
		return (int) (this.getY() + this.getAppearance().getHeight() / 2);
	}

	public Vector2D direccionRandom() {
		double xvector = -1 + Math.random() * 2;
		double yvector = -1 + Math.random() * 2;

		return new Vector2D(xvector, yvector);
	}

	public double velocidadRandom() {
		return 200 + Math.random() * 300;
	}

	public void recibirImpactoDeBala(Bala bala) {
		this.setVida(this.getVida() - bala.getDanio());
		if (this.getVida() <= 0) {
			this.getScene().removeComponent(this);
			this.setZ(-4);
			this.getScene().addComponent(this);
		}
	}

	public void setYBack() {
		this.setY(this.getPosicionAnterior().getY());

	}

	public void setXBack() {
		this.setX(this.getPosicionAnterior().getX());

	}

	public int getWidth() {
		return 40;
	}

	public void setMonstruoState(MonstruoState monstruoState) {
		this.monstruoState = monstruoState;
	}

	public MonstruoState getMonstruoState() {
		return this.monstruoState;
	}

	public Vector2D getPuntoDeAtaque() {
		return this.puntoDeAtaque;
	}

	public void setRotation(double rotation) {
		this.rotation = rotation;
	}

	public double getRotation() {
		return this.rotation;
	}

	public Vector2D getDireccion() {
		return direccion;
	}

	public void setDireccion(Vector2D direccion) {
		this.direccion = direccion;
	}

	public double getTiempoDeMuerte() {
		return tiempoDeMuerte;
	}

	public void setTiempoDeMuerte(double tiempoDeMuerte) {
		this.tiempoDeMuerte = tiempoDeMuerte;
	}

	public double getxInicial() {
		return xInicial;
	}

	public void setxInicial(double xInicial) {
		this.xInicial = xInicial;
	}

	public double getyInicial() {
		return yInicial;
	}

	public void setyInicial(double yInicial) {
		this.yInicial = yInicial;
	}

	public double getTiempoTiradoEnElPiso() {
		return tiempoTiradoEnElPiso;
	}

	public void setTiempoTiradoEnElPiso(double tiempoTiradoEnElPiso) {
		this.tiempoTiradoEnElPiso = tiempoTiradoEnElPiso;
	}

	public int getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	@Override
	public ZombiesScene getScene() {
		return scene;
	}

	public double getTiempoDePersecucion() {
		return tiempoDePersecucion;
	}

	public void setTiempoDePersecucion(double tiempoDePersecucion) {
		this.tiempoDePersecucion = tiempoDePersecucion;
	}

	public double getTiempoDeRecuperacionDeImpacto() {
		return tiempoDeRecuperacionDeImpacto;
	}

	public void setTiempoDeRecuperacionDeImpacto(double tiempoDeRecuperacionDeImpacto) {
		this.tiempoDeRecuperacionDeImpacto = tiempoDeRecuperacionDeImpacto;
	}

	public int getDanio() {
		return danio;
	}

	public void setDanio(int danio) {
		this.danio = danio;
	}

	public double getVida() {
		return vida;
	}

	public void setVida(double vida) {
		this.vida = vida;
	}

	public List<Point> getCaminoHaciaPersonaje() {
		return caminoHaciaPersonaje;
	}

	public void setCaminoHaciaPersonaje(List<Point> camino) {
		this.caminoHaciaPersonaje = camino;
	}

	public List<MonsterObserver> getObservers() {
		if(observers == null){
			observers = new ArrayList<MonsterObserver>();
		}
		return observers;
	}

	public void setObservers(List<MonsterObserver> observers) {
		this.observers = observers;
	}

	public void setPuntoDeAtaque(Vector2D puntoDeAtaque) {
		this.puntoDeAtaque = puntoDeAtaque;
	}

	public void setPosicionAnterior(Vector2D posicionAnterior) {
		this.posicionAnterior = posicionAnterior;
	}

	private void setPosicionAnterior(double x, double y) {
		this.posicionAnterior.setX(x);
		this.posicionAnterior.setY(y);
	}

	public double getTiempoMuertoDeGolpe() {
		return tiempoMuertoDeGolpe;
	}

	public void setTiempoMuertoDeGolpe(double tiempoMuertoDeGolpe) {
		this.tiempoMuertoDeGolpe = tiempoMuertoDeGolpe;
	}

	public double getTiempoMuertoDeGolpeMax() {
		return this.tiempoMuertoDeGolpeMax;
	}

	public void setTiempoMuertoToMax() {
		this.tiempoMuertoDeGolpe = this.tiempoMuertoDeGolpeMax;
	}

	public Vector2D getPosicionAnterior() {
		return posicionAnterior;
	}

}
