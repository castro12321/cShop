package castro.cshop.items.base;

import org.bukkit.entity.Player;

import castro.cshop.Plugin;

public abstract class ShopItem
{
	protected final static Plugin plugin = Plugin.get;
	public abstract float getPricePerHour();
	public abstract boolean giveItem(Player player, ShopItemData itemData);
	public abstract boolean takeItem(String playername, ShopItemData itemData);
	public abstract boolean extendHours(Player player, ShopItemData itemData, long hours);
}
