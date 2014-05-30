package castro.cshop.items.plots;

import castro.shop.items.base.ShopItemPermission;

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
		final float perMonth = 2500;
		final float perDay   = perMonth / 30;
		return perDay / 24;
    }
}