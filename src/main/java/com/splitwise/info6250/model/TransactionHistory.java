package com.splitwise.info6250.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="Transaction")
public class TransactionHistory implements Serializable{

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="EXPENSE_ID")
	private Integer expense_id;
	
	@NotEmpty
	@Column(name="TR_NAME", nullable=false)
	private String tr_name;
	
	@NotEmpty
	@Column(name="source_user", nullable=false)
	private String source_user;
		
	@NotEmpty
	@Column(name="target_user", nullable=false)
	private String target_user;

	@NotEmpty
	@Column(name="output", nullable=false)
	private String output;

		
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTr_name() {
		return tr_name;
	}

	public void setTr_name(String tr_name) {
		this.tr_name = tr_name;
	}

	public String getSource_user() {
		return source_user;
	}

	public void setSource_user(String source_user) {
		this.source_user = source_user;
	}

	public String getTarget_user() {
		return target_user;
	}

	public void setTarget_user(String target_user) {
		this.target_user = target_user;
	}

	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}

	public Integer getExpense_id() {
		return expense_id;
	}

	public void setExpense_id(Integer expense_id) {
		this.expense_id = expense_id;
	}
	
	

	
}
