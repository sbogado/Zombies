package zombies.web.persistence;

public interface PersistentMission {

	public Integer getAmountOfZombiesToKill();

	public void setAmountOfZombiesToKill(Integer amountOfZombiesToKill);

	public Double getAmountOfTimeToSurvive();

	public void setAmountOfTimeToSurvive(Double amountOfTimeToSurvive);
	
}
