package com.splitwise.info6250.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

public class TransactionDisplay implements Serializable{
	
	private Integer expense_id;
	private String tr_name;
	private List<String> output;
	private String date;
	private String time;
	private boolean edit;
		
	

	public boolean isEdit() {
		return edit;
	}

	public void setEdit(boolean edit) {
		this.edit = edit;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Integer getExpense_id() {
		return expense_id;
	}

	public void setExpense_id(Integer expense_id) {
		this.expense_id = expense_id;
	}

	public String getTr_name() {
		return tr_name;
	}

	public void setTr_name(String tr_name) {
		this.tr_name = tr_name;
	}

		public List<String> getOutput() {
		return output;
	}

	public void setOutput(List<String> output) {
		this.output = output;
	}

	
	

	
}
