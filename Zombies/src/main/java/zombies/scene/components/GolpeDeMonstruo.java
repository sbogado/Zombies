package zombies.scene.components;

import java.awt.Color;
import java.awt.Graphics2D;

import zombie.scene.rules.ColisionRule;
import zombie.scene.rules.DesplazamientoLibreRule;
import zombies.scene.monstruos.Monstruo;
import zombies.scene.scenes.ZombiesScene;
import ar.edu.unq.games.vainillautils.Vector2D;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Circle;

public class GolpeDeMonstruo extends GameComponent<ZombiesScene>{

	private double tiempoDeVida;
	private int danio;
	private Vector2D direccion;
	private double tiempoMuertoDeGolpe;
	private DesplazamientoLibreRule dezplazamientoRule;
	private boolean notGolpea;
	private ZombiesScene scene;
	private Monstruo monstruo;
	
	public GolpeDeMonstruo(Monstruo monstruo,ZombiesScene scene){
		super(new Circle(Color.red, 1), monstruo.getX(),monstruo.getY());
		this.monstruo = monstruo;
		this.tiempoDeVida = 0;
		this.setDanio(monstruo.getDanio());
		this.direccion = new Vector2D(monstruo.getDireccion().getX(),monstruo.getDireccion().getY());
		this.tiempoMuertoDeGolpe =monstruo.getTiempoMuertoDeGolpeMax();
		this.notGolpea = true;
		this.scene = scene;
		this.setZ(5);
		this.initRules();
	}
	
	public void update(DeltaState deltaState) {
	
		if(this.tiempoDeVida <= this.tiempoMuertoDeGolpe){
			if(this.notGolpea()){
				if(this.dezplazamientoRule.mustApply(this, this.nuevaPosicion(deltaState), this.getScene())) {
					this.dezplazamientoRule.apply(this, this.nuevaPosicion(deltaState), this.getScene());	
				}
				this.tiempoDeVida = this.tiempoDeVida + deltaState.getDelta()*4;
			}
			else{
				this.destroy();
			}	
		}
		else{
			this.destroy();
		}
		super.update(deltaState);
	}
	
	public boolean notGolpea() {
		return this.notGolpea;
	}
	
	public boolean setGolpea() {
		return this.notGolpea = false;
	}
		
	public void destroy() {
		super.destroy();
		this.scene.getGolpesDeMonstruos().remove(this);
	}
		
	private void initRules() {
		this.dezplazamientoRule = new DesplazamientoLibreRule();
	}
	
	public Vector2D nuevaPosicion(DeltaState deltaState) {
		return (this.direccion.producto((100/this.tiempoMuertoDeGolpe) * deltaState.getDelta())
				.suma(new Vector2D(this.getX(), this.getY())));
	}
	
	
	public void render(Graphics2D graphics){
		double x = this.getX();
		double y = this.getY();
		
		this.setX(this.getX() + this.getScene().getDesplazamientoDePantallaX());
		this.setY(this.getY() + this.getScene().getDesplazamientoDePantallaY());
		
		super.render(graphics);
		
		this.setX(x);
		this.setY(y);

	}

	public Monstruo getMonstruo() {
		return this.monstruo;
	}

	public int getDanio() {
		return danio;
	}

	public void setDanio(int danio) {
		this.danio = danio;
	}
}
