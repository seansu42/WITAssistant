package application;

import java.time.LocalDateTime;
import java.util.HashMap;

// TODO: Ask if it makes sense in terms of organization to have static methods along with object methods in same class


public class EventTime implements Comparable, CurrentTime {
	private int hour;
	private int minute;

	private static int currentMonth;
	private static int currentYear;
	private static int currentDay;
	private static String currentDate;

	public EventTime(int H, int M) {
		hour = H;
		minute = M;
	}
	
	public EventTime() {
		hour = 0;
		minute = 0;
	}
	
	public void setHour(int h) {
		hour = h;
	}
	
	public void setHour(String h) {
		hour = Integer.parseInt(h);
	}
	
	public void setMinutes(int min) {
		minute = min;
	}
	
	public void setMinutes(String min) {
		minute = Integer.parseInt(min);
	}
	
	
	
	
	public String getTimeValue() {
		return(hour + ":" + minute);
	}
	
	public static boolean isValidTime(String timeValue) {
		if(timeValue.length() == 4) {
			
		}
		return false;
	}
	
	public static void nextMonth() {
		if(currentMonth == 12) {
			currentMonth = 1;
			currentYear++;
		}
		else {
			currentMonth++;
		}
		setLeapYear();
	}
	
	public static void previousMonth() {
		if(currentMonth == 1) {
			currentMonth = 12;
			currentYear--;
		}
		else {
			currentMonth--;
		}
		setLeapYear();
	}

	private static void setLeapYear() {
		if(isLeap(currentYear)) {
			numDays[1] = 29;
		}
		else {
			numDays[1] = 28;
		}
		
	}
	
	
	public static void initializeDate() {
		currentMonth = LocalDateTime.now().getMonthValue();
		currentYear = LocalDateTime.now().getYear();
		currentDay = 1;
	}

	private static boolean isLeap(int year) {
		if (year % 4 == 0) {
			if (year % 100 == 0) {
				if (year % 400 == 0) {
					return true;
				} else {
					return false;
				}
			} else {
				// leap
				return true;
			}
		} else {
			// not leap
			return false;
		}
	}
	
	public static String getMonthName() {
		return(months[currentMonth - 1]);
	}
	
	public static int getMonthNumber() {
		return currentMonth;
	}
	
	public static int getNumDays() {
		return(numDays[currentMonth - 1]);
	}
	
	public static int getYear() {
		return currentYear;
	}
	
	public static void setDay(int dayNum) {
		currentDay = dayNum;
	}
	
	public static String getDate() {
		currentDate = (currentMonth + "/" + currentDay + "/" + currentYear);
		return currentDate;
	}
	
	@Override
	public int compareTo(Object arg0) {
		return 0;
	}
	
	
	
}
