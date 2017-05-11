package zombies.model.service;

import java.util.List;

import zombies.model.model.Player;
import zombies.model.model.PlayerMission;

public interface PlayerService  extends GenericService<Player>{

	public Player findByName(String playerName);
	
	public List<PlayerMission> findMissions(Long playerId) throws Exception;
}
