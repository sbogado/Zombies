package zombies.model.dao;

import java.util.List;

public interface DaoGeneric<T> {	
	T create(T t);
	T update(T t);
	void remove(T t);
	List<T> genericSearchAll();
}
