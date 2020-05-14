package com.splitwise.info6250.service;

import java.util.List;

import com.splitwise.info6250.model.Friends;
import com.splitwise.info6250.model.Notification;
import com.splitwise.info6250.model.Tally;
import com.splitwise.info6250.model.User;


public interface TallyService {
	
	void addTallyRecord(Tally tally);
	Tally getTallyRecord(String paidby, String paidto);
	void updateTallyAmount(Tally tally);
	void settleTally(String paidby, String paidto);
}
