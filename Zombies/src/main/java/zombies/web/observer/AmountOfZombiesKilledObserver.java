package zombies.web.observer;

import java.awt.Color;
import java.awt.Font;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Label;

import zombies.scene.scenes.ZombiesScene;

public class AmountOfZombiesKilledObserver  extends GameComponent<ZombiesScene> implements MonsterObserver {

	private Integer amountOfZombiesKilled;
	
	public AmountOfZombiesKilledObserver(double x, double y) {		
		super(new Label(new Font("verdana",  Font.BOLD, 10), Color.green, "3"), x, y);
		this.setZ(20);
		this.amountOfZombiesKilled = 0;
	}
	
	@Override
	public void update(DeltaState deltaState) {
		((Label)this.getAppearance()).setText("Monstruos Muertos: "+Integer.toString(getAmountOfZombiesKilled())); 
		super.update(deltaState);
	} 
	
	@Override
	public void notifyMonsterKilledToObserver() {
		this.setAmountOfZombiesKilled(getAmountOfZombiesKilled() + 1);
	}

	public Integer getAmountOfZombiesKilled() {
		return amountOfZombiesKilled;
	}

	public void setAmountOfZombiesKilled(Integer amountOfZombiesKilled) {
		this.amountOfZombiesKilled = amountOfZombiesKilled;
	}





}
