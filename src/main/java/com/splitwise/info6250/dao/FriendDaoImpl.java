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



@Repository("friendDao")
public class FriendDaoImpl extends AbstractDao<Integer, Friends> implements FriendDao {

	static final Logger logger = LoggerFactory.getLogger(FriendDaoImpl.class);
	
	
	@Override
	public List<Friends> findBySourceId(int source_user_id) {
		Criteria criteria = createEntityCriteria();
		List<Friends> list = new ArrayList<>();
		list = criteria.add(Restrictions.eq("user_id", source_user_id)).list();
		return list;
	}


	@Override
	public String addFriend(Friends friend) {
		persist(friend);
		return "friend added";
	}


	@Override
	public Friends findByUsername(String username) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("friend_username", username));
		Friends friends = (Friends)crit.uniqueResult();
		
		return friends;
	}


	@Override
	public Friends findById(int id) {
		Friends friend = getByKey(id);
		return friend;
	}


	@Override
	public void updateFriend(Friends friend) {
		friend.setStatus("Success");
		update(friend);
	}


	@Override
	public List<Friends> findSuccessFriends(int user_id) {
		Criteria criteria = createEntityCriteria();
		List<Friends> list = new ArrayList<>();
		Criterion user_id_criteria = Restrictions.eq("user_id", user_id);
		Criterion username_criteria = Restrictions.eq("status", "Success");
		list = criteria.add(Restrictions.and(user_id_criteria,username_criteria)).list();
		return list;
	}


	

}
