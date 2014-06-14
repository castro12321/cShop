package castro.cshop.items.plots;


public class AdditionalPlot10 extends AdditionalPlot
{
	public AdditionalPlot10()
	{
		super(10);
	}
	
	@Override
    public final float getPricePerHour()
    {
		return 10000 / week;
    }
}