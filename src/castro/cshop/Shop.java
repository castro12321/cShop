package castro.cshop;

import java.sql.SQLException;

import org.bukkit.entity.Player;

import castro.cshop.items.ShopItemData;
import castro.cshop.utils.Time;

public class Shop
{
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
}
