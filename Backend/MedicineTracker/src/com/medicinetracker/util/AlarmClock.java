package com.medicinetracker.util;

public class AlarmClock {

private int hour;
private int minute;
private String amOrPm;
private int alarmHour;
private int alarmMinute;
private String alarmAmOrPm;
private boolean isAlarmRinging;

//constructor:
AlarmClock() {
hour = 12;
alarmHour = 12;
minute = 0;
alarmMinute = 0;
alarmAmOrPm = new String();
amOrPm = new String();
isAlarmRinging = false;
} 

//getters:
public int getHour() {
if ((hour >= 1) && (hour <= 12)) {
}
return hour;
}

public int getMinute() {
if ((minute >= 0) && (minute <= 59)) {
}
return minute;
}

public String getAmOrPM() {
return amOrPm;
}

public int getAlarmHour() {
return alarmHour;
}

public int getAlarmMinute() {
return alarmMinute;
}

public String getAlarmAmOrPm() {
return alarmAmOrPm;
}

public boolean isIsAlarmRinging() {
if (hour >= 12){
isAlarmRinging = true;
}
return isAlarmRinging;
}

//setters
public void setHour(int newHour) {
if ((newHour >= 1) && (newHour <= 12)) {
    hour = newHour;
}
}

public void setMinute(int newMinute) {
if ((newMinute >= 0) && (newMinute <= 59)) {
    minute = newMinute;
}
}

public void setAmOrPM(String newAmOrPm) {
amOrPm = newAmOrPm;
}

public void setAlarmHour(int newAlarmHour) {
if ((newAlarmHour >= 1) && (newAlarmHour <= 12)) {
    alarmHour = newAlarmHour;
}
}

public void setAlarmMinute(int newAlarmMinute) {
if ((newAlarmMinute >= 0) && (newAlarmMinute <= 59)) {
    alarmMinute = newAlarmMinute;
}
}

public void setAlarmAmOrPm(String newAlarmAmOrPm) {
alarmAmOrPm = newAlarmAmOrPm;
}

public void setIsAlarmRinging(boolean isAlarmRinging) {
if (isAlarmRinging = true) {
    System.out.println("Ringing!");
}
}

public void advanceOneMinute() {
minute++;
if (minute == 59) {
    hour++;
}
}

public void advanceMinutes(int minutesToAdvance) {

}

public void advanceOneHour() {
hour++;
if (hour > 12) {
hour = 1;
}    
}

public void advanceHours(int hoursToAdvance) {

}

public void setTime(int newHour, int newMinute, String newAmOrPm) {
hour = newHour;
amOrPm = newAmOrPm;
}

public void setAlarmTime(int newAlarmHour, int newAlarmMinute, String newAlarmAmOrPm) {

if(hour >=0 && hour <= 23 && minute >=0 && minute <= 59) { 
newAlarmHour = hour; 
newAlarmMinute = minute;
} 
}

public void turnOffAlarm() {
isAlarmRinging = false;
}

public void displayTime() {

}

public void displayAlarmTime() {

}




public static void main(String[] args) {
AlarmClock alarm = new AlarmClock();
System.out.println(alarm.getHour() + ":" + alarm.getMinute() + alarm.getAmOrPM());
System.out.println(alarm.getAlarmHour() + ":" + alarm.getAlarmMinute() + alarm.getAlarmAmOrPm());
System.out.println(alarm.isIsAlarmRinging());
}
} 


