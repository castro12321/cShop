package castro.cshop.items;

import org.bukkit.entity.Player;

import castro.cshop.Plugin;
import castro.cshop.items.base.ShopItem;
import castro.cshop.items.base.ShopItemData;

public class VIP extends ShopItem
{
	public final static String VIP_RANK = "vip";
	
	@Override
    public float getPricePerHour()
    {
	    final float perMonth = 5000;
	    final float perDay   = perMonth / 30;
	    return perDay / 24;
    }
	
	
	@Override
    public boolean giveItem(Player player, ShopItemData itemData)
    {
		return Plugin.permissions.playerAddGroup(player, VIP_RANK);
    }

	@Override
    public boolean takeItem(String playername, ShopItemData itemData)
    {
		return Plugin.permissions.playerRemoveGroup((String)null, playername, VIP_RANK);
    }

	@Override
    public boolean extendHours(Player player, ShopItemData itemData, long hours)
    {
		return true;
    }
}