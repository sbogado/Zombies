package zombies.scene.components;

import java.awt.Color;
import java.awt.Graphics2D;

import zombie.scene.rules.ColisionRule;
import zombies.scene.scenes.ZombiesScene;

import com.uqbar.vainilla.appearances.Rectangle;

public class Camino extends ParteDelMapa{

	private int width;
	private int height;
	
	private double x;
	private double y;
	
	private ZombiesScene scene;
	private ColisionRule colisionRule;	
	
	
	public Camino(int width,int height,double x, double y,ZombiesScene scene){
		super(new Rectangle(Color.GREEN,width,height), x, y);
		this.height = height;
		this.width = width;
		this.setX(x);
		this.setY(y);
		this.scene = scene;
		this.setDistanciasDeParedEnGrafo();
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
	
	@Override
	public int getPesoEnGrafo() {
		return 1;
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
}
