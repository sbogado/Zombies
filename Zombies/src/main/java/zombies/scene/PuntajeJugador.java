package zombies.scene;

import java.awt.Color;
import java.awt.Font;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Label;

public class PuntajeJugador extends GameComponent<ZombiesScene> {

	private double value;
	
	public PuntajeJugador(double x, double y) {		
		super(new Label(new Font("verdana",  Font.BOLD, 10), Color.green, "3"), x, y);
		this.setZ(20);
		this.value = 0;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}
	
	public void blockDistroyed(int points){
		this.setValue(this.getValue() + points);
	}
	
	
	@Override
	public void update(DeltaState deltaState) {
		((Label)this.getAppearance()).setText(Double.toString(this.getValue())); 
		super.update(deltaState);
	} 

}
