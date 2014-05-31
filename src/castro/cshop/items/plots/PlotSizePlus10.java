package castro.cshop.items.plots;

import castro.cshop.items.base.ShopItemPermission;

public class PlotSizePlus10 extends ShopItemPermission
{
	@Override
    public String getPermission()
	{
		return "cworlds.limit.plus10";
    }
	
	@Override
    public float getPricePerHour()
    {
		return 1000 / month;
    }
}