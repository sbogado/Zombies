package zombies.web.observer;

import zombies.scene.monstruos.Monstruo;

public class MissionObserver implements  MonsterKilledObserver, TimeObserver {

	private Integer amountOfZombiesKilled;
	private Double amountOfTimeSurvived;
	
	
	public MissionObserver(){
		amountOfZombiesKilled = 0;
		amountOfTimeSurvived =new Double(0);
	}

	public void notifyMonsterKilledToObserver(Monstruo monstruo) {
		amountOfZombiesKilled = amountOfZombiesKilled + 1;
	}

	public void notifyTimeElapsedToObserver(double timeElapsed) {
		amountOfTimeSurvived = amountOfTimeSurvived + timeElapsed;
	}

	/**********************************************************************************************
	 * GETTERS AND SETTERS
	 *********************************************************************************************/
	
	public Integer getAmountOfZombiesKilled() {
		return amountOfZombiesKilled;
	}

	public void setAmountOfZombiesKilled(Integer amountOfZombiesKilled) {
		this.amountOfZombiesKilled = amountOfZombiesKilled;
	}

	public Double getAmountOfTimeSurvived() {
		return amountOfTimeSurvived;
	}

	public void setAmountOfTimeSurvived(Double amountOfTimeSurvived) {
		this.amountOfTimeSurvived = amountOfTimeSurvived;
	}

}
