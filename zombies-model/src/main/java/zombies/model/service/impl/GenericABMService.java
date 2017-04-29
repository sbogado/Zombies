package zombies.model.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import zombies.model.dao.DaoGeneric;
import zombies.model.service.GenericService;

public abstract class GenericABMService<T> implements GenericService<T>{

	
	/****************************************** Generic Methods ************************************/
	
	@Transactional(rollbackFor = Exception.class, value = "transactionManager")
	public T create(T object) throws Exception {
		return getDao().create(object);
	}

	public T update(T object) throws Exception{
		return getDao().update(object);
	}

	@Transactional(rollbackFor = Exception.class, value = "transactionManager")
	public void remove(T object) throws Exception {
		getDao().remove(object);
	}
	
	@Transactional(rollbackFor = Exception.class, value = "transactionManager")
	public List<T> genericSearchAll() throws Exception{
		return getDao().genericSearchAll();
	}
	
	/****************************************** Abstract Methods ************************************/
	
	public abstract DaoGeneric<T> getDao();


}
