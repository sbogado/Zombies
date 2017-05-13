package zombies.web.persistence;

public interface GameStarter {

	public void update(PersistentPlayer player);

	public PersistentMission getMission();
	
	public void updatePlayerMissionToAcomplished(PersistentPlayer player);
}
