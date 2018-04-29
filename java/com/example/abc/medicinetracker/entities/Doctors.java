package com.example.abc.medicinetracker.entities;

public class Doctors {

	private Long doctor_id;
	private String doctor_name;
	private String specialist_in;
	private String doctor_address;
	private String doctor_mobile;
	private long doctor_zipcode;
	private String doctor_email;
	private String doctor_available_days;
	private String doctor_password;
	private String doctor_city;
	private String doctor_gender;


	public Doctors(Long doctor_id, String doctor_name, String specialist_in, String doctor_mobile, String doctor_address, long doctor_zipcode, String doctor_email, String doctor_available_days, String doctor_password, String doctor_city, String doctor_gender) {
		this.doctor_id=doctor_id;
		this.doctor_name=doctor_name;
		this.specialist_in=specialist_in;
		this.doctor_mobile=doctor_mobile;
		this.doctor_address=doctor_address;
		this.doctor_zipcode=doctor_zipcode;
		this.doctor_email=doctor_email;
		this.doctor_available_days=doctor_available_days;
		this.doctor_password=doctor_password;
		this.doctor_city=doctor_city;
		this.doctor_gender=doctor_gender;



	}

	public Doctors(int medicine_id, String medicine_name, String disease_id, String disease_name) {
	}

	public String getDoctor_city() {
		return doctor_city;
	}
	public void setDoctor_city(String doctor_city) {
		this.doctor_city = doctor_city;
	}
	public String getDoctor_gender() {
		return doctor_gender;
	}
	public void setDoctor_gender(String doctor_gender) {
		this.doctor_gender = doctor_gender;
	}
	public Long getDoctor_id() {
		return doctor_id;
	}
	public void setDoctor_id(Long doctor_id) {
		this.doctor_id = doctor_id;
	}
	public String getDoctor_name() {
		return doctor_name;
	}
	public void setDoctor_name(String doctor_name) {
		this.doctor_name = doctor_name;
	}
	public String getSpecialist_in() {
		return specialist_in;
	}
	public void setSpecialist_in(String specialist_in) {
		this.specialist_in = specialist_in;
	}
	public String getDoctor_address() {
		return doctor_address;
	}
	public void setDoctor_address(String doctor_address) {
		this.doctor_address = doctor_address;
	}
	public String getDoctor_mobile() {
		return doctor_mobile;
	}
	public void setDoctor_mobile(String doctor_mobile) {
		this.doctor_mobile = doctor_mobile;
	}
	public long getDoctor_zipcode() {
		return doctor_zipcode;
	}
	public void setDoctor_zipcode(long doctor_zipcode) {
		this.doctor_zipcode = doctor_zipcode;
	}
	public String getDoctor_email() {
		return doctor_email;
	}
	public void setDoctor_email(String doctor_email) {
		this.doctor_email = doctor_email;
	}
	public String getDoctor_available_days() {
		return doctor_available_days;
	}
	public void setDoctor_available_days(String doctor_available_days) {
		this.doctor_available_days = doctor_available_days;
	}
	public String getDoctor_password() {
		return doctor_password;
	}
	public void setDoctor_password(String doctor_password) {
		this.doctor_password = doctor_password;
	}
	
	
	
	
	
	
	
}
