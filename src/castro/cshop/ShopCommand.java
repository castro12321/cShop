/* cShop
 * Copyright (C) 2013 Norbert Kawinski (norbert.kawinski@gmail.com)

 */

package castro.cshop;

import java.util.Arrays;
import java.util.NoSuchElementException;

import castro.base.BaseCCommand;
import castro.cshop.items.base.ShopItemId;
import castro.cshop.utils.Parser;

// /buy <item> [extra] <timehours>
public class ShopCommand extends BaseCCommand
{
	protected final Plugin plugin = Plugin.get;
	protected ShopItemId item;
	protected String     extra;
	protected int        hours;
	protected boolean   silent = false;
	
	@Override
	protected final boolean prepare()
	{
		if(args[args.length-1].equals("-silent"))
			silent = true;
		args = Arrays.copyOfRange(args, 0, args.length-2);
		
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
			item = ShopItemId.get(cmd, extra);
		}
		catch(NoSuchElementException e)
		{
			return !plugin.sendMessage(sender, "&cWrong item code");
		}
		if(hours < item.executor.minTime())
			return !plugin.sendMessage(sender, "&cToo short time");
		//Plugin.get.broadcast("cmd: " + cmd + " " + item + " " + hours + " " + extra);
		return true;
	}
	
	
	@Override
	protected boolean execute()
	{
		return Plugin.shop.buy(senderPlayer, item, hours, extra, silent);
	}
	
	
	@Override public    boolean  onlyPlayer()        { return true; }
	@Override protected int      minArgs()           { return 1; } // /buy <item> [time] [extra info]
	@Override protected String[] neededPermissions() { return null; }
}
