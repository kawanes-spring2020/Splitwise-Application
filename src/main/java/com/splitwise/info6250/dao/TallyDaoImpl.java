package com.splitwise.info6250.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.splitwise.info6250.model.Friends;
import com.splitwise.info6250.model.Notification;
import com.splitwise.info6250.model.Tally;



@Repository("tallyDao")
public class TallyDaoImpl extends AbstractDao<Integer, Tally> implements TallyDao {

	static final Logger logger = LoggerFactory.getLogger(TallyDaoImpl.class);

	@Override
	public void addTallyRecord(Tally tally) {
		persist(tally);
	}

	@Override
	public void updateTallyAmount(Tally tally) {
		update(tally);
	}

	@Override
	public Tally getTallyRecord(String paidby, String paidto) {
		
		Criteria criteria = createEntityCriteria();
		Criterion source = Restrictions.eq("paid_by", paidby);
		Criterion target = Restrictions.eq("paid_to", paidto);
		Tally tal = (Tally)criteria.add(Restrictions.and(source,target)).uniqueResult();
		return tal;
	}

	@Override
	public void settleTally(String paidby, String paidto) {
		
		
	}
	
	
	
	
	
	

}
