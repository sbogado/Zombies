package zombies.model.service.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import zombies.model.dao.DaoPlayerLevel;
import zombies.model.model.PlayerLevel;
import zombies.model.service.PlayerLevelService;

@Service
public class PlayerLevelServiceImpl extends GenericABMService<PlayerLevel> implements Serializable,PlayerLevelService {

	private static final long serialVersionUID = -6541905902228163766L;
	
	@Autowired
	DaoPlayerLevel dao;

	@Override
	public DaoPlayerLevel getDao() {
		return dao;
	}

	@Override
	public PlayerLevel findByNumber(Integer number)  throws Exception {
		return getDao().findByNumber(number);
	}

	@Override
	public PlayerLevel findById(Long id)  throws Exception {
		return getDao().find(id);
	}


}
