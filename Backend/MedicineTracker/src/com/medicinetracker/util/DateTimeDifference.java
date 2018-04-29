package com.medicinetracker.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeDifference {

	
	/*1000 milliseconds = 1 second
			60 seconds = 1 minute
			60 minutes = 1 hour
			24 hours = 1 day*/
	
	
	public static long getTimeDifference(String dateStart, String dateStop)
	{
		
		dateStart = "01/14/2012 09:29:58";
		dateStop = "01/15/2012 10:31:48";

		//HH converts hour in 24 hours format (0-23), day calculation
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

		Date d1 = null;
		Date d2 = null;
		
		long diffMinutes = 0;

		try {
			d1 = format.parse(dateStart);
			d2 = format.parse(dateStop);

			//in milliseconds
			long diff = d2.getTime() - d1.getTime();
			System.out.println("DDdiff:: "+diff);

			long diffSeconds = diff / 1000 % 60;
			diffMinutes = diff / (60 * 1000) % 60;
			long diffHours = diff / (60 * 60 * 1000) % 24;
			long diffDays = diff / (24 * 60 * 60 * 1000);

			System.out.print(diffDays + " days, ");
			System.out.print(diffHours + " hours, ");
			System.out.print(diffMinutes + " minutes, ");
			System.out.print(diffSeconds + " seconds.");

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return diffMinutes;
		
	}
	
	public static void main(String[] args) {

		//getTimeDifference("01/14/2012 09:29:58",  "01/15/2012 10:31:48");
		System.out.println("Difference:: "+getTimeDifference("01/14/2012 09:29:58",  "01/15/2012 10:31:48"));

	}

}
