/* cShop
 * Copyright (C) 2013 Norbert Kawinski (norbert.kawinski@gmail.com)
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package castro.cshop;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import castro.cshop.items.NickColor;

public class EventListener implements Listener
{
	//private final Plugin plugin = Plugin.get();
	
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event)
	{
		final Player player = event.getPlayer();
		if(player.hasPermission("cshop.nick.expired"))
		{
			NickColor.setNick(player, player.getName());
			Plugin.permissions.playerRemove((String)null, player.getName(), NickColor.expiredPermission);
		}
	}
	
	
	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent event)
	{
		final Player player = event.getPlayer();
		if(player.hasPermission("cshop.nick.expired"))
		{
			NickColor.setNick(player, player.getName());
			Plugin.permissions.playerRemove((String)null, player.getName(), NickColor.expiredPermission);
		}
	}
}