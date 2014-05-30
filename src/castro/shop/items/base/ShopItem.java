package castro.shop.items.base;

import org.bukkit.entity.Player;

public abstract class ShopItem
{
	public abstract float getPricePerHour();
	public abstract boolean giveItem(Player player, ShopItemData itemData);
	public abstract boolean takeItem(String playername, ShopItemData itemData);
	public abstract boolean extendHours(Player player, ShopItemData itemData, long hours);
}
