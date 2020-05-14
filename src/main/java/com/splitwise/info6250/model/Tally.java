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
@Table(name="Tally")
public class Tally implements Serializable{

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@NotEmpty
	@Column(name="PAID_BY", nullable=false)
	private String paid_by;
		
	@NotEmpty
	@Column(name="PAID_TO", nullable=false)
	private String paid_to;

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

	public String getPaid_to() {
		return paid_to;
	}

	public void setPaid_to(String paid_to) {
		this.paid_to = paid_to;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

		
}
