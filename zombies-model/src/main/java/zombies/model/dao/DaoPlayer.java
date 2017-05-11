package zombies.model.dao;

import java.util.List;

import zombies.model.model.Player;
import zombies.model.model.PlayerMission;

public interface DaoPlayer extends DaoGeneric<Player> {

	public Player findByName(String playerName);
	
	public List<PlayerMission> findMissions(Long playerId) throws Exception;
}
