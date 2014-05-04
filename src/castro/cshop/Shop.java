package castro.cshop;

import org.bukkit.entity.Player;

import castro.cshop.items.ShopItem;
import castro.cshop.items.ShopItemData;

public class Shop
{
	public void buy(Player player, CCommandID itemId, long hours, String extra)
	{
		ShopItemData item = getItem(player, itemId);
		
		item.extendHours(hours, extra);
	}
	
	
	private ShopItemData getItem(Player player, CCommandID id)
	{
		ShopItem item = Plugin.SQL;
		return item;
	}
}
