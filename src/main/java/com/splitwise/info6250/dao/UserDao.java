package com.splitwise.info6250.dao;

import java.util.List;

import com.splitwise.info6250.model.User;


public interface UserDao {

	User findById(int id);
	
	User findBySSO(String sso);
	
	void save(User user);
	
	void deleteBySSO(String sso);
	
	List<User> findAllUsers();

}
