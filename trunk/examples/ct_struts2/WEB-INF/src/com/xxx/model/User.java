package com.xxx.model;

import java.util.Date;

public class User implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;

	private int id;
	
	private String name;
	
	private String email;
	
	private int balance;
	
	private Date creationDate;
	
	public User() {}
	
	public User(int id, String name, String email, int balance, Date creationDate) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.balance = balance;
		this.creationDate = creationDate;
	}
	
	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public final String getEmail() {
		return email;
	}

	public final void setEmail(String email) {
		this.email = email;
	}
	
}
