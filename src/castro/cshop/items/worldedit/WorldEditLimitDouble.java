package castro.cshop.items.worldedit;

import castro.shop.items.base.ShopItemPermission;

public class WorldEditLimitDouble extends ShopItemPermission
{
	@Override
    public String getPermission()
	{
		return "aliquam.welimit.x2";
    }
	
	@Override
    public float getPricePerHour()
    {
		final float perDay = 100;
		return perDay/24;
    }
}