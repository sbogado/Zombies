package zombies.web.persistence;

import zombies.web.observer.MissionObserver;

public interface PersistentMission {

	public Boolean isAcomplished(MissionObserver observer);
	
}
