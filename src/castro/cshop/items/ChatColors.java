package castro.cshop.items;

import castro.shop.items.base.ShopItemPermission;

public class ChatColors extends ShopItemPermission
{
	@Override
    public String getPermission()
    {
		return "castro.colors";
    }
	
	@Override
    public float getPricePerHour()
    {
	    final float perMonth = 3000;
	    final float perDay   = perMonth / 30;
	    return perDay / 24;
    }
}