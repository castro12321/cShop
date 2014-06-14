package castro.cshop.items.plots;

import castro.cshop.items.base.ShopItemPermission;

public class MobLimit500 extends ShopItemPermission
{
	@Override
    public String getPermission()
	{
		return "cworlds.moblimit.500";
    }
	
	@Override
    public float getPricePerHour()
    {
		return 1000 / month;
    }
	
	@Override
	public float minTime()
	{
		return week;
	}
}