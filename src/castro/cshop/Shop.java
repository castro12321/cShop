package castro.cshop;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

import castro.cshop.items.base.ShopItemData;
import castro.cshop.utils.Time;

public class Shop implements Runnable
{
	public Shop()
	{
		final int second = 20;
		Plugin.get.scheduleSyncRepeatingTask(this, 3*second, 3*second);
	}
	
	public void buy(Player player, CCommandID itemId, long hours, String extra) throws SQLException
	{
		ShopItemData item = getItem(player, itemId);
		if(item != null)
			itemId.executor.extendHours(player, item, hours);
		else
		{
			Plugin.SQL.addItem(player.getName(), itemId, extra, Time.add(Time.now(), hours));
			item = Plugin.SQL.getItem(player.getName(), itemId);
			item.getExecutor().giveItem(player, item);
		}
	}
	
	
	private ShopItemData getItem(Player player, CCommandID id) throws SQLException
	{
		return Plugin.SQL.getItem(player.getName(), id);
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
				try
		        {
			        Plugin.SQL.deleteItem(itemdata);
		        }
		        catch(SQLException e)
		        {
			        e.printStackTrace();
		        }
	}
}
