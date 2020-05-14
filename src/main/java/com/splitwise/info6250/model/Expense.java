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
@Table(name="Expense")
public class Expense implements Serializable{

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@NotEmpty
	@Column(name="PAID_BY", nullable=false)
	private String paid_by;
		
	@NotEmpty
	@Column(name="SPLIT_WITH", nullable=false)
	private String[] split_with;

	@NotEmpty
	@Column(name="DESCRIPTION", nullable=false)
	private String description;

	@Column(name="AMOUNT")
	private Integer amount;
	
		
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPaid_by() {
		return paid_by;
	}

	public void setPaid_by(String paid_by) {
		this.paid_by = paid_by;
	}

	public String[] getSplit_with() {
		return split_with;
	}

	public void setSplit_with(String[] split_with) {
		this.split_with = split_with;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

		
}
