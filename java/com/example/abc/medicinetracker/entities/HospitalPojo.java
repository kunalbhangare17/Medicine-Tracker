package com.example.abc.medicinetracker.entities;

public class HospitalPojo {


private  int hospital_id;
	private String name;
	private String description;
	private String address;
	private String hospital_email;
	private String phone;
	private String opening_time;

	public HospitalPojo(int hospital_id, String name, String description, String address, String hospital_email, String phone, String opening_time) {
		this.hospital_id=hospital_id;
		this.name = name;
		this.description = description;
		this.address = address;
		this.hospital_email = hospital_email;
		this.phone = phone;
		this.opening_time = opening_time;
	}

	public int getHospital_id() {
		return hospital_id;
	}
	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getAddress() {
		return address;
	}

	public void setHospital_email(String hospital_email) {
		this.hospital_email = hospital_email;
	}

	public String getHospital_email() {
		return hospital_email;

	}

	public String getPhone() {
		return phone;
	}

	public String getOpening_time() {
		return opening_time;
	}
}
