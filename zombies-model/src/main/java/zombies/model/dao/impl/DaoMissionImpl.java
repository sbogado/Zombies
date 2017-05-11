package zombies.model.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import zombies.model.dao.DaoMission;
import zombies.model.model.Mission;

@Repository
public class DaoMissionImpl extends DaoGenericImpl<Mission> implements DaoMission{

	
	public List<Mission> findMissionsToShow() throws Exception{
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Mission> criteria = cb.createQuery(Mission.class);
		Root<Mission> root = criteria.from(Mission.class);
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		predicates.add(cb.equal(root.get("hasToShow"),true));	
		
		criteria.where(predicates.toArray(new Predicate[predicates.size()]));
		criteria.orderBy(cb.desc(root.get("order")));
		TypedQuery<Mission> q = em.createQuery(criteria);
		
		return q.getResultList();

	}
}
