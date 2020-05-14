package com.splitwise.info6250.service;

import java.util.List;

import com.splitwise.info6250.model.Expense;
import com.splitwise.info6250.model.ExpenseDB;


public interface ExpenseService {
	
	int addExpense(ExpenseDB exp);
	
	ExpenseDB getExpense(int expenseId);
	
	void deleteExpense(int ExpenseId);
	
	void splitAmount(String paid_by, String[] split_with, String desc, int amount, boolean split_type,int exp_id);
	
	void RollbackTransaction(String paid_by, String[] split_with, int amount);
	
	List<ExpenseDB> getAllExpenses(String paid_by);
}
