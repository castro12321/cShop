/* cShop
 * Copyright (C) 2013 Norbert Kawinski (norbert.kawinski@gmail.com)

 */

package castro.cshop;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import castro.base.BaseCCommand;
import castro.base.CCommandMgr;
import castro.base.plugin.CPlugin;

public class CommandMgr extends CCommandMgr 
{
	public CommandMgr(CPlugin plugin)
    {
	    super(plugin);
    }

	@Override
    protected BaseCCommand getCommand(CommandSender arg0, Command arg1, String[] arg2)
    {
	    return new ShopCommand();
    }
}
