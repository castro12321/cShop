package castro.cshop.items.base;

import org.bukkit.entity.Player;

import castro.base.Plugin;

public abstract class ShopItemPermission extends ShopItem
{
	public abstract String getPermission();
	
	
	@Override
	public boolean giveItem(Player player, ShopItemData itemData)
	{
		return Plugin.permissions.playerAdd((String)null, player.getName(), getPermission());
	}
	
	
	@Override
	public boolean takeItem(String playername, ShopItemData itemData)
	{
		return Plugin.permissions.playerRemove((String)null, playername, getPermission());
	}
	
	
	@Override
	public boolean update(Player player, ShopItemData itemData)
	{
		return true;
	}
}
