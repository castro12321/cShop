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
		return 600 / week;
    }
	
	@Override
	public float minTime()
	{
		return week;
	}
}