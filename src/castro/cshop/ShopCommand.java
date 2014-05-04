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

import castro.base.BaseCCommand;
import castro.cshop.utils.Parser;

// /buy <item> [extra] <timehours>
public class ShopCommand extends BaseCCommand
{
	protected CCommandID item;
	protected String     extra;
	protected int        hours;
	
	
	@Override
	protected final boolean prepare()
	{
		switch(args.length)
		{
		case 3: // /buy <item> <extra> <time>
			item  = CCommandID.get(args[0], args[1]);
			extra = args[1];
			hours = Parser.parseHours(args[2]);
			break;
			
		case 2: // /buy <item> <<extra>/<time>>
			item  = CCommandID.get(args[0], null);
			hours = Parser.parseHours(args[1]);
			if(hours <= 0)
				extra = args[1];
			break;
			
		case 1: // /buy <item>
			item = CCommandID.get(args[0], null);
			break;
		}
		
		return item != null;
	}
	
	
	@Override
	protected boolean execute()
	{
		Plugin.shop.buy(senderPlayer, item, hours, extra);
		return true;
	}
	
	
	@Override public    boolean  onlyPlayer()        { return true; }
	@Override protected int      minArgs()           { return 1; } // Item id, time
	@Override protected String[] neededPermissions() { return null; }
}
