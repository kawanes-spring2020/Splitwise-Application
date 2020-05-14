package com.splitwise.info6250.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.splitwise.info6250.dao.FriendDao;
import com.splitwise.info6250.dao.NotificationDao;
import com.splitwise.info6250.dao.UserDao;
import com.splitwise.info6250.model.Friends;
import com.splitwise.info6250.model.Notification;
import com.splitwise.info6250.model.User;


@Service("notificationService")
@Transactional
public class NotificationServiceImpl implements NotificationService{

	@Autowired
	private NotificationDao dao;


	@Override
	public String addNotification(Notification notification) {
		return dao.addNotification(notification);
	}


	@Override
	public List<Notification> getNotifications(int user_id) {
		return dao.getNotifications(user_id);
	}


	@Override
	public void deleteNotification(int user_id, String username) {
		dao.deleteNotification(user_id, username);
	}


	
}

