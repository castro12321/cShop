package castro.cshop.items;

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
}
