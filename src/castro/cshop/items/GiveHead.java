package castro.cshop.items;

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
	    return 0;
    }
	
	public static boolean giveHead(Player player, String head)
	{
		ItemStack skull = getPlayerSkull();
		
		SkullMeta sm = (SkullMeta) skull.getItemMeta();
		sm.setOwner(head);
		
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