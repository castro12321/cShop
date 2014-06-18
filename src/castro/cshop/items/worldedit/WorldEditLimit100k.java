package castro.cshop.items.worldedit;

import org.bukkit.entity.Player;

import castro.base.Plugin;
import castro.cshop.items.base.ShopItemData;
import castro.cshop.items.base.ShopItemPermission;

public class WorldEditLimit100k extends ShopItemPermission
{
	@Override
    public String getPermission()
	{
		return "aliquam.welimit.100k";
    }
	
	@Override
	public boolean giveItem(Player player, ShopItemData itemData)
	{
		if(!super.giveItem(player, itemData))
			return false;
		return Plugin.dispatchCommand(player, "/limit 1");
	}
	
	@Override
	public boolean takeItem(String playername, ShopItemData itemData)
	{
		if(!super.takeItem(playername, itemData))
			return false;
		Player player = Plugin.getPlayer(playername);
		if(player != null)
			return Plugin.dispatchCommand(player, "/limit 1");
		return true;
	}
	
	@Override
    public float getPricePerHour()
    {
		return 100;
    }
}