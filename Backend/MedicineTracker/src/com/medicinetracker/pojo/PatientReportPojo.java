package com.medicinetracker.pojo;

public class PatientReportPojo {

	
	private long id;
	private String date;
	private String user_email;
	private double systolic;
	private double diastolic;
	private double glucose;
	private double aic;
	private double total_cholestrol;
	private double bmi;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	public double getSystolic() {
		return systolic;
	}
	public void setSystolic(double systolic) {
		this.systolic = systolic;
	}
	public double getDiastolic() {
		return diastolic;
	}
	public void setDiastolic(double diastolic) {
		this.diastolic = diastolic;
	}
	public double getGlucose() {
		return glucose;
	}
	public void setGlucose(double glucose) {
		this.glucose = glucose;
	}
	public double getAic() {
		return aic;
	}
	public void setAic(double aic) {
		this.aic = aic;
	}
	public double getTotal_cholestrol() {
		return total_cholestrol;
	}
	public void setTotal_cholestrol(double total_cholestrol) {
		this.total_cholestrol = total_cholestrol;
	}
	public double getBmi() {
		return bmi;
	}
	public void setBmi(double bmi) {
		this.bmi = bmi;
	}
	
	
	
	
}
