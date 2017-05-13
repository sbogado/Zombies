package zombies.model.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import zombies.model.dao.DaoGeneric;

public class DaoGenericImpl<T> implements DaoGeneric<T> {
	
	/** Class logger **/
	protected static final Logger logger = Logger.getLogger(DaoGenericImpl.class.getName());
	
	@PersistenceContext
	EntityManager em;
	
    private Class<T> type;

	public DaoGenericImpl() {
	     type = getGenericType(getClass());
	}
	
	@SuppressWarnings("unchecked")
	private Class<T> getGenericType(Class<?> c){
		try{
			Class<?> parent = c;
			while(parent != null){
				Type t = parent.getGenericSuperclass();
				if(t!= null && t instanceof ParameterizedType){
					 ParameterizedType pt = (ParameterizedType) t;
				     Class<T> clazz = (Class<T>) pt.getActualTypeArguments()[0];
				     if(clazz != null){
				    	 return clazz;
				     }
				}
				parent = parent.getSuperclass();
			}
			return null;
		}catch(Exception e){
			return null;
		}
	}

	@Transactional(rollbackFor=Exception.class, value = "transactionManager")
	public T create(T t) {
		em.persist(t);
		return t;
	}

	@Transactional(rollbackFor=Exception.class, value = "transactionManager")
	public T update(T t) {	
		return em.merge(t);
	}
	
	@Transactional(rollbackFor=Exception.class, value = "transactionManager")
	public void remove(T t) {
		//First merge the object to avoid detached entity passed to delete.
		//Object retrieval and delete occurs in different transactions.
		em.remove(em.merge(t));
	}
	
    public T find(final Object id) {
        return (T) this.em.find(type, id);
    }
	
	@SuppressWarnings("unchecked")
	public List<T> genericSearchAll() {
		List<T> list = new ArrayList<T>();
		try {
			list = (List<T>) em.createQuery("from "+type.getName()).getResultList();
		} catch (Exception e){
			logger.severe(e.getMessage());
		}				
		return list;
	}

	public Class<T> getType(){
		return type;
	}
}
