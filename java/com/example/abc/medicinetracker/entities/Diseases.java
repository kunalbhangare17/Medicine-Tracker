package com.example.abc.medicinetracker.entities;

public class Diseases {
	
	private String disease_id;
	private String disease_name;
	private String disease_description;
	private String disease_age;
	private String prevention;


	public Diseases(String disease_id, String disease_name, String disease_description, String prevention , String disease_age) {
		this.disease_id=disease_id;
		this.	disease_name=disease_name;
		this.disease_description=disease_description;
		this.disease_age=disease_age;
		this.prevention=prevention;



	}

	public String getPrevention() {
		return prevention;
	}

	public void setPrevention(String prevention) {
		this.prevention = prevention;
	}

	public String getDisease_age() {
		return disease_age;
	}
	public void setDisease_age(String disease_age) {
		this.disease_age = disease_age;
	}
	public String getDisease_id() {
		return disease_id;
	}
	public void setDisease_id(String disease_id) {
		this.disease_id = disease_id;
	}
	public String getDisease_name() {
		return disease_name;
	}
	public void setDisease_name(String disease_name) {
		this.disease_name = disease_name;
	}
	public String getDisease_description() {
		return disease_description;
	}
	public void setDisease_description(String disease_description) {
		this.disease_description = disease_description;
	}
	   
	@Override
	public String toString() {
		return "Diseases [disease_id=" + disease_id + ", disease_name=" + disease_name + ", disease_description="
				+ disease_description + "]";
	}
}
