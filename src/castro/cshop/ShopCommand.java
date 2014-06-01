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

import java.util.NoSuchElementException;

import castro.base.BaseCCommand;
import castro.cshop.utils.Parser;

// /buy <item> [extra] <timehours>
public class ShopCommand extends BaseCCommand
{
	protected final Plugin plugin = Plugin.get;
	protected CCommandID item;
	protected String     extra;
	protected int        hours;
	
	
	@Override
	protected final boolean prepare()
	{
		String cmd = args[0];
		
		switch(args.length)
		{
		case 3: // /buy <item> <extra> <time>
			extra = args[1];
			hours = Parser.parseHours(args[2]);
			break;
			
		case 2: // /buy <item> <<extra>/<time>>
			extra = args[1];
			hours = Parser.parseHours(args[1]);
			break;
			
		case 1: // /buy <item>
			extra = "";
			hours = 0;
			break;
		}
		
		try
		{
			item = CCommandID.get(cmd, extra);
		}
		catch(NoSuchElementException e)
		{
			return !plugin.sendMessage(sender, "&cWrong item code");
		}
		if(hours < item.executor.minTime())
			return !plugin.sendMessage(sender, "&cToo short time");
		Plugin.get.broadcast("cmd: " + cmd + " " + item + " " + hours + " " + extra);
		return true;
	}
	
	
	@Override
	protected boolean execute()
	{
		return Plugin.shop.buy(senderPlayer, item, hours, extra);
	}
	
	
	@Override public    boolean  onlyPlayer()        { return true; }
	@Override protected int      minArgs()           { return 1; } // /buy <item> [time] [extra info]
	@Override protected String[] neededPermissions() { return null; }
}
