package castro.cshop.items.plots;

import castro.cshop.items.base.ShopItemPermission;

public class MobLimit250 extends ShopItemPermission
{
	@Override
    public String getPermission()
	{
		return "cworlds.moblimit.250";
    }
	
	@Override
    public float getPricePerHour()
    {
		return 2500 / month;
    }
}