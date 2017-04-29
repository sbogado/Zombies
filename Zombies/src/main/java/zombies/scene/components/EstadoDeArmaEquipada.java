package zombies.scene.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;

import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Label;

import zombies.scene.scenes.ZombiesScene;

public class EstadoDeArmaEquipada extends GameComponent<ZombiesScene>{

	
	private Personaje personaje;
	private Label datosDeBalas;

	public EstadoDeArmaEquipada(Personaje personaje,Dimension dimension){
		super(dimension.getWidth()/2 +200,dimension.getHeight()-30);
		this.personaje = personaje;
		this.datosDeBalas = new Label(new Font("verdana",  Font.BOLD, 10), Color.green, "3");
		this.setZ(10);
	}
	
	public void render(Graphics2D graphics){
		double x = this.getX();
		double y = this.getY();
		
		this.personaje.getArma().getAnimationScala().render(this, graphics);
		
		String cantidadDeBalasNoCargadas = ""+personaje.getArma().getCantidadDeBalasNoCargadas();
		if(personaje.getArma().getCantidadDeBalasNoCargadas() == -1){
			cantidadDeBalasNoCargadas = "∞";
		}
		this.setX(this.getX()+50);
		datosDeBalas.setText(" "+this.personaje.getArma().getCantidadDeBalas()+"-"+this.personaje.getArma().getCantidadBalasMax()+"/"+cantidadDeBalasNoCargadas);
		datosDeBalas.render(this, graphics);
		this.setX(x);
		this.setY(y);
		
	}

}
