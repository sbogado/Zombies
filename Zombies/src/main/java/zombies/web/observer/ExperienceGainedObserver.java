package zombies.web.observer;

import zombies.scene.monstruos.Monstruo;

public class ExperienceGainedObserver implements MonsterKilledObserver{

	private Integer experienceGained;
	
	public ExperienceGainedObserver() {
		setExperienceGained(0);
	}
	
	@Override
	public void notifyMonsterKilledToObserver(Monstruo monstruo) {
		addExperienceGained(monstruo.getExperience());
	}

	public Integer getExperienceGained() {
		return experienceGained;
	}

	public void setExperienceGained(Integer experienceGained) {
		this.experienceGained = experienceGained;
	}
	
	public void addExperienceGained(Integer exp){
		setExperienceGained(getExperienceGained() + exp);
	}

}
