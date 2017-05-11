package zombies.model.dao;

import zombies.model.model.PlayerLevel;

public interface DaoPlayerLevel extends DaoGeneric<PlayerLevel> {

	public PlayerLevel findByNumber(Integer number);

}
