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
@Table(name="FRIENDS")
public class Friends implements Serializable{

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(name="USER_ID")
	private Integer user_id;
	
	@NotEmpty
	@Column(name="FRIEND_USERNAME", nullable=false)
	private String friend_username;
		
	@NotEmpty
	@Column(name="FRIEND_FIRST_NAME", nullable=false)
	private String friendFirstName;

	@NotEmpty
	@Column(name="FRIEND_LAST_NAME", nullable=false)
	private String friendLastName;

	@NotEmpty
	@Column(name="FRIEND_EMAIL", nullable=false)
	private String friendEmail;

	@NotEmpty
	@Column(name="STATUS", nullable=false)
	private String status;	
	
	@Column(name="balance")
	private Integer balance;
		
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

	

	public String getFriend_username() {
		return friend_username;
	}

	public void setFriend_username(String friend_username) {
		this.friend_username = friend_username;
	}

	public String getFriendFirstName() {
		return friendFirstName;
	}

	public void setFriendFirstName(String friendFirstName) {
		this.friendFirstName = friendFirstName;
	}

	public String getFriendLastName() {
		return friendLastName;
	}

	public void setFriendLastName(String friendLastName) {
		this.friendLastName = friendLastName;
	}

	public String getFriendEmail() {
		return friendEmail;
	}

	public void setFriendEmail(String friendEmail) {
		this.friendEmail = friendEmail;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getBalance() {
		return balance;
	}

	public void setBalance(Integer balance) {
		this.balance = balance;
	}
	
	

		
	
}
