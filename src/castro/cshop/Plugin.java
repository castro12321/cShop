/* cShop
 * Copyright (C) 2013 Norbert Kawinski (norbert.kawinski@gmail.com)

 */

package castro.cshop;

import org.bukkit.entity.Player;

import castro.base.plugin.CPlugin;
import castro.base.plugin.CPluginSettings;

public class Plugin extends CPlugin 
{
	public static Plugin get;
	public static SQL SQL;
	public static Shop shop;
	
	
	@Override
	protected CPluginSettings getSettings()
	{
		get = this;
		
		CPluginSettings settings = new CPluginSettings();
		settings.useConfig  = false;
		settings.commandMgr = new CommandMgr(this);
		settings.listeners.add(new EventListener());
		return settings;
	}
	
	
	@Override
	protected void init()
	{
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