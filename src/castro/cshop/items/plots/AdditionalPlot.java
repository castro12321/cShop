package castro.cshop.items.plots;

import castro.cshop.items.base.ShopItemPermission;

abstract class AdditionalPlot extends ShopItemPermission
{
	private final int plotNumber;
	
	protected AdditionalPlot(int plotNumber)
	{
		this.plotNumber = plotNumber;
	}
	
	@Override
    public final String getPermission()
	{
		return "cworlds.plots." + plotNumber;
    }
	
	@Override
	public float minTime()
	{
		return 7*day;
	}
}