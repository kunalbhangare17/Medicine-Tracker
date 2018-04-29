package com.medicinetracker.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.medicinetracker.pojo.MedicinePojo;
import com.medicinetracker.util.MyConnection;


public class MedicineDaoImpl implements MedicineDao {
	
	Connection con=null;

	@Override
	public int add(MedicinePojo medicinePojo) 
	{
		try 
		{
			con = MyConnection.getConnection();
			 PreparedStatement psmt = con.prepareStatement("insert into medicine_prescription(doctor_name,medicine_name,morning_time_medicine,afternoon_time_medicine,night_time_medicine,patient_id,patient_email,patient_ref_email,role,days,notify,appointment_date) values(?,?,?,?,?,?,?,?,?,?,?,curdate())");

			 	psmt.setString(1, medicinePojo.getDoctor_name());
				psmt.setString(2, medicinePojo.getMedicine_name());
				psmt.setString(3, medicinePojo.getMorning_time_medicine());
				psmt.setString(4, medicinePojo.getAfternoon_time_medicine());
				psmt.setString(5, medicinePojo.getNight_time_medicine());
				psmt.setInt(6, medicinePojo.getPatient_id());
				psmt.setString(7, medicinePojo.getPatient_email());
				psmt.setString(8, medicinePojo.getPatient_ref_email());
				psmt.setString(9, medicinePojo.getRole());
				psmt.setInt(10, medicinePojo.getDays());
				psmt.setInt(11, medicinePojo.getNotify());
				
				int i = psmt.executeUpdate();
				if (i > 0) {
					return i;
				}
			
		} 
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<MedicinePojo> addMoreMedicines(String patient_email) {
		// TODO Auto-generated method stub
				
		try {
			con = MyConnection.getConnection();
			PreparedStatement psmt = con.prepareStatement("select * from medicine_prescription where patient_email=?");
			
			psmt.setString(1, patient_email);
						
				ResultSet rs=psmt.executeQuery();
				
				List<MedicinePojo> medicineList=new ArrayList<MedicinePojo>();
				while(rs.next())
				{
					MedicinePojo medicinePojo= new MedicinePojo();
					medicinePojo.setDoctor_name(rs.getString("doctor_name"));
					medicinePojo.setMedicine_name(rs.getString("medicine_name"));
					medicinePojo.setMorning_time_medicine(rs.getString("morning_time_medicine"));
					medicinePojo.setAfternoon_time_medicine(rs.getString("afternoon_time_medicine"));
					medicinePojo.setNight_time_medicine(rs.getString("night_time_medicine"));
					medicinePojo.setPatient_id(rs.getInt("patient_id"));
					medicinePojo.setPatient_email(rs.getString("patient_email"));
					medicinePojo.setPatient_ref_email(rs.getString("patient_ref_email"));
					medicinePojo.setRole(rs.getString("role"));
					medicinePojo.setAppointment_date(rs.getString("appointment_date"));
					
					medicineList.add(medicinePojo);
				}
				return medicineList;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<MedicinePojo> viewNotify(int notify) {
		// TODO Auto-generated method stub
				
		try {
			con = MyConnection.getConnection();
			PreparedStatement psmt = con.prepareStatement("select * from medicine_prescription where notify=?");
			
			psmt.setInt(1, notify);
						
				ResultSet rs=psmt.executeQuery();
				
				List<MedicinePojo> notifyList=new ArrayList<MedicinePojo>();
				while(rs.next())
				{
					MedicinePojo medicinePojo= new MedicinePojo();
					medicinePojo.setDoctor_name(rs.getString("doctor_name"));
					medicinePojo.setMedicine_name(rs.getString("medicine_name"));
					medicinePojo.setMorning_time_medicine(rs.getString("morning_time_medicine"));
					medicinePojo.setAfternoon_time_medicine(rs.getString("afternoon_time_medicine"));
					medicinePojo.setNight_time_medicine(rs.getString("night_time_medicine"));
					medicinePojo.setPatient_id(rs.getInt("patient_id"));
					medicinePojo.setPatient_email(rs.getString("patient_email"));
					medicinePojo.setPatient_ref_email(rs.getString("patient_ref_email"));
					medicinePojo.setRole(rs.getString("role"));
					medicinePojo.setAppointment_date(rs.getString("appointment_date"));
					medicinePojo.setDays(rs.getInt("days"));
					medicinePojo.setNotify(rs.getInt("notify"));
					
					notifyList.add(medicinePojo);
				}
				return notifyList;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<MedicinePojo> getNotificationByPatient(String patient_email,
			String date) {
		// TODO Auto-generated method stub
		try {
			con = MyConnection.getConnection();
			PreparedStatement psmt = con.prepareStatement("select * from medicine_prescription where patient_email=? and appointment_date=?");
			
			psmt.setString(1, patient_email);
			psmt.setString(2, date);
						
				ResultSet rs=psmt.executeQuery();
				
				List<MedicinePojo> notifyList=new ArrayList<MedicinePojo>();
				while(rs.next())
				{
					MedicinePojo medicinePojo= new MedicinePojo();
					medicinePojo.setDoctor_name(rs.getString("doctor_name"));
					medicinePojo.setMedicine_name(rs.getString("medicine_name"));
					medicinePojo.setMorning_time_medicine(rs.getString("morning_time_medicine"));
					medicinePojo.setAfternoon_time_medicine(rs.getString("afternoon_time_medicine"));
					medicinePojo.setNight_time_medicine(rs.getString("night_time_medicine"));
					medicinePojo.setPatient_id(rs.getInt("patient_id"));
					medicinePojo.setPatient_email(rs.getString("patient_email"));
					medicinePojo.setPatient_ref_email(rs.getString("patient_ref_email"));
					medicinePojo.setRole(rs.getString("role"));
					medicinePojo.setAppointment_date(rs.getString("appointment_date"));
					medicinePojo.setDays(rs.getInt("days"));
					medicinePojo.setNotify(rs.getInt("notify"));
					
					notifyList.add(medicinePojo);
				}
				return notifyList;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/*@Override
	public int add(String[][] medicine) {
		try {
			 con = MyConnection.getConnection();
			 PreparedStatement ps = con.prepareStatement("insert into medicine_prescription(doctor_name,medicines,morning_time_medicine,afternoon_time_medicine,night_time_medicine,syrup,morning_time_syrup,afternoon_time_syrup,night_time_syrup,patient_id,patient_email,patient_ref_email,appointment_date) values(?,?,?,?,?,?,?,?,?,?,?,?,curdate())");

			 	psmt.setString(1, patientPojo.getPatient_name());
				psmt.setString(2, patientPojo.getPatient_contact_no());
				psmt.setString(3, patientPojo.getPatient_email());
				psmt.setString(4, patientPojo.getPatient_password());
				psmt.setString(5, patientPojo.getPatient_reference());
				psmt.setString(6, patientPojo.getPatient_ref_name());
				psmt.setString(7, patientPojo.getPatient_ref_email());
				
				int i = psmt.executeUpdate();
				if (i > 0) {
					return i;
				}
			 
			 int k=0;
				//System.out.println(qualification[0].length);
				if(medicine[0]!=null)
				{
					for(int i=0;i<medicine[0].length;i++)
					{   
						ps.setString(1,medicine[k][i]);
						ps.setString(2,medicine[++k][i]);
						ps.setString(3,medicine[++k][i]);
						ps.setString(4,medicine[++k][i]);
						ps.setString(5,medicine[++k][i]);
						ps.setString(6,medicine[++k][i]);
						ps.setString(7,medicine[++k][i]);
						ps.setString(8,medicine[++k][i]);
						ps.setString(9,medicine[++k][i]);
						ps.setInt(10, Integer.parseInt(medicine[++k][i]));
						ps.setString(11,medicine[++k][i]);
						ps.setString(12, medicine[++k][i]);
						//ps.setString(13,medicine[++k][i]);
						ps.executeUpdate();
						
						k=0;
					}
				}
				return 1;
			
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return 0;
	}*/

}
