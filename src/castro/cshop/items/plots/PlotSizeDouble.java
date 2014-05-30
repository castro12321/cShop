package castro.cshop.items.plots;

import castro.cshop.items.base.ShopItemPermission;

public class PlotSizeDouble extends ShopItemPermission
{
	@Override
    public String getPermission()
	{
		return "cworlds.plotsize.double";
    }
	
	@Override
    public float getPricePerHour()
    {
		final float perMonth = 1000;
		final float perDay   = perMonth / 30;
		return perDay / 24;
    }
}