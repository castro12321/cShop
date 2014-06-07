package castro.cshop.items.plots;

import castro.cshop.items.base.ShopItemPermission;

abstract class AdditionalSave extends ShopItemPermission
{
	private final int slot;
	
	protected AdditionalSave(int plotNumber)
	{
		this.slot = plotNumber;
	}
	
	@Override
    public final String getPermission()
	{
		return "cworlds.saves." + slot;
    }
	
	@Override
    public final float getPricePerHour()
    {
		return 3000 / month;
    }
	
	@Override
	public int minTime()
	{
		return 1*day;
	}
}