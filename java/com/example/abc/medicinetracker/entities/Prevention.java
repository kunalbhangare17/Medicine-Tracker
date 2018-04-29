package com.example.abc.medicinetracker.entities;

public class Prevention {

	
	private long prevention_id;
	private long disease_id;
	private String disease_name;
	private String prevention;
	private String info;


	public Prevention(long prevention_id, String disease_name, String prevention, long disease_id, String info) {
		this.prevention_id = prevention_id;
		this.disease_id = disease_id;
		this.disease_name = disease_name;
		this.prevention = prevention;
		this.info = info;



	}

	public long getPrevention_id() {
		return prevention_id;
	}
	public void setPrevention_id(long prevention_id) {
		this.prevention_id = prevention_id;
	}
	public long getDisease_id() {
		return disease_id;
	}
	public void setDisease_id(long disease_id) {
		this.disease_id = disease_id;
	}
	public String getPrevention() {
		return prevention;
	}
	public void setPrevention(String prevention) {
		this.prevention = prevention;
	}
	public String getDisease_name() {
		return disease_name;
	}
	public void setDisease_name(String disease_name) {
		this.disease_name = disease_name;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	
	
}
