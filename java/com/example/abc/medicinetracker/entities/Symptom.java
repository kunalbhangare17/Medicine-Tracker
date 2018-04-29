package com.example.abc.medicinetracker.entities;

public class Symptom {
   private long symptom_id;
   private String symptom_name;
   private String symptom_description;

	public Symptom(String symptom_name, long symptom_id, String symptom_description) {
		this.symptom_name=symptom_name;
		this.symptom_id=symptom_id;
		this.symptom_description=symptom_description;
		this.symptom_description=symptom_description;

	}

	public Symptom(long symptom_id, String symptom_name, String symptom_description) {
		this.symptom_id = symptom_id;
		this.symptom_name = symptom_name;
		this.symptom_description = symptom_description;
	}

	public long getSymptom_id() {
	return symptom_id;
}
public void setSymptom_id(long symptom_id) {
	this.symptom_id = symptom_id;
}
public String getSymptom_name() {
	return symptom_name;
}
public void setSymptom_name(String symptom_name) {
	this.symptom_name = symptom_name;
}
public String getSymptom_description() {
	return symptom_description;
}
public void setSymptom_description(String symptom_description) {
	this.symptom_description = symptom_description;
}


@Override
public String toString() {
	return "Symptom [symptom_id=" + symptom_id + ", symptom_name="
			+ symptom_name + ", symptom_description=" + symptom_description
			+ "]";
}
   
	
}
