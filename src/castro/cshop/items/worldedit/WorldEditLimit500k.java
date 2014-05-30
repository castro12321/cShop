package castro.cshop.items.worldedit;

import castro.shop.items.base.ShopItemPermission;

public class WorldEditLimit500k extends ShopItemPermission
{
	@Override
    public String getPermission()
	{
		return "aliquam.welimit.500k";
    }
	
	@Override
    public float getPricePerHour()
    {
		return 500;
    }
}