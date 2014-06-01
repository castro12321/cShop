package castro.cshop.utils;

import java.sql.Timestamp;

import castro.cshop.Plugin;

public class Time
{
	public static Timestamp now()
	{
		return new Timestamp(System.currentTimeMillis());
	}
	
	public static Timestamp zero()
	{
		return new Timestamp(0);
	}
	
	public static Timestamp add(Timestamp time, long hours)
	{
		long extendMillis  = Parser.hoursToMillis(hours);
		long oldMillis     = time.getTime();
		long currentMillis = System.currentTimeMillis();
		if(currentMillis > oldMillis)
			oldMillis = currentMillis;
		time.setTime(oldMillis + extendMillis);
		return time;
	}
}