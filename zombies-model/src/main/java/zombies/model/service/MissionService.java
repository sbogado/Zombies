package zombies.model.service;

import java.util.List;

import zombies.model.model.Mission;

public interface MissionService extends GenericService<Mission>{

	public List<Mission> findMissionsToShow() throws Exception;
}
