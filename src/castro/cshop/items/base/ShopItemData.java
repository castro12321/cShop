package castro.cshop.items.base;

import java.sql.Timestamp;

import castro.cshop.CCommandID;
import castro.cshop.utils.Time;

public class ShopItemData
{
	public final int        dbId;
	public final CCommandID itemId;
	public final String     owner;
	public String          extra;
	private Timestamp       expires;
	
	public ShopItemData(int dbId, CCommandID itemId, String owner, String extra, Timestamp expires)
    {
		this.dbId    = dbId;
	    this.itemId  = itemId;
	    this.owner   = owner;
	    this.expires = expires;
	    this.extra   = extra;
    }
	
	public Timestamp expires()
	{
		return expires;
	}
	
	public ShopItem getExecutor()
	{
		return itemId.executor;
	}
	
	public void extendHours(long extendHours)
	{
		expires = Time.add(expires, extendHours);
	}
	
	public boolean expired()
	{
		long expiresMillis = expires.getTime();
		long currentMillis = System.currentTimeMillis();
		return currentMillis > expiresMillis;
	}
}