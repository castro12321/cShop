package castro.cshop.items;

import java.sql.SQLException;

import org.bukkit.entity.Player;

public abstract class ShopItemSingleUse extends ShopItem
{
	protected abstract int getPricePerUse();
	
	
	@Override
	public float getPricePerHour()
	{
		return getPricePerUse();
	}
	
	
	@Override
	public boolean takeItem(String playername, ShopItemData itemData)
	{
		return true; // SingleUse items decay automatically, don't need to remove it
	}
	
	
	@Override
	public boolean extendHours(Player player, ShopItemData itemData, long hours)
	{
		throw new UnsupportedOperationException("Cannot extend single use item");
	}
}
