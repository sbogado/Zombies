package zombies.scene;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Label;
import com.uqbar.vainilla.appearances.Sprite;

public class BarraDeVida extends GameComponent<ZombiesScene>{

	
	private Personaje personaje;
	private Label cantidadDeVida;

	public BarraDeVida(Personaje personaje,Dimension dimension){
		super(new Label(new Font("verdana",  Font.BOLD, 10), Color.green, "Vida:"),100,dimension.getHeight()-30);
		this.personaje = personaje;
		this.cantidadDeVida = new Label(new Font("verdana",  Font.BOLD, 10), Color.green, "3");
		this.setZ(10);
	}
	
	public void render(Graphics2D graphics){
		double x = this.getX();
		double y = this.getY();
		
		super.render(graphics);
		if(this.personaje.getVida() > 50){
			graphics.setColor(Color.GREEN);
		}
		else{
			if(this.personaje.getVida() > 30){
				graphics.setColor(Color.orange);
			}
			else{
				graphics.setColor(Color.red);
			}
		}
		graphics.fillRect((int) this.getX()+30, (int) this.getY()+5,this.personaje.getVida(), 5);
		this.setX(this.getX() +40);
		this.setY(this.getY() -10);
		cantidadDeVida.setText(Integer.toString(this.personaje.getVida()));
		cantidadDeVida.render(this, graphics);
		this.setX(x);
		this.setY(y);
		
	}
}
