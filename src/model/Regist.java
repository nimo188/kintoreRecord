package model;

public class Regist {
	private String userId;
	private String name;
	private String parts;
	private String type;
	private String rep;
	private String exSet;
	private String weight;

	public Regist() {}

	public Regist(String userId, String name, String parts, String type, String rep, String exSet, String weight) {
		this.userId = userId;
		this.name = name;
		this.parts = parts;
		this.type = type;
		this.rep = rep;
		this.exSet = exSet;
		this.weight = weight;

	}
	public String getUserId() {return userId;}
	public String getName() {return name;}
	public String getParts() {return parts;}
	public String getType() {return type;}
	public String getRep() {return rep;}
	public String getExSet() {return exSet;}
	public String getWeight() {return weight;}


}
