package zombies.model.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import zombies.model.authentication.UserCredential;
import zombies.model.dao.UserCredentialDao;

@Repository
public class UserCredentialDaoImpl extends DaoGenericImpl<UserCredential> implements UserCredentialDao{

	public UserCredential findByName(String name) throws Exception{
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<UserCredential> criteria = cb.createQuery(UserCredential.class);
		Root<UserCredential> root = criteria.from(UserCredential.class);
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		predicates.add(cb.equal(root.get("name"),name));	
		
		criteria.where(predicates.toArray(new Predicate[predicates.size()]));
		TypedQuery<UserCredential> q = em.createQuery(criteria);
		
		return q.getSingleResult();

	}

}
