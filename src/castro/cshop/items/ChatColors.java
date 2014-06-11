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
		//return 100/day;
		return 3000 / month;
    }
	
	
	@Override
	public float minTime()
	{
		//return hour;
		return 24*hour;
	}
}