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
		return 250 / week;
    }
	
	@Override
	public float minTime()
	{
		return week;
	}
}