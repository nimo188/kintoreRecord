package model;

public class Account {
	private String id;
	private String userId;
	private String pass;
	private String name;

	public Account() {}

	public Account(String userId) {
		this.userId = userId;
	}

	public Account(String userId, String name) {
		this.userId = userId;
		this.name = name;

	}
	public String id() {return id;}
	public String getUserId() {return userId;}
	public String getPass() {return pass;}
	public String getName() {return name;}

}
