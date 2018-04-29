package com.medicinetracker.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.medicinetracker.pojo.PatientPojo;
import com.medicinetracker.pojo.PatientReportPojo;
import com.medicinetracker.util.MyConnection;

public class PatientDaoImpl implements PatientDao {

	Connection con=null;
	@Override
	public int add(PatientPojo patientPojo) {
		try {
			 con = MyConnection.getConnection();
			 PreparedStatement psmt = con.prepareStatement("insert into patient_detail(patient_name,patient_contact_no,patient_email,patient_password,patient_reference,patient_ref_name,patient_ref_email,address) values(?,?,?,?,?,?,?,?)");

			 	psmt.setString(1, patientPojo.getPatient_name());
				psmt.setString(2, patientPojo.getPatient_contact_no());
				psmt.setString(3, patientPojo.getPatient_email());
				psmt.setString(4, patientPojo.getPatient_password());
				psmt.setString(5, patientPojo.getPatient_reference());
				psmt.setString(6, patientPojo.getPatient_ref_name());
				psmt.setString(7, patientPojo.getPatient_ref_email());
				psmt.setString(8, patientPojo.getAddress());
				
				int i = psmt.executeUpdate();
				if (i > 0) {
					return i;
				}
			
		} catch (SQLException e) {

			e.printStackTrace();
		}
		
		return 0;
	}

	@Override
	public PatientPojo login(String patient_email, String patient_password) {
		PatientPojo patientPojo=new PatientPojo();
		try {
			con = MyConnection.getConnection();
			PreparedStatement psmt = con.prepareStatement("select * from patient_detail where patient_email=? and patient_password=?");

				psmt.setString(1, patient_email);
				psmt.setString(2, patient_password);
				
				
				ResultSet rs = psmt.executeQuery();
				
				while(rs.next()) {
					
					
					patientPojo.setPatient_name(rs.getString("patient_name"));
					patientPojo.setPatient_contact_no(rs.getString("patient_contact_no"));
					patientPojo.setPatient_email(rs.getString("patient_email"));
					patientPojo.setPatient_reference(rs.getString("patient_reference"));
					patientPojo.setPatient_ref_name(rs.getString("patient_ref_name"));
					patientPojo.setPatient_ref_email(rs.getString("patient_ref_email"));
					patientPojo.setPatient_id(rs.getInt("patient_id"));
					patientPojo.setAddress(rs.getString("address"));
					patientPojo.setLoggedIn("1");
					
					return patientPojo;
				}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		patientPojo.setLoggedIn("0");
		return patientPojo;
	}

	@Override
	public boolean AddReport(PatientReportPojo patientReportPojo) {
		// TODO Auto-generated method stub
		try {
			 con = MyConnection.getConnection();
			 PreparedStatement psmt = con.prepareStatement("insert into patient_report(user_email,date,systolic,diastolic,glucose,aic,total_cholestrol,bmi) values(?,?,?,?,?,?,?,?)");

			 	psmt.setString(1, patientReportPojo.getUser_email());
				psmt.setString(2, patientReportPojo.getDate());
				psmt.setDouble(3, patientReportPojo.getSystolic());
				psmt.setDouble(4, patientReportPojo.getDiastolic());
				psmt.setDouble(5, patientReportPojo.getGlucose());
				psmt.setDouble(6, patientReportPojo.getAic());
				psmt.setDouble(7, patientReportPojo.getTotal_cholestrol());
				psmt.setDouble(8, patientReportPojo.getBmi());
				
				int i = psmt.executeUpdate();
				if (i > 0) {
					return true;
				}
				else{
					return false;
				}
			
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<PatientReportPojo> getPatientReport(String user_email) {
		// TODO Auto-generated method stub
		try {
			con = MyConnection.getConnection();
			PreparedStatement psmt = con.prepareStatement("select * from patient_report where user_email=?");

				psmt.setString(1, user_email);
				
				
				ResultSet rs = psmt.executeQuery();
				List<PatientReportPojo> list=new ArrayList<PatientReportPojo>();
				while(rs.next()) {
					PatientReportPojo patientPojo=new PatientReportPojo();
					patientPojo.setId(rs.getLong("id"));
					patientPojo.setUser_email(rs.getString("user_email"));
					patientPojo.setDate(rs.getString("date"));
					patientPojo.setSystolic(rs.getDouble("systolic"));
					patientPojo.setDiastolic(rs.getDouble("diastolic"));
					patientPojo.setGlucose(rs.getDouble("glucose"));
					patientPojo.setAic(rs.getDouble("aic"));
					patientPojo.setTotal_cholestrol(rs.getDouble("total_cholestrol"));
					patientPojo.setBmi(rs.getDouble("bmi"));
					
					
					list.add(patientPojo);
					
				}
			
				return list;
						
		} catch (Exception e) {

			e.printStackTrace();
		}
		return null;
	}

}
