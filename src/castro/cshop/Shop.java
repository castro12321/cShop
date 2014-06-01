package castro.cshop;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

import castro.cshop.items.base.ShopItem;
import castro.cshop.items.base.ShopItemData;
import castro.cshop.utils.Time;

public class Shop implements Runnable
{
	public Shop()
	{
		final int second = 20;
		Plugin.get.scheduleSyncRepeatingTask(this, 3*second, 3*second);
	}
	
	public boolean buy(Player player, CCommandID itemId, long hours, String extra)
	{
		String playername = player.getName();
		
		Plugin.get.log("smgr buy " + itemId + " for " + playername + " for " + hours + "h; extra: " + extra);
		
		if(!Economy.canAfford(playername, itemId, hours))
			return !Plugin.get.sendMessage(player, "&cYou don't have enough cash");
		
		ShopItemData item = getOrCreateItem(playername, itemId, hours, extra);
		if(item == null)
			return Plugin.get.sendMessage(playername, "&cGot error while querying for item. Please contact an administrator.");
		
		ShopItem executor = itemId.executor;
		if(executor.singleUse())
		{
			Plugin.get.log("Single use item!");
			if(!executor.giveItem(player, item))
				return !Plugin.get.sendMessage(player, "&cCannot give the item. Please contact an administrator");
		}
		else if(item.isNew())
		{
			Plugin.get.log("no previous item. Creating one");
			
			if(!Plugin.SQL.addItem(playername, itemId, extra, item.expires()))
				return !Plugin.get.sendMessage(player, "&cCannot query database. Please contact an administrator");
			if(!executor.giveItem(player, item))
			{
				if(!Plugin.SQL.deleteItem(playername, itemId))
					Plugin.get.sendMessage(player, "&cCannot query delete from database. Please contact an administrator");
				return !Plugin.get.sendMessage(player, "&cCannot give the item. Please contact an administrator");
			}
		}
		else
		{
			Plugin.get.log("Updating item");
			if(Plugin.SQL.updateItem(item))
				executor.update(player, item);
			else
				return !Plugin.get.sendMessage(player, "&cCannot query database. Please contact an administrator");
		}
		
		if(Economy.charge(playername, itemId, hours))
			return Plugin.get.sendMessage(player, "&aThere you go");
		return Plugin.get.sendMessage(player, "&cSomething wen't wrong while charging. Please contact an administrator");
	}
	
	private ShopItemData getOrCreateItem(String playername, CCommandID itemId, long hours, String extra)
	{
		ShopItemData item = null;
		
		if(!itemId.executor.singleUse())
		{
			try
			{
				item = Plugin.SQL.getItem(playername, itemId);
			}
			catch(SQLException e)
			{
				e.printStackTrace();
				return null;
			}
		}
		
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
        if(expired.size() > 0)
        	Plugin.get.broadcast("shop run! " + expired.size());
        
        for(ShopItemData itemdata : expired)
			if(itemdata.getExecutor().takeItem(itemdata.owner, itemdata))
				Plugin.SQL.deleteItem(itemdata);
	}
}
