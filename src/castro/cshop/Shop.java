package castro.cshop;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

import castro.cshop.items.base.ShopItem;
import castro.cshop.items.base.ShopItemData;
import castro.cshop.items.base.ShopItemId;
import castro.cshop.utils.Time;

public class Shop implements Runnable
{
	public Shop()
	{
		final int second = 20;
		Plugin.get.scheduleSyncRepeatingTask(this, 3*second, 3*second);
	}
	
	
	public boolean buy(Player player, ShopItemId itemId, long hours, String extra, boolean silent)
	{
		String playername = player.getName();
		//Plugin.get.log("smgr buy " + itemId + " for " + playername + " for " + hours + "h; extra: " + extra);
		 
		// Check constraints
		if(hours > 24*365) // year is enough
			return !Plugin.get.sendMessage(player, "&cToo long time");
		if(!Economy.canAfford(player, itemId, hours))
			return !Plugin.get.sendMessage(player, "&cYou don't have enough cash");
		
		// Do the thing
		try
		{
			ShopItemData item = getOrCreateItem(playername, itemId, hours, extra);
			ShopItem executor = itemId.executor;
			
			if(item.isNew())
			{
				if(!executor.singleUse())
					Plugin.SQL.addItem(playername, itemId, extra, item.expires());
				if(!executor.giveItem(player, item))
				{
					Plugin.SQL.deleteItem(playername, itemId);
					return !Plugin.get.sendMessage(player, "&cCannot give the item. Please contact an administrator");
				}
			}
			else
			{
			    if(!executor.update(player, item))
			        return !Plugin.get.sendMessage(player, "&cCannot give the item. Please contact an administrator");
				Plugin.SQL.updateItem(item);
			}
		}
		catch(SQLException e)
		{
			return !Plugin.get.sendMessage(player, "&cCannot query database. Please contact an administrator");
		}
		
		// Charge players
		if(Economy.charge(playername, itemId, hours) && !silent)
			return Plugin.get.sendMessage(player, "&aThere you go. Charged $" + Economy.getPrice(itemId, hours));
		return Plugin.get.sendMessage(player, "&cSomething wen't wrong while charging. Please contact an administrator");
	}
	
	private ShopItemData getOrCreateItem(String playername, ShopItemId itemId, long hours, String extra) throws SQLException
	{
		ShopItemData item = null;
		if(!itemId.executor.singleUse())
			item = Plugin.SQL.getItem(playername, itemId);
		if(item == null)
			item = new ShopItemData(0, itemId, playername, extra, Time.now());
		item.extra = extra;
		item.extendHours(hours);
		return item;
	}
	
	@Override
	public void run()
	{
		List<ShopItemData> expired = new ArrayList<>();
        try
        {
	        expired = Plugin.SQL.getExpiredItems();
        }
        catch(SQLException e)
        {
	        e.printStackTrace();
        }
        /*
        if(expired.size() > 0)
        	Plugin.get.broadcast("shop run! " + expired.size());
        */
        
        for(ShopItemData itemdata : expired)
			if(itemdata.getExecutor().takeItem(itemdata.owner, itemdata))
			{
				try
				{
					Plugin.SQL.deleteItem(itemdata);
				}
				catch(SQLException e)
				{
					// Nothing. Will try to delete next time
				}
			}
	}
}
