package zombies.web.persistence;

import java.util.logging.Logger;

import zombies.web.observer.MonsterObserver;
import zombies.web.observer.TimeObserver;

public class MissionObserver implements  MonsterObserver, TimeObserver {

	public static final Logger logger = Logger.getLogger(MissionObserver.class.getName());
	
	private PersistentMission mission;
	
	private Integer amountOfZombiesKilled;
	private Double amountOfTimeSurvived;
	
	public MissionObserver(PersistentMission mission){
		setMission(mission);
	}
	
	public void initialize(){
		amountOfZombiesKilled = 0;
		amountOfTimeSurvived =new Double(0);
	}
	
	public Boolean isMissionAcomplished() {
		return isAmountOfTimeToSurviveAcomplished() && isAmountOfZombiesToKillAcomplished();
	}

	private Boolean isAmountOfZombiesToKillAcomplished() {
		return getMission().getAmountOfZombiesToKill() <= amountOfZombiesKilled;
	}

	private Boolean isAmountOfTimeToSurviveAcomplished() {
		return getMission().getAmountOfTimeToSurvive() <= amountOfTimeSurvived;
	}

	public void notifyMonsterKilledToObserver() {
		amountOfZombiesKilled = amountOfZombiesKilled + 1;
	}

	public void notifyTimeElapsedToObserver(double timeElapsed) {
		amountOfTimeSurvived = amountOfTimeSurvived + timeElapsed;
	}

	public PersistentMission getMission() {
		return mission;
	}

	public void setMission(PersistentMission mission) {
		this.mission = mission;
	}

}
