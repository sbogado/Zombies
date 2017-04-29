package zombies.scene.components;

import java.awt.Color;
import java.awt.Graphics2D;

import zombie.scene.rules.ColisionRule;
import zombie.scene.rules.DesplazamientoLibreRule;
import zombies.scene.monstruos.Monstruo;
import zombies.scene.scenes.ZombiesScene;
import zombies.scene.zombiesappearences.BalaAppearences;
import ar.edu.unq.games.vainillautils.AnimationRotate;
import ar.edu.unq.games.vainillautils.Vector2D;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Circle;
import com.uqbar.vainilla.appearances.Sprite;

public class Bala extends GameComponent<ZombiesScene> {

	private double danio;
	private Vector2D direccion;
	private double velocidad;
	private double xInicial;
	private double yInicial;
	private Vector2D direccionInicial;
	private double velocidadInicial;
	private double velocidadStep = 0.1;
	private int radio;
	private DesplazamientoLibreRule dezplazamientoRule;
	private boolean exploto = false;
	private double tiempoDeExplosion = 0.3;
	private double rotation;
	private AnimationRotate estallandoAnimation;
	private AnimationRotate estallandoParedAnimation;
	private boolean efectoDeRecorridoNoFuePintado = true;
	private ColisionRule colisionRule;
	
	public Bala( double xInicial, double yInicial,
			Vector2D direccionInicial,double danio,double rotation) {
		super(new Circle(Color.blue, 1), xInicial, yInicial);
		this.setZ(-1);
	
		this.estallandoAnimation = BalaAppearences.INSTANCE.getEstallandoAnimation();
		this.estallandoParedAnimation = BalaAppearences.INSTANCE.getEstallandoParedAnimation();
		
		this.xInicial = xInicial;
		this.yInicial = yInicial;
		
		this.direccion = direccionInicial.asVersor();
		this.direccionInicial = this.direccion;
		this.velocidad = 5000;
		this.danio = danio;
		this.rotation = rotation;
		this.initRules();
	}

	private void initRules() {
		this.dezplazamientoRule = new DesplazamientoLibreRule();
		this.setColisionRule( new ColisionRule(this, "fx_0051.wav"));
	}

	private void setColisionRule(ColisionRule colisionRule) {
		this.colisionRule = colisionRule;
	}

	public Vector2D nuevaPosicion(double delta,int velocidad ) {
		return (this.direccion.producto(velocidad * delta)
				.suma(new Vector2D(this.getX(), this.getY())));
	}
	
	public Vector2D nuevaPosicion(double delta) {
		return (this.direccion.producto(velocidad * delta)
				.suma(new Vector2D(this.getX(), this.getY())));
	}

	@Override
	public void update(DeltaState deltaState) {
		
		
		for (Monstruo monstruo: getScene().getMonstruos()) {
			if(!this.getExploto()){
				if(!monstruo.isDestroyPending() && monstruo.getEstaVivo()){
					this.getColisionRule().mustApplyBalaMonstruo(this, monstruo, deltaState);	
				}
			}
			else{
				break;
			}
		}
		
		for (Pared pared: getScene().getParedes()) {
			if(!this.getExploto()){
				this.getColisionRule().mustApplyBalaPared(this,pared,deltaState); 

			}
			else{
				break;
			}
		}

				
		if(!this.getExploto()){
			this.getDesplazamientoLibreRule().apply(this, this.nuevaPosicion(deltaState.getDelta()), this.getScene());	
		}
		else{
			this.setTiempoDeExplosion(this.getTiempoDeExplosion() - deltaState.getDelta());
			
			if(this.getTiempoDeExplosion() <= 0){
				this.destroy();
			}
			
		}
		
		if(this.getX() > 3000 || this.getX() < -2000 || this.getY() > 3000 || this.getY() < -2000){
			this.destroy();
		}
		
		super.update(deltaState);
	}

	
	private ColisionRule getColisionRule() {
		return this.colisionRule;
	}

	public void destroy() {
		super.destroy();
		this.getScene().getBalas().remove(this);
	}
	
	public void render(Graphics2D graphics){
		double x = this.getX();
		double y = this.getY();
		
		this.setX(this.getX() + this.getScene().getDesplazamientoDePantallaX());
		this.setY(this.getY() + this.getScene().getDesplazamientoDePantallaY());
		
		
		if( this.efectoDeRecorridoNoFuePintado ){
			double random = Math.random();
			if(random < 0.5){	
				this.efectoDeRecorridoNoFuePintado = false;
				graphics.setColor(Color.yellow);
				graphics.drawLine((int)this.xInicial+ (int) this.getScene().getDesplazamientoDePantallaX(), (int)this.yInicial + (int)this.getScene().getDesplazamientoDePantallaY(),(int) this.getX(), (int)this.getY());
			}
		}
		super.render(graphics);
		
		this.setX(x);
		this.setY(y);

	}
	
	public void centrar() {
		this.setX(this.xInicial);
		this.setY(this.yInicial);
		this.direccion = this.direccionInicial;
		this.velocidad = this.velocidadInicial;
	}

	public void setDireccion(Vector2D vector2d) {
		this.direccion = vector2d.asVersor();
	}

	public Vector2D getDireccion() {
		return this.direccion;
	}

	public void masRapido() {
		this.velocidad = this.velocidad + this.getVelocidadStep();
	}

	public double getVelocidadStep() {
		return velocidadStep;
	}

	public void setVelocidadStep(double velocidadStep) {
		this.velocidadStep = velocidadStep;
	}

	public int getRadio() {
		return radio;
	}

	public void setRadio(int radio) {
		this.radio = radio;
	}
	
	private DesplazamientoLibreRule getDesplazamientoLibreRule() {
		return this.dezplazamientoRule;
	}

	public void explotar(Vector2D posicion) {
		this.setX(posicion.getX());
		this.setY(posicion.getY());
		this.setExploto(true);
	}
	
	
	public void explotarZombie(Vector2D posicion) {
		this.explotar(posicion);
		AnimationRotate animation = this.getEstallandoZombieAnimation();
		animation.rotate(this.rotation);
		this.setAppearance(animation);
	}
	

	public void explotarPared(Vector2D posicion) {
		this.explotar(posicion);
		AnimationRotate animation = this.getEstallandoParedAnimation();
//		this.setX(this.getX()-this.getAppearance().getWidth()/2);
//		this.setY(this.getY()-this.getAppearance().getHeight()/2);
		this.setAppearance(animation);
	}
	

	public void setTiempoDeExplosion(double tiempoDeExplosion) {
		this.tiempoDeExplosion = tiempoDeExplosion;
	}

	public double getTiempoDeExplosion() {
		return this.tiempoDeExplosion;
	}
	
	private void setExploto(boolean booleano) {
		this.exploto = booleano;
	}

	public boolean getExploto() {
		return this.exploto;
	}
	
	private AnimationRotate getEstallandoZombieAnimation() {	
		return this.estallandoAnimation;
	}

	
	private AnimationRotate getEstallandoParedAnimation() {	
		return this.estallandoParedAnimation;
	}

	public double getDanio() {
		return danio;
	}

	public void setDanio(double danio) {
		this.danio = danio;
	}

	public double getVelocidad() {
		return velocidad;
	}
	
	
}
