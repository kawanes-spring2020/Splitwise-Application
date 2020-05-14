package com.splitwise.info6250.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.splitwise.info6250.dao.ExpenseDao;
import com.splitwise.info6250.dao.FriendDao;
import com.splitwise.info6250.dao.UserDao;
import com.splitwise.info6250.model.Expense;
import com.splitwise.info6250.model.ExpenseDB;
import com.splitwise.info6250.model.Friends;
import com.splitwise.info6250.model.Tally;
import com.splitwise.info6250.model.TransactionHistory;
import com.splitwise.info6250.model.User;


@Service("expenseService")
@Transactional
public class ExpenseServiceImpl implements ExpenseService{

	@Autowired
	private TallyService tallyService;
	
	@Autowired
	private ExpenseDao dao;
	
	@Autowired
	private TransactionService trService;

	@Override
	public void splitAmount(String paid_by, String[] split_with, String desc, int amount, boolean split_type,int exp_id) {
		if(split_type==false) {
			System.out.println(split_with[0].toUpperCase()+" owes $"+amount+" to "+paid_by);
			Tally tally = tallyService.getTallyRecord(paid_by,split_with[0]);
			Tally tallyrev = tallyService.getTallyRecord(split_with[0],paid_by);
			if(tally==null) {
				Tally tal = new Tally();
				tal.setPaid_by(paid_by);
				tal.setPaid_to(split_with[0]);
				tal.setAmount(amount);
				tallyService.addTallyRecord(tal);
			}
			else {
				int prevamount = tally.getAmount();
				int total = prevamount+amount;
				tally.setAmount(total);
				tallyService.updateTallyAmount(tally);
			}
			
			if(tallyrev==null) {
				Tally tal = new Tally();
				tal.setPaid_by(split_with[0]);
				tal.setPaid_to(paid_by);
				tal.setAmount(amount*(-1));
				tallyService.addTallyRecord(tal);
			}
			else {
				int prevamount = tallyrev.getAmount();
				int total = prevamount-amount;
				tallyrev.setAmount(total);
				tallyService.updateTallyAmount(tallyrev);
			}
			
			TransactionHistory th = new TransactionHistory();
			th.setExpense_id(exp_id);
			th.setTr_name(paid_by+" added expense for "+desc+" $"+amount);
			th.setSource_user(paid_by);
			th.setTarget_user(split_with[0]);
			th.setOutput(paid_by.toUpperCase()+" will get back $"+amount+" from "+split_with[0].toUpperCase());
			
			TransactionHistory th1 = new TransactionHistory();
			th1.setExpense_id(exp_id);
			th1.setTr_name(paid_by+" added expense for "+desc+" $"+amount);
			th1.setSource_user(split_with[0]);
			th1.setTarget_user(paid_by);
			th1.setOutput(split_with[0].toUpperCase()+" owes $"+amount+" to "+paid_by.toUpperCase());
			
			trService.addTransaction(th);
			trService.addTransaction(th1);
			
		}
		else {
			if(split_with.length==1) {
				System.out.println(split_with[0].toUpperCase()+" owes $"+(amount/2)+" to "+paid_by);
				Tally tally = tallyService.getTallyRecord(paid_by,split_with[0]);
				Tally tallyrev = tallyService.getTallyRecord(split_with[0],paid_by);
				if(tally==null) {
					Tally tal = new Tally();
					tal.setPaid_by(paid_by);
					tal.setPaid_to(split_with[0]);
					tal.setAmount((amount/2));
					tallyService.addTallyRecord(tal);
				}
				else {
					int prevamount = tally.getAmount();
					int total = prevamount+(amount/2);
					tally.setAmount(total);
					tallyService.updateTallyAmount(tally);
				}
				
				if(tallyrev==null) {
					Tally tal = new Tally();
					tal.setPaid_by(split_with[0]);
					tal.setPaid_to(paid_by);
					tal.setAmount((amount/2)*(-1));
					tallyService.addTallyRecord(tal);
				}
				else {
					int prevamount = tallyrev.getAmount();
					int total = prevamount-(amount/2);
					tallyrev.setAmount(total);
					tallyService.updateTallyAmount(tallyrev);
				}
				
				TransactionHistory th = new TransactionHistory();
				th.setExpense_id(exp_id);
				th.setTr_name(paid_by+" added expense for "+desc+" $"+amount);
				th.setSource_user(paid_by);
				th.setTarget_user(split_with[0]);
				th.setOutput(paid_by.toUpperCase()+" will get back $"+amount/2+" from "+split_with[0].toUpperCase());
				
				TransactionHistory th1 = new TransactionHistory();
				th1.setExpense_id(exp_id);
				th1.setTr_name(paid_by+" added expense for "+desc+" $"+amount);
				th1.setSource_user(split_with[0]);
				th1.setTarget_user(paid_by);
				th1.setOutput(split_with[0].toUpperCase()+" owes $"+amount/2+" to "+paid_by.toUpperCase());
				trService.addTransaction(th);
				trService.addTransaction(th1);
			}
			else {
				int amt = (amount/split_with.length);
				for(String payee : split_with) {
					if(!payee.equals(paid_by)) {
						System.out.println(payee.toUpperCase()+" owes $"+amt+" to "+paid_by);
						Tally tally = tallyService.getTallyRecord(paid_by,payee);
						Tally tallyrev = tallyService.getTallyRecord(payee,paid_by);
						if(tally==null) {
							Tally tal = new Tally();
							tal.setPaid_by(paid_by);
							tal.setPaid_to(payee);
							tal.setAmount((amt));
							tallyService.addTallyRecord(tal);
						}
						else {
							int prevamount = tally.getAmount();
							int total = prevamount+(amt);
							tally.setAmount(total);
							tallyService.updateTallyAmount(tally);
						}
						
						if(tallyrev==null) {
							Tally tal = new Tally();
							tal.setPaid_by(payee);
							tal.setPaid_to(paid_by);
							tal.setAmount((amt)*(-1));
							tallyService.addTallyRecord(tal);
						}
						else {
							int prevamount = tallyrev.getAmount();
							int total = prevamount-(amt);
							tallyrev.setAmount(total);
							tallyService.updateTallyAmount(tallyrev);
						}
						
						TransactionHistory th = new TransactionHistory();
						th.setExpense_id(exp_id);
						th.setTr_name(paid_by+" added expense for "+desc+" $"+amount);
						th.setSource_user(paid_by);
						th.setTarget_user(payee);
						th.setOutput(paid_by.toUpperCase()+" will get back $"+amt+" from "+payee.toUpperCase());
						
						TransactionHistory th1 = new TransactionHistory();
						th1.setExpense_id(exp_id);
						th1.setTr_name(paid_by+" added expense for "+desc+" $"+amount);
						th1.setSource_user(payee);
						th1.setTarget_user(paid_by);
						th1.setOutput(payee.toUpperCase()+" owes $"+amt+" to "+paid_by.toUpperCase());
						
						trService.addTransaction(th);
						trService.addTransaction(th1);
					}
				}
			}
		}
	}

	@Override
	public int addExpense(ExpenseDB exp) {
		return dao.addExpense(exp);
	}

	@Override
	public ExpenseDB getExpense(int expenseId) {
		// TODO Auto-generated method stub
		return dao.getExpense(expenseId);
	}

	@Override
	public void deleteExpense(int ExpenseId) {
		dao.deleteExpense(ExpenseId);
	}

	@Override
	public void RollbackTransaction(String paid_by, String[] split_with, int amount) {
		for(String payee : split_with) {
			if(!payee.equals(paid_by)) {
				Tally tally = tallyService.getTallyRecord(paid_by,payee);
				Tally tallyrev = tallyService.getTallyRecord(payee,paid_by);
				
				int prevamount = tally.getAmount();
				int total = prevamount-(amount);
				tally.setAmount(total);
				tallyService.updateTallyAmount(tally);
			
			
				
			
				int prevamoun = tallyrev.getAmount();
				int total1 = prevamoun+(amount);
				tallyrev.setAmount(total1);
				tallyService.updateTallyAmount(tallyrev);
			
			
			}
		}
		
	}

	@Override
	public List<ExpenseDB> getAllExpenses(String paid_by) {
		return dao.getAllExpenses(paid_by);
	}

	

	
}

