/* cShop
 * Copyright (C) 2013 Norbert Kawinski (norbert.kawinski@gmail.com)

 */

package castro.cshop;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import castro.cshop.items.NickColor;
import castro.cshop.items.base.ShopItemId;

public class EventListener implements Listener
{
	//private final Plugin plugin = Plugin.get();
	
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event)
	{
		final Player player = event.getPlayer();
		if(player.hasPermission("cshop.nick.expired"))
		{
			if(Plugin.SQL.getItemOrNull(player.getName(), ShopItemId.CHAT_COLORS) != null)
				return;
			NickColor.setNick(player, player.getName());
			Plugin.permissions.playerRemove((String)null, player.getName(), NickColor.expiredPermission);
		}
	}
	
	/*
	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent event)
	{
		final Player player = event.getPlayer();
		if(player.hasPermission("cshop.nick.expired"))
		{
			if(Plugin.SQL.getItemOrNull(player.getName(), CCommandID.CHAT_COLORS) == null)
				return;
			NickColor.setNick(player, player.getName());
			Plugin.permissions.playerRemove((String)null, player.getName(), NickColor.expiredPermission);
		}
	}
	*/
}