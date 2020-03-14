package model;

import java.util.Date;

public class RecordList {

	private String parts;
	private String type;
	private String rep;
	private String exSet;
	private String weight;
	private Date createDate;

	public RecordList() {}

	public RecordList(String parts, String type, String rep, String exSet, String weight, Date createDate) {
		this.parts = parts;
		this.type = type;
		this.rep = rep;
		this.exSet = exSet;
		this.weight = weight;
		this.createDate = createDate;

	}
	public String getParts() {return parts;}
	public String getType() {return type;}
	public String getRep() {return rep;}
	public String getExSet() {return exSet;}
	public String getWeight() {return weight;}
	public Date getCreateDate() {return createDate;}

}
