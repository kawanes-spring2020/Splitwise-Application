package com.splitwise.info6250.dao;

import java.util.List;

import com.splitwise.info6250.model.Friends;
import com.splitwise.info6250.model.User;


public interface FriendDao {

	List<Friends> findBySourceId(int source_user_id);
	
	String addFriend(Friends friend);
	
	Friends findByUsername(String username);
	
	Friends findById(int id);
	
	void updateFriend(Friends friend);
	
	List<Friends> findSuccessFriends(int user_id);

}
