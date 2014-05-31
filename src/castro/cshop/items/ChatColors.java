package castro.cshop.items;

import castro.cshop.items.base.ShopItemPermission;

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
		return 3000 / month;
    }
	
	
	@Override
	public int minTime()
	{
		return 24*hour;
	}
}