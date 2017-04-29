package zombies.model.dao;

import zombies.model.model.Player;

public interface DaoPlayer extends DaoGeneric<Player> {

	public Player findByName(String playerName);
}
