package model;

public class Account {
	private String userId;
	private String name;

	public Account() {}

	public Account(String userId) {
		this.userId = userId;
	}

	public Account(String userId, String name) {
		this.userId = userId;
		this.name = name;

	}
	public String getUserId() {return userId;}
	public String getName() {return name;}

}
