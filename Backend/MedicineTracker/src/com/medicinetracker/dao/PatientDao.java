package com.medicinetracker.dao;

import java.util.List;

import com.medicinetracker.pojo.PatientPojo;
import com.medicinetracker.pojo.PatientReportPojo;


public interface PatientDao {
	
	public int add(PatientPojo patientPojo);
	
	public PatientPojo login(String patient_email, String patient_password);
	
	public boolean AddReport(PatientReportPojo patientReportPojo);
	
	public List<PatientReportPojo> getPatientReport(String user_email);
}
