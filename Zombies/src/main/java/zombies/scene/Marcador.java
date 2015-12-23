package zombies.scene;

import java.awt.Color;
import java.awt.Font;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Appearance;
import com.uqbar.vainilla.appearances.Label;

public class Marcador extends GameComponent<ZombiesScene> {

	private int value;
	
	public Marcador(double x, double y) {		
		super(new Label(new Font("verdana",  Font.BOLD, 12), Color.BLACK, "3"), x, y);
		this.value = 3;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	public void gol() {
		this.setValue(this.getValue() - 1);
	}
	
	
	@Override
	public void update(DeltaState deltaState) {
		((Label)this.getAppearance()).setText("Vidas: "+Integer.toString(this.getValue())); 
		super.update(deltaState);
	}
	
//	public boolean isBetter(Marcador other) {
//		return this.value > other.value;
//	}

	public boolean finJuego() {
		//TODO terminar con todos los bloques para ganar 
		return this.value < 0;
	}
	

}
