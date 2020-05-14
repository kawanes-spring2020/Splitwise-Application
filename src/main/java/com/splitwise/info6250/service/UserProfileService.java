package com.splitwise.info6250.service;

import java.util.List;

import com.splitwise.info6250.model.UserProfile;


public interface UserProfileService {

	UserProfile findById(int id);

	UserProfile findByType(String type);
	
	List<UserProfile> findAll();
	
}
