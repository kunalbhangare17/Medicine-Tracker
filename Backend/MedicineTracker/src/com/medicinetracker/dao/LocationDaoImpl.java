package com.medicinetracker.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;



import com.medicinetracker.pojo.LocationMasterPojo;
import com.medicinetracker.util.MyConnection;

public class LocationDaoImpl implements LocationDao {

	
	@Override
	public List<LocationMasterPojo> getAllNearbyParkingArea(double latitude,
			double longitude) {
		// TODO Auto-generated method stub
Connection conn=null;
		
		//String constant= Config.SERVEFETCHURL;
		
		try 
		{
			conn = MyConnection.getConnection();
			List<LocationMasterPojo> deviceInofList = new ArrayList<LocationMasterPojo>();
			String sqlQuery = "SELECT  z.location_master_id,z.blood_bank_name,z.bank_description,z.contact_details,z.date,z.time,z.sub_area, z.location_id, z.location,z.location_latitude,z.location_longitude, p.distance_unit * DEGREES(ACOS(COS(RADIANS(p.latpoint)) * COS(RADIANS(z.location_latitude))  * COS(RADIANS(p.longpoint) - RADIANS(z.location_longitude))+ SIN(RADIANS(p.latpoint))* SIN(RADIANS(z.location_latitude)))) AS distance_in_km FROM location_master AS z JOIN (   /* these are the query parameters */ SELECT ? AS latpoint,? AS longpoint, 10.0 AS radius, 90.045 AS distance_unit ) AS p ON 1=1 WHERE z.location_latitude  BETWEEN p.latpoint  - (p.radius / p.distance_unit) AND p.latpoint  + (p.radius / p.distance_unit) AND z.location_longitude BETWEEN p.longpoint - (p.radius / (p.distance_unit * COS(RADIANS(p.latpoint)))) AND p.longpoint + (p.radius / (p.distance_unit * COS(RADIANS(p.latpoint)))) ORDER BY distance_in_km ASC LIMIT 4";
			System.out.println("sqlQuery::: "+sqlQuery);
			//String sqlQuery ="SELECT latitude, longitude, distance FROM (SELECT latitude, z.longitude,p.radius,p.distance_unit * DEGREES(ACOS(COS(RADIANS(p.latpoint))* COS(RADIANS(z.latitude))* COS(RADIANS(p.longpoint - z.latitude))+ SIN(RADIANS(p.latpoint))* SIN(RADIANS(z.latitude)))) AS distance FROM map AS z JOIN (   /* these are the query parameters */ SELECT  ?  AS latpoint,  ? AS longpoint,50.0 AS radius,      111.045 AS distance_unit) AS p ON 1=1 WHERE z.latitude BETWEEN p.latpoint  - (p.radius / p.distance_unit) AND p.latpoint  + (p.radius / p.distance_unit) AND z.longitude BETWEEN p.longpoint - (p.radius / (p.distance_unit * COS(RADIANS(p.latpoint)))) AND p.longpoint + (p.radius / (p.distance_unit * COS(RADIANS(p.latpoint))))) AS d WHERE distance <= radius ORDER BY distance LIMIT 15";
			
			PreparedStatement ps= (PreparedStatement) conn.prepareStatement(sqlQuery);
			ps.setDouble(1, latitude);
			ps.setDouble(2, longitude);

			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				LocationMasterPojo parkingPojo = new LocationMasterPojo();
				
				parkingPojo.setLocation_master_id(rs.getInt("location_master_id"));
				parkingPojo.setLocation_latitude(rs.getDouble("location_latitude"));
				parkingPojo.setLocation_longitude(rs.getDouble("location_longitude"));
				parkingPojo.setLocation(rs.getString("location"));
				parkingPojo.setSub_area(rs.getString("sub_area"));
				parkingPojo.setLocation_id(rs.getInt("location_id"));
				parkingPojo.setBlood_bank_name(rs.getString("blood_bank_name"));
				parkingPojo.setBank_description(rs.getString("bank_description"));
				parkingPojo.setContact_details(rs.getString("contact_details"));
				parkingPojo.setDate(rs.getString("date"));
				parkingPojo.setTime(rs.getString("time"));
				
				deviceInofList.add(parkingPojo);
			}
			return deviceInofList;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}

}
