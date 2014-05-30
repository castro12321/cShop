package castro.cshop.items;

import org.bukkit.entity.Player;

import castro.cshop.Plugin;
import castro.cshop.items.base.ShopItemData;
import castro.cshop.items.base.ShopItemSingleUse;

public class Schematic extends ShopItemSingleUse
{
	@Override
    protected int getPricePerUse()
    {
	    return 0;
    }
	

	@Override
    public boolean giveItem(Player player, ShopItemData itemData)
    {
		return Plugin.dispatchCommand(player, "plot download");
    }
}