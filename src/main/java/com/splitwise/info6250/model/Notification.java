package com.splitwise.info6250.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="NOTIFICATIONS")
public class Notification implements Serializable{

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(name="USER_ID")
	private Integer user_id;
	
	@NotEmpty
	@Column(name="FROM_USERNAME", nullable=false)
	private String from_username;
		
	@NotEmpty
	@Column(name="FROM_FIRST_NAME", nullable=false)
	private String fromFirstName;

	@NotEmpty
	@Column(name="FROM_LAST_NAME", nullable=false)
	private String fromLastName;

	@NotEmpty
	@Column(name="FROM_EMAIL", nullable=false)
	private String fromEmail;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public String getFrom_username() {
		return from_username;
	}

	public void setFrom_username(String from_username) {
		this.from_username = from_username;
	}

	public String getFromFirstName() {
		return fromFirstName;
	}

	public void setFromFirstName(String fromFirstName) {
		this.fromFirstName = fromFirstName;
	}

	public String getFromLastName() {
		return fromLastName;
	}

	public void setFromLastName(String fromLastName) {
		this.fromLastName = fromLastName;
	}

	public String getFromEmail() {
		return fromEmail;
	}

	public void setFromEmail(String fromEmail) {
		this.fromEmail = fromEmail;
	}

		
}
