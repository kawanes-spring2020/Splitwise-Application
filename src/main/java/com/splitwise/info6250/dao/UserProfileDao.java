package com.splitwise.info6250.dao;

import java.util.List;

import com.splitwise.info6250.model.UserProfile;


public interface UserProfileDao {

	List<UserProfile> findAll();
	
	UserProfile findByType(String type);
	
	UserProfile findById(int id);
}
