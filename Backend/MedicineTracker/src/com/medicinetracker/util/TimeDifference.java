package com.medicinetracker.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeDifference {
	
	
	public static void main(String args[])
	{
		String data="2018-01-23 10:50:50";
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now = new Date();
		System.out.println("now "+now);
		try {
			int difference = now.compareTo(formatter.parse(data));
			System.out.println("difference "+difference);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
