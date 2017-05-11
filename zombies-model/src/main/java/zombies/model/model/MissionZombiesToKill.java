package zombies.model.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import zombies.web.observer.MissionObserver;

@Entity
@Table(name = "MISSION_ZOMBIES_TO_KILL")
public class MissionZombiesToKill extends Mission implements Serializable{

	private static final long serialVersionUID = -2245584987535675976L;

	@Column(name = "MISSION_AMOUNT_OF_ZOMBIES_TO_KILL")
	private Integer amountOfZombiesToKill;

	@Override
	public Boolean isAcomplished(MissionObserver observer) {
		return getAmountOfZombiesToKill() <= observer.getAmountOfZombiesKilled();
	}
	
	/**********************************************************************************************
	 * GETTERS AND SETTERS
	 *********************************************************************************************/
	
	public Integer getAmountOfZombiesToKill() {
		return amountOfZombiesToKill;
	}

	public void setAmountOfZombiesToKill(Integer amountOfZombiesToKill) {
		this.amountOfZombiesToKill = amountOfZombiesToKill;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MissionZombiesToKill other = (MissionZombiesToKill ) obj;
		if (getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!getId().equals(other.getId()))
			return false;
		return true;
	}


}
