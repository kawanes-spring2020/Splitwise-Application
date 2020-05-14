package com.splitwise.info6250.service;

import java.util.List;

import com.splitwise.info6250.model.Friends;
import com.splitwise.info6250.model.Notification;
import com.splitwise.info6250.model.User;


public interface NotificationService {
	
	String addNotification(Notification notification);
	List<Notification> getNotifications(int user_id);
	void deleteNotification(int user_id, String username);

}
