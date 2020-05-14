package com.splitwise.info6250.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.splitwise.info6250.model.Expense;
import com.splitwise.info6250.model.ExpenseDB;
import com.splitwise.info6250.model.Friends;
import com.splitwise.info6250.model.Notification;
import com.splitwise.info6250.model.Tally;
import com.splitwise.info6250.model.TransactionHistory;
import com.splitwise.info6250.model.User;



@Repository("transactionDao")
public class TransactionDaoImpl extends AbstractDao<Integer, TransactionHistory> implements TransactionDao {

	static final Logger logger = LoggerFactory.getLogger(TransactionDaoImpl.class);

	@Override
	public void addTransaction(TransactionHistory th) {
		persist(th);
	}

	@Override
	public List<TransactionHistory> getAllTransactions(String sourceUser) {
		List<TransactionHistory> thlist = new ArrayList<TransactionHistory>();
		
		Criteria criteria = createEntityCriteria();
		
		thlist = criteria.add(Restrictions.eq("source_user", sourceUser)).list();
		
		return thlist;
	}

	@Override
	public List<TransactionHistory> getIndividualTransactions(String sourceUser, String targetUser) {
		Criteria crit = createEntityCriteria();
		List<TransactionHistory> retList = new ArrayList<TransactionHistory>();
		Criterion user_id_criteria = Restrictions.eq("source_user", sourceUser);
		Criterion username_criteria = Restrictions.eq("target_user", targetUser);
		crit.add(Restrictions.and(user_id_criteria,username_criteria));
		retList = crit.list();
		return retList;
	}

	@Override
	public void deleteTransactions(int expenseId) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("expense_id", expenseId));
		List<TransactionHistory> list = crit.list();
		for(TransactionHistory th:list) {
			delete(th);
		}
		
		
	}

	@Override
	public List<TransactionHistory> getTransactionByexpenseId(int expenseId) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("expense_id", expenseId));
		List<TransactionHistory> list = crit.list();
		return list;
	}

	@Override
	public void settleAmountTransaction(String sourceUser, String targetUser) {
		
		
	}

	

	
	
	
	
	
	

}
