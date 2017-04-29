package zombies.web.observer;

public interface Subject {

	public void add(Observer observer);
	public void remove(Observer observer);
	public void notifyObservers();
	
}
