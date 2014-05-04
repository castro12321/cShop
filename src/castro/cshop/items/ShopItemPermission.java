package castro.cshop.items;

import castro.base.Plugin;

public abstract class ShopItemPermission extends ShopItem
{
	public abstract String getPermission();
	
	
	@Override
	public boolean giveItem(String playername, ShopItemData itemData)
	{
		return Plugin.permissions.playerAdd((String)null, playername, getPermission());
	}
	
	
	@Override
	public boolean takeItem(String playername, ShopItemData itemData)
	{
		return Plugin.permissions.playerRemove((String)null, playername, getPermission());
	}
}
