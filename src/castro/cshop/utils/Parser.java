package castro.cshop.utils;

import castro.base.plugin.CUtils;

public class Parser
{
	private static final long millis = 1;
	private static final long second = 1000*millis;
	private static final long minute = 60*second;
	private static final long hour   = 60*minute;
	//private static final long day    = 24*hour;
	
	
	// Works with days and hours.
	// @param time like "1h", "6h", "5d", "24h", "30h"
	// @returns time in hours (or 0 if wrong time)
	public static int parseHours(String time)
	{
		try
		{
			String multiplier = time.substring(time.length()-1);
			String timeString = time.substring(0, time.length()-2);
			int    timeValue  = CUtils.convert(timeString, Integer.class, 0);
			switch(multiplier)
			{
			case "h": return timeValue;
			case "d": return timeValue * 24;
			}
		}
		catch(Exception e) // Shit happens, especially when parsing user input.
		{}
		return 0;
		
	}
	
	public static long hoursToMillis(long hours)
	{
		return millis * hour;
	}
	
	public static long millisToHours(long millis)
	{
		return millis / hour;
	}
}
