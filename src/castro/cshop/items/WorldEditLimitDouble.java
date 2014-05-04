package castro.cshop.items;

public class WorldEditLimitDouble extends ShopItemPermission
{
	@Override
    public String getPermission()
	{
		return "aliquam.welimit.x2";
    }
	
	@Override
    public float getPricePerHour()
    {
		float perDay = 100;
		return perDay/24;
    }
}