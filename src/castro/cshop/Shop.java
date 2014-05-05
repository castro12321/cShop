package castro.cshop;

import org.bukkit.entity.Player;

import castro.cshop.items.ShopItem;
import castro.cshop.items.ShopItemData;

public class Shop
{
	public void buy(Player player, CCommandID itemId, long hours, String extra)
	{
		ShopItemData item = getItem(player, itemId);
		if(item == null)
			item = Plugin.SQL.addItem();
		item.extendHours(hours, extra);
	}
	
	
	private ShopItemData getItem(Player player, CCommandID id)
	{
		return Plugin.SQL.getItem(player, id);
	}
}
