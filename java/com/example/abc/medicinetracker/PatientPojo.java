package com.example.abc.medicinetracker;

public class PatientPojo {
	
	private int patient_id;
	private String patient_name;
	private String patient_password;


	private String patient_contact_no;
	private String address;
	private String patient_email;
	private String patient_reference;
	private String patient_ref_name;
	private String patient_ref_email;
	private String loggedIn;

	public String getLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(String loggedIn) {
		this.loggedIn = loggedIn;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPatient_password() {
		return patient_password;
	}

	public void setPatient_password(String patient_password) {
		this.patient_password = patient_password;
	}


	public int getPatient_id(String patient_id) {
		return this.patient_id;
	}
	public void setPatient_id(int patient_id) {
		this.patient_id = patient_id;
	}
	public String getPatient_name() {
		return patient_name;
	}
	public void setPatient_name(String patient_name) {
		this.patient_name = patient_name;
	}
	public String getPatient_contact_no() {
		return patient_contact_no;
	}
	public void setPatient_contact_no(String patient_contact_no) {
		this.patient_contact_no = patient_contact_no;
	}
	public String getPatient_email() {
		return patient_email;
	}
	public void setPatient_email(String patient_email) {
		this.patient_email = patient_email;
	}
	public String getPatient_reference() {
		return patient_reference;
	}
	public void setPatient_reference(String patient_reference) {
		this.patient_reference = patient_reference;
	}
	public String getPatient_ref_name() {
		return patient_ref_name;
	}
	public void setPatient_ref_name(String patient_ref_name) {
		this.patient_ref_name = patient_ref_name;
	}
	public String getPatient_ref_email(String patient_ref_email) {
		return this.patient_ref_email;
	}
	public void setPatient_ref_email(String patient_ref_email) {
		this.patient_ref_email = patient_ref_email;
	}
	
	
}
