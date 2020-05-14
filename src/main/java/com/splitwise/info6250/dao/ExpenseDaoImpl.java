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
import com.splitwise.info6250.model.User;



@Repository("expenseDao")
public class ExpenseDaoImpl extends AbstractDao<Integer, ExpenseDB> implements ExpenseDao {

	static final Logger logger = LoggerFactory.getLogger(ExpenseDaoImpl.class);

	@Override
	public int addExpense(ExpenseDB exp) {
		persist(exp);
		return exp.getId();
	}

	@Override
	public ExpenseDB getExpense(int expenseId) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("id", expenseId));
		ExpenseDB exp = (ExpenseDB)crit.uniqueResult();
		return exp;
	}

	@Override
	public void deleteExpense(int ExpenseId) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("id", ExpenseId));
		ExpenseDB exp = (ExpenseDB)crit.uniqueResult();
		delete(exp);
		
	}

	@Override
	public List<ExpenseDB> getAllExpenses(String paid_by) {
		Criteria criteria = createEntityCriteria();
		List<ExpenseDB> notList = new ArrayList<ExpenseDB>();
		notList = criteria.add(Restrictions.eq("paid_by", paid_by)).list();
		return notList;
	}

	
	
	
	
	
	

}
