package castro.cshop.items.base;

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
		throw new UnsupportedOperationException("Cannot take single use item");
	}
	
	
	@Override
	public boolean extendHours(Player player, ShopItemData itemData, long hours)
	{
		throw new UnsupportedOperationException("Cannot extend single use item");
	}
	
	
	@Override
	public boolean singleUse()
	{
		return true;
	}
}
