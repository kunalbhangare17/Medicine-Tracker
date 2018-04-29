package com.example.abc.medicinetracker.entities;

/**
 * Created by developer on 16/3/17.
 */
public class Medicine {

    private int medicine_id;
    private String disease_id;
    private String medicine_name;
    private String disease_name;

    public Medicine(int medicine_id, String medicine_name, String disease_id, String disease_name) {
    this.medicine_id=medicine_id;
        this.    medicine_name=medicine_name;
     this.   disease_id=disease_id;
      this.  disease_name=disease_name;

    }



    public int getMedicine_id() {
        return medicine_id;
    }
    public void setMedicine_id(int medicine_id) {
        this.medicine_id = medicine_id;
    }
    public String getDisease_id() {
        return disease_id;
    }
    public void setDisease_id(String disease_id) {
        this.disease_id = disease_id;
    }
    public String getMedicine_name() {
        return medicine_name;
    }
    public void setMedicine_name(String medicine_name) {
        this.medicine_name = medicine_name;
    }
    public String getDisease_name() {
        return disease_name;
    }
    public void setDisease_name(String disease_name) {
        this.disease_name = disease_name;
    }



}