package castro.cshop.items.plots;

import castro.cshop.items.base.ShopItemPermission;

public class PlotSizePlus25 extends ShopItemPermission
{
	@Override
    public String getPermission()
	{
		return "cworlds.plotsize.plus25";
    }
	
	@Override
    public float getPricePerHour()
    {
		final float perMonth = 2500;
		final float perDay   = perMonth / 30;
		return perDay / 24;
    }
}