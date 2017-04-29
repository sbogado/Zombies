package zombies.web.observer;

public interface TimeSubject {

	public void add(TimeObserver observer);
	public void remove(TimeObserver observer);
	public void notifyTimeElapsedToObservers(double time);
}
