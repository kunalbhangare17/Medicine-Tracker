package com.example.abc.medicinetracker.entities;

public class PatientReportPojo {

	
	private long id;
	private String date;
	private String user_email;
	private String systolic;
	private String diastolic;
	private String glucose;
	private String aic;
	private String total_cholestrol;
	private String bmi;

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

	public String getSystolic() {
		return systolic;
	}

	public void setSystolic(String systolic) {
		this.systolic = systolic;
	}

	public String getDiastolic() {
		return diastolic;
	}

	public void setDiastolic(String diastolic) {
		this.diastolic = diastolic;
	}

	public String getGlucose() {
		return glucose;
	}

	public void setGlucose(String glucose) {
		this.glucose = glucose;
	}

	public String getAic() {
		return aic;
	}

	public void setAic(String aic) {
		this.aic = aic;
	}

	public String getTotal_cholestrol() {
		return total_cholestrol;
	}

	public void setTotal_cholestrol(String total_cholestrol) {
		this.total_cholestrol = total_cholestrol;
	}

	public String getBmi() {
		return bmi;
	}

	public void setBmi(String bmi) {
		this.bmi = bmi;
	}
}
