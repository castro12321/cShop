package castro.cshop.items.worldedit;

import org.bukkit.entity.Player;

import castro.base.Plugin;
import castro.cshop.items.base.ShopItemData;
import castro.cshop.items.base.ShopItemPermission;
import castro.ctools.modules.WorldEditLimits;

public class WorldEditLimitTriple extends ShopItemPermission
{
	@Override
    public String getPermission()
	{
		return "aliquam.welimit.x3";
	}
	
	@Override
	public boolean giveItem(Player player, ShopItemData itemData)
	{
		if(!super.giveItem(player, itemData))
			return false;
		WorldEditLimits.reloadPlayerLimit(player);
		return true;
	}
	
	@Override
	public boolean takeItem(String playername, ShopItemData itemData)
	{
		if(!super.takeItem(playername, itemData))
			return false;
		Player player = Plugin.getPlayer(playername);
		if(player != null)
			WorldEditLimits.reloadPlayerLimit(player);
		return true;
	}
	
	@Override
    public float getPricePerHour()
    {
		return 300 / hour;
    }
}