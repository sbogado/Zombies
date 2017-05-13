package zombies.web.persistence;

public interface PersistentPlayer {

	public String getName() ;
	
	public void addExperience(Integer experience);

	public Integer getAim();

	public Integer getHitRecovery();

	public Integer getTotalLife();
	
	public Integer getSpeedRecharge();
	
	public Integer getMovement();

}
