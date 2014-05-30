package castro.cshop.items.base;

import java.sql.SQLException;

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
	public boolean extendHours(Player player, ShopItemData itemData, long hours)
	{
		try
		{
			itemData.extendHours(hours);
		}
		catch(SQLException e)
		{
			return false;
		}
		return true;
	}
}
