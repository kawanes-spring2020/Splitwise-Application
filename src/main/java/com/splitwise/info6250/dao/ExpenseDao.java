package com.splitwise.info6250.dao;

import java.util.List;

import com.splitwise.info6250.model.Expense;
import com.splitwise.info6250.model.ExpenseDB;
import com.splitwise.info6250.model.Friends;
import com.splitwise.info6250.model.Tally;
import com.splitwise.info6250.model.User;


public interface ExpenseDao {

	int addExpense(ExpenseDB expense);
	ExpenseDB getExpense(int expenseId);
	void deleteExpense(int ExpenseId);
	List<ExpenseDB> getAllExpenses(String paid_by);
}
