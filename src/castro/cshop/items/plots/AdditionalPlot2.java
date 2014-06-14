package castro.cshop.items.plots;


public class AdditionalPlot2 extends AdditionalPlot
{
	public AdditionalPlot2()
	{
		super(2);
	}
	
	@Override
    public final float getPricePerHour()
    {
		return 2000 / week;
    }
}