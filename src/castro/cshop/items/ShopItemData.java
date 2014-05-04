package castro.cshop.items;

import java.sql.Timestamp;

import castro.cshop.CCommandID;
import castro.cshop.utils.Parser;

public class ShopItemData
{
	public final int        dbId;
	public final CCommandID itemId;
	public final String     owner;
	public String           extra;
	public Timestamp        expires;
	
	public ShopItemData(int dbId, CCommandID itemId, String owner, String extra, Timestamp expires)
    {
		this.dbId    = dbId;
	    this.itemId  = itemId;
	    this.owner   = owner;
	    this.expires = expires;
	    this.extra   = extra;
    }
	
	
	public void extendHours(long extendHours, String extra)
	{
		long extendMillis  = Parser.hoursToMillis(extendHours);
		long oldMillis     = expires.getTime();
		long currentMillis = System.currentTimeMillis();
		if(currentMillis > oldMillis)
			oldMillis = currentMillis;
		expires.setTime(oldMillis + extendMillis);
	}
	
	public boolean expired()
	{
		long expiresMillis = expires.getTime();
		long currentMillis = System.currentTimeMillis();
		return currentMillis > expiresMillis;
	}
}