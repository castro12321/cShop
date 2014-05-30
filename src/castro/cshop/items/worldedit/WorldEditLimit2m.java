package castro.cshop.items.worldedit;

import castro.shop.items.base.ShopItemPermission;

public class WorldEditLimit2m extends ShopItemPermission
{
	@Override
    public String getPermission()
	{
		return "aliquam.welimit.2m";
    }
	
	@Override
    public float getPricePerHour()
    {
		return 2000;
    }
}