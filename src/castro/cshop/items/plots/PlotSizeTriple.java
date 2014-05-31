package castro.cshop.items.plots;

import castro.cshop.items.base.ShopItemPermission;

public class PlotSizeTriple extends ShopItemPermission
{
	@Override
    public String getPermission()
	{
		return "cworlds.limit.triple";
    }
	
	@Override
    public float getPricePerHour()
    {
		return 2000 / month;
    }
}