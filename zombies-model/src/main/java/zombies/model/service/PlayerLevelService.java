package zombies.model.service;

import zombies.model.model.PlayerLevel;

public interface PlayerLevelService extends GenericService<PlayerLevel>{

	public PlayerLevel findByNumber(Integer number) throws Exception;

	
}
