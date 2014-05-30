package castro.cshop.items.worldedit;

import castro.shop.items.base.ShopItemPermission;

public class WorldEditLimitTriple extends ShopItemPermission
{
	@Override
    public String getPermission()
	{
		return "aliquam.welimit.x3";
    }
	
	@Override
    public float getPricePerHour()
    {
		final float perDay = 200;
		return perDay/24;
    }
}