package com.medicinetracker.dao;

import java.util.List;

import com.medicinetracker.pojo.LocationMasterPojo;

public interface LocationDao {

	public List<LocationMasterPojo> getAllNearbyParkingArea(double latitude,double longitude);
}
