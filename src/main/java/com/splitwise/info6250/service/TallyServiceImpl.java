package com.splitwise.info6250.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.splitwise.info6250.dao.FriendDao;
import com.splitwise.info6250.dao.TallyDao;
import com.splitwise.info6250.dao.UserDao;
import com.splitwise.info6250.model.Friends;
import com.splitwise.info6250.model.Tally;
import com.splitwise.info6250.model.User;


@Service("tallyService")
@Transactional
public class TallyServiceImpl implements TallyService{

	@Autowired
	private TallyDao dao;

	@Override
	public void addTallyRecord(Tally tally) {
		dao.addTallyRecord(tally);
	}

	

	@Override
	public void updateTallyAmount(Tally tally) {
		dao.updateTallyAmount(tally);
	}





	@Override
	public Tally getTallyRecord(String paidby, String paidto) {
		return dao.getTallyRecord(paidby, paidto);
	}



	@Override
	public void settleTally(String paidby, String paidto) {
		dao.settleTally(paidby,paidto);
		
	}



	

	
}

