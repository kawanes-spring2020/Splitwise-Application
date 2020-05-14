package com.splitwise.info6250.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.splitwise.info6250.model.Friends;
import com.splitwise.info6250.model.Notification;
import com.splitwise.info6250.model.User;



@Repository("notificationDao")
public class NotificationDaoImpl extends AbstractDao<Integer, Notification> implements NotificationDao {

	static final Logger logger = LoggerFactory.getLogger(NotificationDaoImpl.class);
	

	@Override
	public String addNotification(Notification notification) {
		persist(notification);
		return "Notification Sent";
	}


	@Override
	public List<Notification> getNotifications(int user_id) {
		List<Notification> notList = new ArrayList<Notification>();
		Criteria criteria = createEntityCriteria();
		
		notList = criteria.add(Restrictions.eq("user_id", user_id)).list();
		
		
		return notList;
	}


	@Override
	public void deleteNotification(int user_id, String username) {
		Criteria crit = createEntityCriteria();
		Criterion user_id_criteria = Restrictions.eq("user_id", user_id);
		Criterion username_criteria = Restrictions.eq("from_username", username);
		crit.add(Restrictions.and(user_id_criteria,username_criteria));
		Notification not = (Notification)crit.uniqueResult();
		delete(not);
		
	}

}
