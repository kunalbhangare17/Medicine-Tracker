package com.medicinetracker.dao;

import java.util.List;

import com.medicinetracker.pojo.MedicinePojo;


public interface MedicineDao {
	
	//public int add(String[][] medicine);
	
	public int add(MedicinePojo medicinePojo);
	
	public List<MedicinePojo> addMoreMedicines(String patient_email);
	
	public List<MedicinePojo> viewNotify(int notify);
	
	public List<MedicinePojo> getNotificationByPatient(String patient_email, String date);

}
