package castro.cshop.items.base;

import org.bukkit.entity.Player;

import castro.cshop.Plugin;

public abstract class ShopItem
{
	protected final static Plugin plugin = Plugin.get;
	protected final static int hour  = 1;
	protected final static int day   = 24*hour;
	protected final static int week  = 7*day;
	protected final static int month = 30*day; 
	
	public abstract float getPricePerHour();
	public abstract boolean giveItem(Player player, ShopItemData itemData);
	public abstract boolean takeItem(String playername, ShopItemData itemData);
	public abstract boolean update(Player player, ShopItemData itemData);
	
	public boolean singleUse()
	{
		return false;
	}
	
	public int minTime()
	{
		return 1*hour;
	}
}
