/* cShop
 * Copyright (C) 2013 Norbert Kawinski (norbert.kawinski@gmail.com)

 */

package castro.cshop;

import castro.cshop.items.base.ShopItemId;
import net.milkbowl.vault.economy.EconomyResponse;

public class Economy 
{
	private static net.milkbowl.vault.economy.Economy economy = Plugin.economy;
	
	private static double round2(double value)
	{
		return Math.round(value * 100.0) / 100.0;
	}
	
	public static double getPrice(ShopItemId item, float hours)
	{
		double perHour = item.executor.getPricePerHour();
		if(item.executor.singleUse())
			return perHour;
		double discountModifier = getDiscountModifier(hours);
		return round2(perHour * hours * discountModifier);
	}
	
	
	public static boolean canAfford(String player, ShopItemId item, long hours)
	{
		double balance = economy.getBalance(player);
		double price   = getPrice(item, hours);
		return balance >= price;
	}
	
	
	public static double getDiscountModifier(float hours)
	{
		double days = (double)hours / 24.d;
		if(days > 30)
			days = 30; // 30 days cap
		return 1.d - (days / 100); // 1% discount per each day
	}
	
	
	public static boolean charge(String player, ShopItemId item, long hours)
	{
		//Plugin.get.log("Charging player");
		double price = getPrice(item, hours);
		EconomyResponse resp = economy.withdrawPlayer(player, price);
		if(!resp.transactionSuccess())
			Plugin.get.log("ERROR: Cannot withdraw player. Err: " + resp.errorMessage);
		return resp.transactionSuccess();
	}
}