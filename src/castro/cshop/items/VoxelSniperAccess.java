package castro.cshop.items;

import castro.cshop.items.base.ShopItemPermission;

public class VoxelSniperAccess extends ShopItemPermission
{
	@Override
    public String getPermission()
    {
		return "voxelsniper.sniper";
    }
	
	@Override
    public float getPricePerHour()
    {
		return 1000 / day;
    }
	
	
	@Override
	public float minTime()
	{
		return 24*hour;
	}
}