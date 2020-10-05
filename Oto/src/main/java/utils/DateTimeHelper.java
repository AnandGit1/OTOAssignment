package utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class DateTimeHelper {

	public static String getCurrentDateTime() {

		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
		DateFormat dateFormat1 = new SimpleDateFormat("MMM dd yyyy");
		Calendar cal = Calendar.getInstance();
		String time = "" + dateFormat.format(cal.getTime());
		return time;
	}
	
	public static String getCurrentDate() {
		
		DateFormat dateFormat = new SimpleDateFormat("MMM dd yyyy");
		Calendar cal = Calendar.getInstance();
		String time = "" + dateFormat.format(cal.getTime());
		return time;
	}

	public static String getCurrentDate(String format) {
		
		String dateformat=null;
		
		switch(format)
		{
		
		case "date":
			dateformat= getCurrentDateTime().substring(0, 9);
			break;
		case "datetime":
			dateformat= getCurrentDateTime().substring(0, 13);
			break;
		case "fulldatetime":
			dateformat= getCurrentDateTime();
			break;
		case "Approver":
			dateformat= getCurrentDate();
			break;
		}
		return dateformat;
	}

}
