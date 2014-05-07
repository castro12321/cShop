package castro.cshop;

import java.sql.SQLException;
import java.sql.Timestamp;

import org.bukkit.entity.Player;

import castro.cshop.items.ShopItem;
import castro.cshop.items.ShopItemData;
import castro.cshop.utils.Time;

public class Shop
{
	public void buy(Player player, CCommandID itemId, long hours, String extra)
	{
		ShopItemData item = getItem(player, itemId);
		if(item != null)
			item.extendHours(hours, extra);
		else
		{
			//public void addItem(String player, CCommandID item, String extra, Timestamp expires) throws SQLException
			item = Plugin.SQL.addItem(player.getName(), itemId, extra, Time.add(Time.now(), hours));
		}
	}
	
	
	private ShopItemData getItem(Player player, CCommandID id) throws SQLException
	{
		return Plugin.SQL.getItem(player.getName(), id);
	}
}
