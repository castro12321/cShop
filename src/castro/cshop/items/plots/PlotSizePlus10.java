package castro.cshop.items.plots;

import castro.shop.items.base.ShopItemPermission;

public class PlotSizePlus10 extends ShopItemPermission
{
	@Override
    public String getPermission()
	{
		return "cworlds.plotsize.plus10";
    }
	
	@Override
    public float getPricePerHour()
    {
		final float perMonth = 1000;
		final float perDay   = perMonth / 30;
		return perDay / 24;
    }
}