package zombies.model.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import zombies.model.dao.DaoPlayer;
import zombies.model.dao.DaoPlayerLevel;
import zombies.model.model.Player;
import zombies.model.model.PlayerMission;
import zombies.model.service.PlayerService;

@Service
public class PlayerServiceImpl extends GenericABMService<Player> implements Serializable,PlayerService {

	private static final long serialVersionUID = -6541905902228163766L;
	
	@Autowired
	DaoPlayer playerDao;
	
	@Autowired
	DaoPlayerLevel playerLevelDao;

	public Player findByName(String playerName){
		return ((DaoPlayer) getDao()).findByName(playerName);
	}

	public List<PlayerMission> findMissions(Long playerId) throws Exception{
		return getDao().findMissions(playerId);
		
	}
	
	@Override
	public Player update(Player player) throws Exception{
		if(player.getExperience() > player.getPlayerLevel().getExperienceToNextLevel() 
				&& player.getPlayerLevel().getNextLevel() != null){
			player.addSkillPoints(player.getPlayerLevel().getSkillPoints());
			player.setPlayerLevel(getPlayerLevelDao().find(player.getPlayerLevel().getNextLevel().getId()));
			
		}
		return getDao().update(player);
	}
	
	@Override
	public DaoPlayer getDao() {
		return playerDao;
	}

	public DaoPlayerLevel getPlayerLevelDao() {
		return playerLevelDao;
	}

	public void setPlayerLevelDao(DaoPlayerLevel playerLevelDao) {
		this.playerLevelDao = playerLevelDao;
	}

}
