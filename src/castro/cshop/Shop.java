package castro.cshop;

import java.sql.SQLException;
import java.sql.Timestamp;

import org.bukkit.entity.Player;

import castro.cshop.items.ShopItem;
import castro.cshop.items.ShopItemData;
import castro.cshop.utils.Time;

public class Shop
{
	public void buy(Player player, CCommandID itemId, long hours, String extra) throws SQLException
	{
		ShopItemData item = getItem(player, itemId);
		if(item != null)
			itemId.item.extendHours(player, item, hours);
		else
		{
			//public void addItem(String player, CCommandID item, String extra, Timestamp expires) throws SQLException
			Plugin.SQL.addItem(player.getName(), itemId, extra, Time.add(Time.now(), hours));
			item = Plugin.SQL.getItem(player.getName(), itemId);
			item.buy();
		}
	}
	
	
	private ShopItemData getItem(Player player, CCommandID id) throws SQLException
	{
		return Plugin.SQL.getItem(player.getName(), id);
	}
}
