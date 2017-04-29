package zombies.scene.components;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import zombie.Zombies;
import zombie.scene.rules.ColisionRule;
import zombies.scene.scenes.ZombiesScene;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Rectangle;

public class Pared extends ParteDelMapa{

	private int width;
	private int height;
	
	private double x;
	private double y;
	
	private ZombiesScene scene;
	private ColisionRule colisionRule;	
	
	
	public Pared(int width,int height,double x, double y,ZombiesScene scene){
		super(new Rectangle(Color.BLACK,0,0), x, y);
		this.height = height;
		this.width = width;
		this.setX(x);
		this.setY(y);
		this.scene = scene;
		this.initRules();
		this.setZ(-4);
		this.setDistanciasDeParedEnGrafo();
	}
	protected void initRules() {
	 	this.setColisionRule( new ColisionRule(this));
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
	

	public int getWidth() {
		return this.width;
	}

	public int getHeight() {
		return this.height;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public ZombiesScene getScene() {
		return scene;
	}

	public void setScene(ZombiesScene scene) {
		this.scene = scene;
	}
	
	public ColisionRule getColisionRule() {
		return this.colisionRule;
	}
	
	public void setColisionRule(ColisionRule colisionRule) {
		this.colisionRule = colisionRule;
	}
	@Override
	public int getPesoEnGrafo() {
		return 2000;
	}
	
}
