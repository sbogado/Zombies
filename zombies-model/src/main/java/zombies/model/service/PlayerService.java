package zombies.model.service;

import zombies.model.model.Player;

public interface PlayerService  extends GenericService<Player>{

	public Player findByName(String playerName);
}
