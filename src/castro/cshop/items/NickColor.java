package castro.cshop.items;

import java.util.Arrays;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import castro.base.plugin.CPlugin;
import castro.cshop.Plugin;
import castro.cshop.items.base.ShopItem;
import castro.cshop.items.base.ShopItemData;

public class NickColor extends ShopItem
{
	public final static String expiredPermission = "cshop.nick.expired";
	
	private final static List<Character> whitelistedColors = Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9');
	private String translate(String color)
	{
		if(color.length() == 1)
			color = "&" + color;
		if(color.length() != 2)
			return null;
		if(color.charAt(0) == '&' && whitelistedColors.contains(color.charAt(1)))
			return ChatColor.translateAlternateColorCodes('&', color);
		return null;
	}
	
	public static boolean setNick(Player player, String nick)
	{
		return Plugin.dispatchConsoleCommand("nick " + player.getName() + " " + nick);
	}
	
	@Override
    public boolean giveItem(Player player, ShopItemData itemData)
    {
		String color = translate(itemData.extra);
		if(color == null)
			return !plugin.sendMessage(player, "&cWrong color provided!");
		return setNick(player, color + player.getName());
    }

	@Override
    public boolean takeItem(String playername, ShopItemData itemData)
    {
		Player player = Plugin.getPlayer(playername);
		if(player != null)
			return NickColor.setNick(player, player.getName());
		return CPlugin.addPermission(playername, expiredPermission);
    }

	@Override
    public boolean update(Player player, ShopItemData itemData)
    {
		String color = translate(itemData.extra);
		if(color == null)
			return !plugin.sendMessage(player, "&cWrong color provided!");
		return setNick(player, color + player.getName());
    }
	
	@Override
    public float getPricePerHour()
    {
		return 50 / day;
    }
	
	@Override
	public float minTime()
	{
		return 24*hour;
	}
}