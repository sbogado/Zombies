package zombies.web.persistence;

public interface PersistentPlayer {

	public String getName() ;

	public void setName(String name);
	
	public Long getId();

	public void setId(Long id);
	
	public Integer getActualLevel();

	public void setActualLevel(Integer actualLevel);

	public Integer getExperience();

	public void setExperience(Integer experience);

	public Integer getScene();

	public void setScene(Integer escene);

	public Integer getAim();

	public void setAim(Integer aim);

	public Double getHitRecovery();

	public void setHitRecovery(Double hitRecovery);

	public Integer getTotalLife();

	public void setTotalLife(Integer totalLife);

	
}
