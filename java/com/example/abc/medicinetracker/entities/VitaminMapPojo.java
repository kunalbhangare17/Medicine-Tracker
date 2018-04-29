package com.example.abc.medicinetracker.entities;

import android.view.View;

public class VitaminMapPojo {

	private long level_1_vitamin_id;
	private long level_1_id;
	private String level_1_name;
	private String vitamin_deficiency;
	public VitaminMapPojo(long level_1_vitamin_id, long level_1_id, String level_1_name, String vitamin_deficiency ) {
		this.level_1_vitamin_id = level_1_vitamin_id;
		this.level_1_id = level_1_id;
		this.level_1_name = level_1_name;
		this.vitamin_deficiency = vitamin_deficiency;
	}

	public VitaminMapPojo(View layoutView) {

	}

	public long getLevel_1_vitamin_id() {
		return level_1_vitamin_id;
	}
	public void setLevel_1_vitamin_id(long level_1_vitamin_id) {
		this.level_1_vitamin_id = level_1_vitamin_id;
	}
	public long getLevel_1_id() {
		return level_1_id;
	}
	public void setLevel_1_id(long level_1_id) {
		this.level_1_id = level_1_id;
	}
	public String getLevel_1_name() {
		return level_1_name;
	}
	public void setLevel_1_name(String level_1_name) {
		this.level_1_name = level_1_name;
	}
	public String getVitamin_deficiency() {
		return vitamin_deficiency;
	}
	public void setVitamin_deficiency(String vitamin_deficiency) {
		this.vitamin_deficiency = vitamin_deficiency;
	}
	
	
	
}
