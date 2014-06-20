package castro.cshop.items;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.material.MaterialData;

import castro.cshop.items.base.ShopItemData;
import castro.cshop.items.base.ShopItemSingleUse;

public class GiveHead extends ShopItemSingleUse
{
	@Override
    protected int getPricePerUse()
    {
	    return 50;
    }
	
	public static boolean giveHead(Player player, String head)
	{
		ItemStack skull = getPlayerSkull();
		
		// Link player name with skull
		SkullMeta sm = (SkullMeta) skull.getItemMeta();
		sm.setOwner(head);
		
		// Woozie DRM
		List<String> lore = new ArrayList<String>();
		lore.add(player.getName());
		sm.setLore(lore);
		
		skull.setItemMeta(sm);
		
		// Finally, add the item
		Inventory inv = player.getInventory();
		inv.addItem(skull);
		
		return true;
	}
	
	@SuppressWarnings("deprecation")
    private static ItemStack getPlayerSkull()
	{
		MaterialData data = new MaterialData(Material.SKULL_ITEM);
		data.setData((byte) 3); // 3 is the player head
		ItemStack item = data.toItemStack();
		item.setAmount(1);
		return item;
	}
	

	@Override
    public boolean giveItem(Player player, ShopItemData itemData)
    {
		return giveHead(player, itemData.extra);
    }
}