package soot.jimple.apkpler.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil 
{
	public static String toReadableTime(long timeInMillis)
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date(timeInMillis);
		return dateFormat.format(date);
	}
	
	public static long computeTimeDifferenceInSeconds(long beginTimeInMillis, long endTimeInMillis)
	{
		long timeDiff = endTimeInMillis - beginTimeInMillis;
		
		return timeDiff / 1000;
	}
}
