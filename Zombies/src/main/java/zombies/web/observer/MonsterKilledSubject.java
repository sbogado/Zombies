package zombies.web.observer;

public interface MonsterKilledSubject {


	public void addMonsterKilledObserver(MonsterKilledObserver observer);
	public void removeMonsterKilledObserver(MonsterKilledObserver observer);
	public void notifyMonsterKilledToObservers();
}
