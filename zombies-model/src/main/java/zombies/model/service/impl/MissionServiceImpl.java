package zombies.model.service.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import zombies.model.dao.DaoGeneric;
import zombies.model.dao.DaoMission;
import zombies.model.model.Mission;
import zombies.model.service.MissionService;

@Service
public class MissionServiceImpl extends GenericABMService<Mission> implements Serializable,MissionService {

	
	private static final long serialVersionUID = 3804372018107282555L;
	
	@Autowired
	DaoMission dao;

	@Override
	public DaoGeneric<Mission> getDao() {
		return dao;
	}

}
