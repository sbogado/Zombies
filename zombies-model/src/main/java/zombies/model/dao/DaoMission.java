package zombies.model.dao;

import java.util.List;

import zombies.model.model.Mission;

public interface DaoMission extends DaoGeneric<Mission> {

	public List<Mission> findMissionsToShow() throws Exception;
}
