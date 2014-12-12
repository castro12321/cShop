package castro.cshop.items;

import org.bukkit.entity.Player;

import castro.base.plugin.CPlugin;
import castro.cshop.Plugin;
import castro.cshop.items.base.ShopItem;
import castro.cshop.items.base.ShopItemData;

public class VIP extends ShopItem
{
	public final static String VIP_RANK = "vip";
	
	@Override
    public float getPricePerHour()
    {
		return 6000 / week;
    }
	
	@Override
	public float minTime()
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
		return CPlugin.removePermission(playername, VIP_RANK);
    }

	@Override
    public boolean update(Player player, ShopItemData itemData)
    {
		return true;
    }
}