package castro.cshop.items.plots;

import castro.cshop.items.base.ShopItemPermission;

public class PlotSizePlus25 extends ShopItemPermission
{
	@Override
    public String getPermission()
	{
		return "cworlds.limit.plus25";
    }
	
	@Override
    public float getPricePerHour()
    {
		return 2500 / month;
    }
}