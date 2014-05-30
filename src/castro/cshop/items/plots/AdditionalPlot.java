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
    public final float getPricePerHour()
    {
		final float perMonth = 10000;
		final float perDay   = perMonth / 30;
		return perDay / 24;
    }
}