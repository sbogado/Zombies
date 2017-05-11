package zombies.web.observer;

import zombies.scene.monstruos.Monstruo;

public interface MonsterKilledObserver{

	public void notifyMonsterKilledToObserver(Monstruo monstruo);
	
	
}
