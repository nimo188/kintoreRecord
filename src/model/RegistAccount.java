package model;

public class RegistAccount {
	private String userId;
	private String name;
	private String password;
	private String birthday;
	private String gender;

	public RegistAccount() {}

	public RegistAccount(String userId, String name, String password, String birthday, String gender) {
		this.userId = userId;
		this.name = name;
		this.password = password;
		this.birthday = birthday;
		this.gender = gender;
	}

	public String getUserId() {return userId;}
	public String getName() {return name;}
	public String getPassword() {return password;}
	public String getBirthday() {return birthday;}
	public String getGender() {return gender;}
}
