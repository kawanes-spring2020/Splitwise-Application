package com.splitwise.info6250.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.splitwise.info6250.dao.FriendDao;
import com.splitwise.info6250.dao.UserDao;
import com.splitwise.info6250.model.Friends;
import com.splitwise.info6250.model.User;


@Service("friendService")
@Transactional
public class FriendServiceImpl implements FriendService{

	@Autowired
	private FriendDao dao;

	@Autowired
    private PasswordEncoder passwordEncoder;
	
	

	@Override
	public List<Friends> findBySourceId(int source_user_id) {
		
		return dao.findBySourceId(source_user_id);
	}



	@Override
	public String addFriend(Friends friend) {
		return dao.addFriend(friend);
	}



	@Override
	public Friends findByUsername(String username) {
		return dao.findByUsername(username);
	}



	@Override
	public void updateFriend(Friends friend) {
		dao.updateFriend(friend);
	}



	@Override
	public Friends findById(int id) {
		return dao.findById(id);
	}



	@Override
	public List<Friends> findSuccessFriends(int user_id) {
		return dao.findSuccessFriends(user_id);
	}


	
}

