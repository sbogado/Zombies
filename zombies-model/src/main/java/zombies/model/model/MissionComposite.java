package zombies.model.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import zombies.web.observer.MissionObserver;

@Entity
@Table(name = "MISSION_COMPOSITE")
public class MissionComposite extends Mission implements Serializable {

	private static final long serialVersionUID = 5765325453269064985L;

	@ManyToMany(cascade=CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinTable(name = "COMPOSITE_MISSION_SIMPLE_MISSION", joinColumns = @JoinColumn(name = "MISSION_COMPOSITE_ID"), inverseJoinColumns = @JoinColumn(name = "MISSION_ID"))
	List<Mission> missions;
	
	@Override
	public Boolean isAcomplished(MissionObserver observer) {
		Boolean isAccomplished = true;
		Iterator<Mission> missionsIterator = getMissions().iterator();
		
		if(canCheckNextMission(isAccomplished, missionsIterator)){
			isAccomplished = checkMission(observer, isAccomplished, missionsIterator);
		}
		
		while(canCheckNextMission(isAccomplished, missionsIterator)){
			isAccomplished = checkMission(observer, isAccomplished, missionsIterator);
		}
		
		return isAccomplished;
	}

	private Boolean checkMission(MissionObserver observer, Boolean isAccomplished, Iterator<Mission> missionsIterator) {
		return  isAccomplished && missionsIterator.next().isAcomplished(observer);
	}

	private boolean canCheckNextMission(Boolean isAccomplished, Iterator<Mission> missionsIterator) {
		return isAccomplished && missionsIterator.hasNext();
	}
	
	/**********************************************************************************************
	 * GETTERS AND SETTERS
	 *********************************************************************************************/
	
	public List<Mission> getMissions() {
		if(missions == null){
			missions = new ArrayList<Mission>();
		}
		return missions;
	}

	public void setMissions(List<Mission> missions) {
		this.missions = missions;
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
		MissionComposite other = (MissionComposite) obj;
		if (getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!getId().equals(other.getId()))
			return false;
		return true;
	}
}
