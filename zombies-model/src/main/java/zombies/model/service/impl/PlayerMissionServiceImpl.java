package zombies.model.service.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import zombies.model.dao.DaoGeneric;
import zombies.model.dao.DaoPlayerMission;
import zombies.model.model.PlayerMission;
import zombies.model.service.PlayerMissionService;

@Service
public class PlayerMissionServiceImpl extends GenericABMService<PlayerMission> implements Serializable,PlayerMissionService{

	private static final long serialVersionUID = -6064284880075436867L;
	
	@Autowired
	DaoPlayerMission dao;
	
	@Override
	public DaoGeneric<PlayerMission> getDao() {
		return dao;
	}

}
