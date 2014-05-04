package castro.cshop.items;

public abstract class ShopItem
{
	public abstract float getPricePerHour();
	public abstract boolean giveItem(String playername, ShopItemData itemData);
	public abstract boolean takeItem(String playername, ShopItemData itemData);
}
