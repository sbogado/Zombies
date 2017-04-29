package zombies.model.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import zombies.model.dao.DaoPlayer;
import zombies.model.model.Player;

@Repository
public class PlayerDaoImpl extends DaoGenericImpl<Player> implements DaoPlayer{

	public Player findByName(String playerName){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Player> criteria = cb.createQuery(Player.class);
		Root<Player> root = criteria.from(Player.class);
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		predicates.add(cb.equal(root.get("name"),playerName));	
		
		criteria.where(predicates.toArray(new Predicate[predicates.size()]));
		TypedQuery<Player> q = em.createQuery(criteria);
		
		return q.getSingleResult();

	}
	
}
