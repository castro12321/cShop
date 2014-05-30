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
		final float perMonth = 1000;
		final float perDay   = perMonth / 30;
		return perDay / 24;
    }
}