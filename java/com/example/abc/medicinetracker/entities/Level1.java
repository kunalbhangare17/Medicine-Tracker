package com.example.abc.medicinetracker.entities;

public class Level1 {

	private String level1_id;
	private String level1_name;
	private String level1_description;

	public Level1(String level1_id, String level1_name, String level1_description) {
		this.level1_id = level1_id;
		this.level1_name = level1_name;
		this.level1_description = level1_description;
	}



	public String getLevel1_id() {
		return level1_id;
	}

	public void setLevel1_id(String level1_id) {
		this.level1_id = level1_id;
	}

	public String getLevel1_name() {
		return level1_name;
	}

	public void setLevel1_name(String level1_name) {
		this.level1_name = level1_name;
	}

	public String getLevel1_description() {
		return level1_description;
	}

	public void setLevel1_description(String level1_description) {
		this.level1_description = level1_description;
	}
}
