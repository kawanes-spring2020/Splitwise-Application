package com.splitwise.info6250.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.splitwise.info6250.dao.FriendDao;
import com.splitwise.info6250.dao.TallyDao;
import com.splitwise.info6250.dao.TransactionDao;
import com.splitwise.info6250.dao.UserDao;
import com.splitwise.info6250.model.Friends;
import com.splitwise.info6250.model.Tally;
import com.splitwise.info6250.model.TransactionHistory;
import com.splitwise.info6250.model.User;


@Service("transactionService")
@Transactional
public class TransactionServiceImpl implements TransactionService{

	@Autowired
	private TransactionDao dao;
	
	@Autowired
	private TransactionService trService;

	@Override
	public void addTransaction(TransactionHistory th) {
		dao.addTransaction(th);
	}

	@Override
	public List<TransactionHistory> getAllTransactions(String sourceUser) {
		// TODO Auto-generated method stub
		return dao.getAllTransactions(sourceUser);
	}

	@Override
	public List<TransactionHistory> getIndividualTransactions(String sourceUser, String targetUser) {
		return dao.getIndividualTransactions(sourceUser,targetUser);
	}

	@Override
	public void deleteTransactions(int expenseId) {
		dao.deleteTransactions(expenseId);
	}

	@Override
	public List<TransactionHistory> getTransactionByexpenseId(int expenseId) {
		return dao.getTransactionByexpenseId(expenseId);
	}

	@Override
	public void settleAmountTransaction(String sourceUser, String targetUser, int expid,int amount) {
//		dao.settleAmountTransaction(sourceUser, targetUser);
		TransactionHistory th = new TransactionHistory();
		th.setExpense_id(expid);
		th.setTr_name(sourceUser+" settled up with "+targetUser+" $"+amount);
		th.setSource_user(sourceUser);
		th.setTarget_user(targetUser);
		th.setOutput(sourceUser+" settled up for " +"$"+amount+ " with "+targetUser);
		
		TransactionHistory th1 = new TransactionHistory();
		th1.setExpense_id(expid);
		th1.setTr_name(sourceUser+" settled up with "+targetUser+" $"+amount);
		th1.setSource_user(targetUser);
		th1.setTarget_user(sourceUser);
		th1.setOutput(sourceUser+" settled up for " +"$"+amount+ " with "+targetUser);
		
		trService.addTransaction(th);
		trService.addTransaction(th1);
		
	}

	

	



	

	
}

