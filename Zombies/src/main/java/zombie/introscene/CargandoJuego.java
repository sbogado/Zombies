package zombie.introscene;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Label;

import zombies.scene.components.Personaje;

public class CargandoJuego extends GameComponent<IntroScene>{
	
	private Label components;
	private double tiempoDeEsperaParaDestroy = 0;

	public CargandoJuego(Dimension dimension){
		super(new Label(new Font("verdana",  Font.BOLD, 10), Color.white.darker().darker(), "Cargando: "),100,dimension.getHeight()-100);
		this.components = new Label(new Font("verdana",  Font.BOLD, 10), Color.white.darker().darker(), "3");
		this.setZ(10);
	}
	
	public void render(Graphics2D graphics){
		double x = this.getX();
		double y = this.getY();
		
		super.render(graphics);
		graphics.setColor(Color.red);

		graphics.fillRect((int) this.getX()+90, (int) this.getY()+5,this.getScene().getPorcentajeDeCargado()*4, 5);
		this.setX(this.getX() +90);
		this.setY(this.getY() -10);
		components.setText(this.getScene().currentComponentLoading());
		components.render(this, graphics);
		this.setX(x);
		this.setY(y);
		
	}
	
	public void update(DeltaState deltaState) {	
		if(this.getScene().getPorcentajeDeCargado() >= 100){ 
			if(this.tiempoDeEsperaParaDestroy  > 5){
				this.destroy();
			}
			else{
				if(deltaState.getDelta() < 0.1){
					this.tiempoDeEsperaParaDestroy =  (this.tiempoDeEsperaParaDestroy + deltaState.getDelta());
				}
			}
		}
		else{
			this.getScene().getNextScene().getCargandoState().cargarScenario();
			this.getScene().getNextScene().getCargandoState().next();
		}
		
		super.update(deltaState);
	}	
	
	public void destroy(){
		super.destroy();
		this.getScene().goToNextScene();
	}

}
