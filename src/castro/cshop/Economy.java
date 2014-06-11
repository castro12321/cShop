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

import castro.cshop.items.base.ShopItemId;
import net.milkbowl.vault.economy.EconomyResponse;

public class Economy 
{
	private static net.milkbowl.vault.economy.Economy economy = Plugin.economy;
	
	
	private static double getPrice(ShopItemId item, long hours)
	{
		if(item.executor.singleUse())
			return item.executor.getPricePerHour();
		return item.executor.getPricePerHour() * (float)hours;
	}
	
	
	public static boolean canAfford(String player, ShopItemId item, long hours)
	{
		double balance = economy.getBalance(player);
		double price   = getPrice(item, hours);
		return balance >= price;
	}
	
	
	public static boolean charge(String player, ShopItemId item, long hours)
	{
		Plugin.get.log("Charging player");
		double price = getPrice(item, hours);
		EconomyResponse resp = economy.withdrawPlayer(player, price);
		if(!resp.transactionSuccess())
			Plugin.get.log("ERROR: Cannot withdraw player. Err: " + resp.errorMessage);
		return resp.transactionSuccess();
	}
}