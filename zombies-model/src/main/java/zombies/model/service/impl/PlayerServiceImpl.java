package zombies.model.service.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import zombies.model.dao.DaoGeneric;
import zombies.model.dao.DaoPlayer;
import zombies.model.model.Player;
import zombies.model.service.PlayerService;

@Service
public class PlayerServiceImpl extends GenericABMService<Player> implements Serializable,PlayerService {

	private static final long serialVersionUID = -6541905902228163766L;
	
	@Autowired
	DaoPlayer playerDao;

	public Player findByName(String playerName){
		return ((DaoPlayer) getDao()).findByName(playerName);
	}

	@Override
	public DaoGeneric<Player> getDao() {
		return playerDao;
	}


}
