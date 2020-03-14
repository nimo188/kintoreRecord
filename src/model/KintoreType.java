package model;


import java.io.Serializable;

public class KintoreType implements Serializable {
	private String parts; // 部位
	private String type;  // 筋トレの種目

	public KintoreType() {}

	public KintoreType(String parts) {
		this.parts = parts;
	}

	public KintoreType(String parts, String type) {
		this.parts = parts;
		this.type = type;
	}

	public String getParts() { return parts; }
	public String getType() {return type; }

}
