package castro.cshop.items.plots;

import castro.cshop.items.base.ShopItemPermission;

public class PlotSizeDouble extends ShopItemPermission
{
	@Override
    public String getPermission()
	{
		return "cworlds.limit.double";
    }
	
	@Override
    public float getPricePerHour()
    {
		return 1000 / month;
    }
}