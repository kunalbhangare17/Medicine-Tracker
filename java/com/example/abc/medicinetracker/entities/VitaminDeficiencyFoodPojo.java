package com.example.abc.medicinetracker.entities;

public class VitaminDeficiencyFoodPojo {
	
	private long food_id;
	private String vitamin_deficiency;
	private long level_1_id;
	private String food_intake;
	private long level_1_vitamin_id;

	/*public VitaminDeficiencyFoodPojo(long food_id, String vitamin_deficiency, long level_1_vitamin_id, String food_intake, long level_1_id) {
this.food_id=food_id;
this.vitamin_deficiency=vitamin_deficiency;
this.level_1_vitamin_id=level_1_vitamin_id;
this.food_intake=food_intake;
this.level_1_id=level_1_id;





	}*/

	public long getFood_id() {
		return food_id;
	}
	public void setFood_id(long food_id) {
		this.food_id = food_id;
	}
	public String getVitamin_deficiency() {
		return vitamin_deficiency;
	}
	public void setVitamin_deficiency(String vitamin_deficiency) {
		this.vitamin_deficiency = vitamin_deficiency;
	}
	public long getLevel_1_id() {
		return level_1_id;
	}
	public void setLevel_1_id(long level_1_id) {
		this.level_1_id = level_1_id;
	}
	public String getFood_intake() {
		return food_intake;
	}
	public void setFood_intake(String food_intake) {
		this.food_intake = food_intake;
	}
	public long getLevel_1_vitamin_id() {
		return level_1_vitamin_id;
	}
	public void setLevel_1_vitamin_id(long level_1_vitamin_id) {
		this.level_1_vitamin_id = level_1_vitamin_id;
	}
	
	

}
