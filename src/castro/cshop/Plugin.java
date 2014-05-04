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

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;

import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.ServicesManager;

import castro.base.plugin.CPlugin;
import castro.base.plugin.CPluginSettings;

public class Plugin extends CPlugin 
{
	public static Plugin get;
	public static SQL SQL;
	public static Shop shop;
	
	public static Permission permission;
	public static Economy economy;
	
	
	@Override
	protected CPluginSettings getSettings()
	{
		get = this;
		
		CPluginSettings settings = new CPluginSettings();
		settings.useConfig  = true;
		settings.commandMgr = new CommandMgr(this);
		settings.listeners.add(new EventListener());
		return settings;
	}
	
	
	@Override
	protected void init()
	{
		ServicesManager services = getServer().getServicesManager();
		RegisteredServiceProvider<Economy> economyProvider = services.getRegistration(net.milkbowl.vault.economy.Economy.class);
		if (economyProvider != null)
			economy = economyProvider.getProvider();
		RegisteredServiceProvider<Permission> permissionProvider = services.getRegistration(net.milkbowl.vault.permission.Permission.class);
		if (permissionProvider != null)
			permission = permissionProvider.getProvider();
		
		SQL  = new SQL();
		shop = new Shop();
	}
	
	
	public void reloadWELimit(String playername)
	{ reloadWELimit(getServer().getPlayerExact(playername)); }
	public void reloadWELimit(Player player)
	{
		if(player != null)
			dispatchCommand(player, "/limit -1");
	}
}