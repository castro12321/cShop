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
		return 5000 / month;
    }
	
	@Override
	public int minTime()
	{
		return 7*day;
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