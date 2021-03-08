package util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {
	
	public static String getTimeStamp() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("ddMMYYYYHHmmssa");
		Date date = new Date();
		String timeStamp = simpleDateFormat.format(date);
		return timeStamp;
	}
}
