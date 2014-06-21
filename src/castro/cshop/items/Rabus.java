package castro.cshop.items;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import castro.cshop.items.base.ShopItemData;
import castro.cshop.items.base.ShopItemSingleUse;

public class Rabus extends ShopItemSingleUse
{
	@Override
    protected int getPricePerUse()
    {
	    return 2000;
    }
	
	
    @Override
    public boolean giveItem(Player player, ShopItemData itemData)
    {
    	GiveHead.giveHead(player, "Rabus_PL");
		return plugin.broadcast(player.getName() + ChatColor.GREEN + " kupil Rabusa!");
    }
}