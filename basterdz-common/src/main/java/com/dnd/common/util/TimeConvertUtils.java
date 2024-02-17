package com.dnd.common.util;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TimeConvertUtils {

	public static int convertTimeStringToMinutes(String time) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
		LocalTime localTime = LocalTime.parse(time, formatter);
		return localTime.getHour() * 60 + localTime.getMinute();
	}

	public static String convertMinutesToTimeString(int minutes) {
		int hours = minutes / 60;
		minutes %= 60;
		return String.format("%02d:%02d", hours, minutes);
	}
}
