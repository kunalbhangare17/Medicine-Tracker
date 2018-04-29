package com.medicinetracker.util;

import java.util.Timer;
import java.util.TimerTask;
 
/**
 * Author: Crunchify.com
 */
 
public class CrunchifyTimerTaskExample {
	Timer timer;
 
	public CrunchifyTimerTaskExample(int seconds) {
		timer = new Timer();
		timer.schedule(new CrunchifyReminder(), seconds * 1000);
	}
 
	class CrunchifyReminder extends TimerTask {
		public void run() {
			System.out.format("Timer Task Finished..!%n");
			timer.cancel(); // Terminate the timer thread
		}
	}
 
	public static void main(String args[]) {
		new CrunchifyTimerTaskExample(5);
		System.out.format("Task scheduled.. Now wait for 5 sec to see next message..%n");
	}
}
