package com.example.abc.medicinetracker.entities;

public class BookSlot {

	
	private int booking_id;
	private long doctor_id;
	private int id;
	private String booking_date;
	private String slot_time;
	private String loggedIn;

	public String getLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(String loggedIn) {
		this.loggedIn = loggedIn;
	}

	public int getBooking_id() {
		return booking_id;
	}
	public void setBooking_id(int booking_id) {
		this.booking_id = booking_id;
	}
	public long getDoctor_id() {
		return doctor_id;
	}
	public void setDoctor_id(long doctor_id) {
		this.doctor_id = doctor_id;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBooking_date() {
		return booking_date;
	}
	public void setBooking_date(String booking_date) {
		this.booking_date = booking_date;
	}
	public String getSlot_time() {
		return slot_time;
	}
	public void setSlot_time(String slot_time) {
		this.slot_time = slot_time;
	}
	
	
}
