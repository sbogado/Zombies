package zombies.web.observer;

public interface MonsterSubject {


	public void add(MonsterObserver observer);
	public void remove(MonsterObserver observer);
	public void notifyMonsterKilledToObservers();
}
