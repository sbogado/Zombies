package zombies.model.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import zombies.model.dao.DaoPlayerLevel;
import zombies.model.model.PlayerLevel;

@Repository
public class DaoPlayerLevelImpl extends DaoGenericImpl<PlayerLevel> implements DaoPlayerLevel{

	public PlayerLevel findByNumber(Integer number){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<PlayerLevel> criteria = cb.createQuery(PlayerLevel.class);
		Root<PlayerLevel> root = criteria.from(PlayerLevel.class);
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		predicates.add(cb.equal(root.get("number"),number));	
		
		criteria.where(predicates.toArray(new Predicate[predicates.size()]));
		TypedQuery<PlayerLevel> q = em.createQuery(criteria);
		
		return q.getSingleResult();

	}

}
