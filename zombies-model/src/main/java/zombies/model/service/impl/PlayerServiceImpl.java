package zombies.model.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import zombies.model.dao.DaoPlayer;
import zombies.model.model.Player;
import zombies.model.model.PlayerMission;
import zombies.model.service.PlayerService;

@Service
public class PlayerServiceImpl extends GenericABMService<Player> implements Serializable,PlayerService {

	private static final long serialVersionUID = -6541905902228163766L;
	
	@Autowired
	DaoPlayer playerDao;

	public Player findByName(String playerName){
		return ((DaoPlayer) getDao()).findByName(playerName);
	}

	public List<PlayerMission> findMissions(Long playerId) throws Exception{
		return getDao().findMissions(playerId);
		
	}
	
	@Override
	public DaoPlayer getDao() {
		return playerDao;
	}


}
