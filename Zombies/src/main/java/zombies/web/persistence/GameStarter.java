package zombies.web.persistence;

public interface GameStarter {

	public void update(PersistentPlayer player);

	public PersistentMission getMission();
}
