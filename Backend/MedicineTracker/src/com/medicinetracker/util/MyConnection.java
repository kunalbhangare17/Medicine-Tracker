package com.medicinetracker.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class MyConnection {

	static Connection con = null;
	  public static Connection getConnection()
	  {
		  try
		  {
			  Class.forName("com.mysql.jdbc.Driver");
			  //Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bloodbank","root","P@ssw0rd");
			 Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/medicine_tracker","root","root");
		//	 Connection con = DriverManager.getConnection("jdbc:mysql://5.189.132.169:3306/medicine_tracker","ft","ft123");
			  return con;
		  }
		  catch(Exception e)
		  {
			  e.printStackTrace();
		  }
		  return null;
	  }
}
