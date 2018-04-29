package com.example.abc.medicinetracker.entities;

public class LocationMasterPojo {
	
	private int location_master_id;
	private String location;
	private String location_id;
	private double location_latitude;
	private double location_longitude;
	private String blood_bank_name;
	private String bank_description;
	private String sub_area;
	private String date;
	private String time;
	private String contact_details;


	public String getSub_area() {
		return sub_area;
	}

	public void setSub_area(String sub_area) {
		this.sub_area = sub_area;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getLocation_master_id() {
		return location_master_id;
	}
	public void setLocation_master_id(int location_master_id) {
		this.location_master_id = location_master_id;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getLocation_id() {
		return location_id;
	}
	public void setLocation_id(String location_id) {
		this.location_id = location_id;
	}
	public double getLocation_latitude() {
		return location_latitude;
	}
	public void setLocation_latitude(double location_latitude) {
		this.location_latitude = location_latitude;
	}
	public double getLocation_longitude() {
		return location_longitude;
	}
	public void setLocation_longitude(double location_longitude) {
		this.location_longitude = location_longitude;
	}
	public String getBlood_bank_name() {
		return blood_bank_name;
	}
	public void setBlood_bank_name(String blood_bank_name) {
		this.blood_bank_name = blood_bank_name;
	}
	public String getBank_description() {
		return bank_description;
	}
	public void setBank_description(String bank_description) {
		this.bank_description = bank_description;
	}
	public String getContact_details() {
		return contact_details;
	}
	public void setContact_details(String contact_details) {
		this.contact_details = contact_details;
	}
	
	

}
