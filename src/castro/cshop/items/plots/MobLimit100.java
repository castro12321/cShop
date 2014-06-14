package castro.cshop.items.plots;

import castro.cshop.items.base.ShopItemPermission;

public class MobLimit100 extends ShopItemPermission
{
	@Override
    public String getPermission()
	{
		return "cworlds.moblimit.100";
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