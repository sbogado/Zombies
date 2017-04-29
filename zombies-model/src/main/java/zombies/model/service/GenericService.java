package zombies.model.service;

import java.util.List;

public interface GenericService<T>{

	/****************************************** Generic Methods ************************************/
	
	T create(T object) throws Exception;
	T update(T object) throws Exception;
	void remove(T object) throws Exception;
	List<T> genericSearchAll() throws Exception; 

}
