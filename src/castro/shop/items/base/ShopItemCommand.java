package castro.shop.items.base;

import org.bukkit.entity.Player;

import castro.base.Plugin;

public abstract class ShopItemCommand extends ShopItem
{
	public abstract String getGiveCommand();
	public abstract String getTakeCommand();
	
	@Override
	public boolean giveItem(Player player, ShopItemData itemData)
	{
		String command = getGiveCommand()
				.replace("$target$", player.getName());
		return Plugin.dispatchConsoleCommand(command);
	}
	
	
	@Override
	public boolean takeItem(String playername, ShopItemData itemData)
	{
		String command = getTakeCommand()
				.replace("$target$", playername);
		return Plugin.dispatchConsoleCommand(command);
	}
}
