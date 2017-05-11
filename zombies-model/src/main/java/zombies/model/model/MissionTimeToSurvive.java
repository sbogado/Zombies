package zombies.model.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import zombies.web.observer.MissionObserver;

@Entity
@Table(name = "MISSION_TIME_TO_SURVIVE")
public class MissionTimeToSurvive extends Mission implements Serializable {

	private static final long serialVersionUID = 8358144321052645353L;

	@Column(name = "MISSION_AMOUNT_OF_TIME_TO_SURVIVE")
	private Double amountOfTimeToSurvive;

	@Override
	public Boolean isAcomplished(MissionObserver observer) {
		return getAmountOfTimeToSurvive() <= observer.getAmountOfTimeSurvived();
	}
	
	/**********************************************************************************************
	 * GETTERS AND SETTERS
	 *********************************************************************************************/
	
	public Double getAmountOfTimeToSurvive() {
		return amountOfTimeToSurvive;
	}

	public void setAmountOfTimeToSurvive(Double amountOfTimeToSurvive) {
		this.amountOfTimeToSurvive = amountOfTimeToSurvive;
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
		MissionTimeToSurvive other = (MissionTimeToSurvive) obj;
		if (getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!getId().equals(other.getId()))
			return false;
		return true;
	}
	
}
