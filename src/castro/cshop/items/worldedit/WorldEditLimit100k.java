package castro.cshop.items.worldedit;

import castro.cshop.items.base.ShopItemPermission;

public class WorldEditLimit100k extends ShopItemPermission
{
	@Override
    public String getPermission()
	{
		return "aliquam.welimit.100k";
    }
	
	@Override
    public float getPricePerHour()
    {
		return 100;
    }
}