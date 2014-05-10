package castro.cshop.items;

import java.sql.SQLException;
import java.sql.Timestamp;

import castro.cshop.CCommandID;
import castro.cshop.Plugin;
import castro.cshop.utils.Time;

public class ShopItemData
{
	public final int        dbId;
	public final CCommandID itemId;
	public final String     owner;
	private String          extra;
	private Timestamp       expires;
	
	public ShopItemData(int dbId, CCommandID itemId, String owner, String extra, Timestamp expires)
    {
		this.dbId    = dbId;
	    this.itemId  = itemId;
	    this.owner   = owner;
	    this.expires = expires;
	    this.extra   = extra;
    }
	
	public String getExtra()
	{
		return extra;
	}
	
	public void setExtra(String extra) throws SQLException
	{
		this.extra = extra;
		Plugin.SQL.updateItem(this);
	}
	
	public Timestamp getExpires()
	{
		return expires;
	}
	
	public void extendHours(long extendHours) throws SQLException
	{
		Time.add(expires, extendHours);
		Plugin.SQL.updateItem(this);
	}
	
	public boolean expired()
	{
		long expiresMillis = expires.getTime();
		long currentMillis = System.currentTimeMillis();
		return currentMillis > expiresMillis;
	}
}