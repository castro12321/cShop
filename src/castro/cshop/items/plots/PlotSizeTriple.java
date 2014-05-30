package castro.cshop.items.plots;

import castro.shop.items.base.ShopItemPermission;

public class PlotSizeTriple extends ShopItemPermission
{
	@Override
    public String getPermission()
	{
		return "cworlds.plotsize.triple";
    }
	
	@Override
    public float getPricePerHour()
    {
		final float perMonth = 2000;
		final float perDay   = perMonth / 30;
		return perDay / 24;
    }
}