package zombies.scene.components;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Label;
import com.uqbar.vainilla.events.constants.Key;
import com.uqbar.vainilla.sound.Sound;

import ar.edu.unq.games.vainillautils.AnimationRotateMoved;
import ar.edu.unq.games.vainillautils.SpriteMoved;
import ar.edu.unq.games.vainillautils.Vector2D;
import sound.SoundBuilderZombie;
import zombie.Zombies;
import zombie.scene.rules.ChoqueParedRulePersonaje;
import zombie.scene.rules.ColisionRule;
import zombies.scene.scenes.ZombiesScene;
import zombies.web.persistence.PersistentPlayer;
import zombies.zombiescene.armas.Arma;
import zombies.zombiescene.armas.Benelli;
import zombies.zombiescene.armas.Beretta;
import zombies.zombiescene.armas.MP5;
import zombies.zombiescene.personajestates.PersonajeState;
import zombies.zombiescene.personajestates.PersonajeStateQuieto;
import zombiescene.strategies.ControlDelJugador;

public class Personaje extends GameComponent<ZombiesScene> implements Individuo{

	
	private PersistentPlayer player;
	private Arma arma;
	private int currentWeapon = 0;
	private List<Arma> armas = new ArrayList<Arma>();
	private double velocidad;
	private double velocidadInicial;
	private double xInicial;
	private double yInicial;
	private double xMin;
	private double xMax;
	private double yMin;
	private double yMax;
	private Vector2D direccion;
	private PersonajeState state;
	private int vida;
	private double tiempoDeMuerte;
	private Vector2D posicionAnterior;
	private double rotation;	
	private SoundBuilderZombie soundBuilderDisparo;
	private Sound personajeMuriendo;
	private Sound personajeGolpeado1;
	private Sound personajeGolpeado2;
	private Sound personajeGolpeado3;
	
	private ControlDelJugador strategy;
	private ChoqueParedRulePersonaje choqueParedRule;
	private double desplazamientoDePantallaAnteriorX = 0;
	private double desplazamientoDePantallaAnteriorY = 0;
	private double dimensionX;
	private double dimensionY;
	private boolean estaVivo;
	private ColisionRule colisionRule;
	private boolean estaImpactado = false;
	private double tiempoDeRecuperacionDeImpacto = 0.5;
	private AnimationRotateMoved disparandoAnimation;
	private AnimationRotateMoved quietoAnimation;
	private AnimationRotateMoved personajeMuertoAnimation;
	private Label label;

	public Personaje(PersistentPlayer player,AnimationRotateMoved imagenParado, double x, double y, double xMin, double xMax,
			double yMin, double yMax, ControlDelJugador strategy) {
		super(imagenParado, x, y);
		
		this.setPlayer(player);
		this.setLabel(new Label(new Font("verdana",  Font.BOLD, 12), Color.green, "3"));
		this.setZ(1);
		this.setVelocidad(300);
		this.soundBuilderDisparo = new SoundBuilderZombie(this.getClass().getClassLoader().getResourceAsStream("fx_1036.wav"));
		this.personajeMuriendo = new SoundBuilderZombie(this.getClass().getClassLoader().getResourceAsStream("personaje gritando.wav")).getSound();
		this. personajeGolpeado1 = new SoundBuilderZombie(this.getClass().getClassLoader().getResourceAsStream("personaje golpeado1.wav")).getSound();
		this. personajeGolpeado2 = new SoundBuilderZombie(this.getClass().getClassLoader().getResourceAsStream("personaje golpeado2.wav")).getSound();
		this. personajeGolpeado3 = new SoundBuilderZombie(this.getClass().getClassLoader().getResourceAsStream("personaje golpeado3.wav")).getSound();
		
		SpriteMoved[] sprites0 = {SpriteMoved.fromImage("personajeMuerto.png")};
		this.personajeMuertoAnimation = new AnimationRotateMoved(0.1, sprites0);
		
		SpriteMoved[] sprites1 = {SpriteMoved.fromImage("personajeDisparando1.png"),SpriteMoved.fromImage("personajeDisparando2.png"),SpriteMoved.fromImage("personajeDisparando3.png")};
		this.disparandoAnimation= new AnimationRotateMoved(.01, sprites1);
		
		SpriteMoved[] sprites2 = {SpriteMoved.fromImage("personajeQuieto.png")};
		this.quietoAnimation= new AnimationRotateMoved(.01, sprites2);
	
		
		this.xInicial = x;
		this.yInicial = y;
		this.velocidadInicial = 500;
		this.dimensionX = xMax;
		this.dimensionY = yMax;
		this.strategy = strategy;
		this.direccion = new Vector2D(0,-1);
		this.state = new PersonajeStateQuieto(this);
		this.vida= getPlayer().getTotalLife();
		this.initRules();
		this.rotation = 0;
		this.estaVivo = true;
		this.tiempoDeMuerte = 5;
		this.arma = new Beretta(this);
		this.addArma(this.arma);
		this.addArma(new MP5(this));
		this.addArma(new Benelli(this));
		
		
	}

	private void addArma(Arma arma) {
		this.armas.add(arma);
	}

	public void setNextWeapon() {
		if(this.armas.size() > this.currentWeapon + 1 ){
			this.currentWeapon = this.currentWeapon + 1;
			this.arma = this.armas.get(this.currentWeapon);
		}
	}

	public void setPreviousWeapon() {
		if(this.currentWeapon - 1 >= 0){
			this.currentWeapon = this.currentWeapon -1 ;
			this.arma = this.armas.get(this.currentWeapon);
		}
	}
	
	
	public void sonidoGolpeado(){
		double random = Math.random();
		int volumen = 5;
		
		if(random < 0.33){
			this.personajeGolpeado1.play(volumen);
		}
		else{
			if(random < 0.66){
				this.personajeGolpeado2.play(volumen);
			}
			else{
				this.personajeGolpeado3.play(volumen);
			}
		}
	}
	
	public void setDireccion(Vector2D direccion) {
		this.direccion = direccion;
	}


	private void initRules() {
		this.choqueParedRule = new ChoqueParedRulePersonaje(this);
		this.colisionRule = new ColisionRule(this);
	}


	public int getVida() {
		return vida;
	}
	
	public void recibirImpacto(int daño){
		this.vida= this.vida - daño;
		
		if(this.vida <= 0){
			ZombiesScene scene = this.getScene();
			this.setEstaVivo(false);
			this.personajeMuriendo.play(7);
			scene.removeComponent(this);
			this.setZ(-4);
			scene.addComponent(this);
			this.setAppearance(this.getPersonajeMuerto());
			
		}
		else{
			this.estaImpactado= true;
			this.sonidoGolpeado();
		}
		
	}
		
		
	public void setAppearanceRotated(AnimationRotateMoved image,double rotation){
		image.rotate(rotation);
		super.setAppearance(image);
	}



	public AnimationRotateMoved getPersonajeMuerto() {
		return this.personajeMuertoAnimation;
	}


	public void izquierda(double delta) {
		this.setX(this.getX() - getVelocidad() * delta );
		this.getScene().setDesplazamientoDePantallaX(this.getScene().getDesplazamientoDePantallaX()+ getVelocidad() * delta );
	}

	public void derecha(double delta) {
		this.setX(this.getX() + getVelocidad() * delta);
		this.getScene().setDesplazamientoDePantallaX(this.getScene().getDesplazamientoDePantallaX() - getVelocidad() * delta);
	}

	public void arriba(double delta) {
		this.setY(this.getY() - getVelocidad() * delta);
		this.getScene().setDesplazamientoDePantallaY(this.getScene().getDesplazamientoDePantallaY() + getVelocidad() * delta);
	}

	public void abajo(double delta) {
		this.setY(this.getY() + getVelocidad() * delta);
		this.getScene().setDesplazamientoDePantallaY(this.getScene().getDesplazamientoDePantallaY() - getVelocidad() * delta);
	}

	public void disparar(DeltaState delta) {
		this.state.modoDisparo(delta);
		this.state.accionar(delta);
	}

	public void permanecerQuieto(DeltaState delta) {
		this.state.modoQuieto(delta);
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

	public void centrar() {
		this.setVelocidad(velocidadInicial);
		this.setX(xInicial);
		this.setY(yInicial);
	}

	public ControlDelJugador getStrategy() {
		return strategy;
	}

	public void setStrategy(ControlDelJugador strategy) {
		this.strategy = strategy;
	}

	@Override
	public void update(DeltaState deltaState) {
		
		if(this.getEstaVivo()){
			if(!this.estaImpactado){

				this.guardarPosicionAnterior();
				this.strategy.update(this, this.getScene(), deltaState);
				
				for(Pared pared : this.getScene().getParedes()){
						this.choqueParedRule.checkChoquePared(pared);
					}
				
				boolean canFix = false;
				Barricada barricadaToFix = null;
				for(Barricada barricada : this.getScene().getBarricadas()){
					if(this.colisionRule.mustApplyFixBarricade(this,barricada,this.getScene())){
						canFix = true;
						barricadaToFix = barricada;
						break;
					}
				}	
				
				if(canFix && barricadaToFix != null && barricadaToFix.getVida() < 100){
					this.getScene().addPressFToFix();
					if(deltaState.isKeyPressed(Key.F)){
						barricadaToFix.fix();
					}
				}
				else{
					this.getScene().removePressFToFix();
				}
				
				
				for(Barricada barricada : this.getScene().getBarricadas()){
					this.choqueParedRule.checkChoquePared(barricada);
				}

			}
			else{
				this.tiempoDeRecuperacionDeImpacto = this.tiempoDeRecuperacionDeImpacto - deltaState.getDelta();
				if(this.tiempoDeRecuperacionDeImpacto <= 0){
					this.estaImpactado = false;
					this.setTiempoDeRecuperacionDeImpacto();
				}
			}
			
			for(GolpeDeMonstruo golpe : this.getScene().getGolpesDeMonstruos()){
					if(this.colisionRule.mustApplyCirculoPersonaje(golpe,this)){
						this.colisionRule.applyPersonDamage(this,golpe);
					}
			}
			if(this.getArma().estaRecargando()){
				this.getArma().restarTiempoDeRecarga(deltaState.getDelta());
			}
			
		}
		else{
			
				die(deltaState);
		}
			
		super.update(deltaState);
			
	}
	
	public void recargar(){
		this.getArma().recargar();
	}

	public void die(DeltaState deltaState) {
		this.tiempoDeMuerte=  (this.tiempoDeMuerte - deltaState.getDelta());
		if(tiempoDeMuerte <= 0){
			this.destroy();
		}
	}

	public void endGame(){
		((Zombies)this.getScene().getGame()).buildEndScene(false);
	}
	
	public void destroy(){
		this.endGame();
		super.destroy();
	}
	
	private void setTiempoDeRecuperacionDeImpacto() {
		this.tiempoDeRecuperacionDeImpacto = 0.1;
	}

	public boolean getEstaVivo() {
		return this.estaVivo;
	}
	
	public void setEstaVivo(boolean estaVivo) {
		this.estaVivo = estaVivo;
	}

	public void render(Graphics2D graphics){
		double x = this.getX();
		double y = this.getY();
		
		this.setX(this.getDimensionX()/2);
		this.setY(this.getDimensionY()/2);

		super.render(graphics);
//		this.label.setText("x: "+x+"\n"+"y: "+y);
//		this.label.render(this, graphics);
//		graphics.fillRect((int)this.getX()-13, (int)this.getY()-16, 20, 25);
//		graphics.fillRect((int)this.getX()-16, (int)this.getY()-13, 25, 20);

		this.setX(x);
		this.setY(y);
		

		
	}


	public void rotate(double actualRotation) {

		this.rotation = actualRotation;
		((AnimationRotateMoved)this.getAppearance()).rotate( actualRotation);


		this.direccion = new Vector2D(0,-1);
		this.direccion.rotate(actualRotation);
	}
	
	public double getDimensionY() {
		return this.dimensionY;
	}

	public double getDimensionX() {
		return this.dimensionX;
	}
	
	public double getxMin() {
		return xMin;
	}

	public void setxMin(double xMin) {
		this.xMin = xMin;
	}

	public double getxMax() {
		return xMax;
	}

	public void setxMax(double xMax) {
		this.xMax = xMax;
	}

	public double getyMin() {
		return yMin;
	}

	public void setyMin(double yMin) {
		this.yMin = yMin;
	}

	public double getyMax() {
		return yMax;
	}

	public void setyMax(double yMax) {
		this.yMax = yMax;
	}

	public double getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(double velocidad) {
		this.velocidad = velocidad;
	}

	public Vector2D getDireccion() {
		return direccion;
	}

	public PersonajeState getPersonajeState() {
		return this.state;		
	}
	
	public void setPersonajeState(PersonajeState personajeState) {
		this.state = personajeState;		
	}


	public Vector2D getPosicionAnterior() {
		return posicionAnterior;
	}


	public void guardarPosicionAnterior() {
		this.posicionAnterior = new Vector2D(this.getX(),this.getY());
		this.desplazamientoDePantallaAnteriorX = this.getScene().getDesplazamientoDePantallaX();
		this.desplazamientoDePantallaAnteriorY = this.getScene().getDesplazamientoDePantallaY();
	}

	@Override
	public void setXBack() {
		this.setX(this.posicionAnterior.getX());
		this.getScene().setDesplazamientoDePantallaX(this.desplazamientoDePantallaAnteriorX);
	}

	@Override
	public void setYBack() {
		this.setY(this.posicionAnterior.getY());
		this.getScene().setDesplazamientoDePantallaY(this.desplazamientoDePantallaAnteriorY);
	}

	public int getWidth() {
		return 40;
	}

	public double getRotation() {
		return this.rotation;
	}

	public SoundBuilderZombie getSoundBuilderDisparo() {
		return soundBuilderDisparo;
	}

	public void setSoundBuilderDisparo(SoundBuilderZombie soundBuilderDisparo) {
		this.soundBuilderDisparo = soundBuilderDisparo;
	}

	public Arma getArma() {
		return this.arma;
	}

	public AnimationRotateMoved getPersonajeDisparandoAnimation() {
		return this.disparandoAnimation;
	}

	public AnimationRotateMoved getPersonajeQuietoAnimation() {
		return this.quietoAnimation;
	}

	public double getPunteria() {
		return getPlayer().getAim();
	}

	public Label getLabel() {
		return label;
	}

	public void setLabel(Label label) {
		this.label = label;
	}

	public PersistentPlayer getPlayer() {
		return player;
	}

	public void setPlayer(PersistentPlayer player) {
		this.player = player;
	}

	
//	public void playDeadSound(){
//        try {            
//            Clip sonido = AudioSystem.getClip();
//            sonido.open(AudioSystem.getAudioInputStream(new File("fx_1037.wav")));
//            sonido.start();
//            while (sonido.isRunning())
//                Thread.sleep(1000);
//            sonido.close();
//        } catch (Exception e) {
//            System.out.println("el sonido tiene un error" + e);
//        }
//	}
}
