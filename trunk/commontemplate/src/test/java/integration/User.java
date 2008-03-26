package integration;

import java.util.Date;

public class User {

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

	public static String getTest() {
		return "mytest";
	}

	public static String getTest2(String name) {
		return "test" + name;
	}

	private long fee = 50L;

	public long getFee() {
		return fee;
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

	public String toString() {
		return "id:" + id
				+ ",name:" + name
				+ ",email:" + email
				+ ",balance:" + balance
				+ ",creationDate:" + creationDate;
	}

	public static final String getStaticName() {
		return "user";
	}

}
