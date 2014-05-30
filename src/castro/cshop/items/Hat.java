package castro.cshop.items;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import castro.cshop.items.base.ShopItemData;
import castro.cshop.items.base.ShopItemSingleUse;

public class Hat extends ShopItemSingleUse
{
	@Override
    protected int getPricePerUse()
    {
	    return 50;
    }
	

	@Override
    public boolean giveItem(Player player, ShopItemData itemData)
    {
		ItemStack hand   = player.getItemInHand();
		if(hand.getTypeId() < 1 || hand.getTypeId() > 250)
			return !plugin.sendMessage(player, "&cYou must have block in hand");
		
		PlayerInventory inventory = player.getInventory();
		ItemStack helmet = inventory.getHelmet();
		
		// Swap hand with a helmet
		inventory.setItemInHand(helmet);
		inventory.setHelmet(hand);
		return plugin.sendMessage(player, "&aYou have bought a hat");
    }
}