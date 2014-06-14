package castro.cshop.items.plots;


public class AdditionalPlot5 extends AdditionalPlot
{
	public AdditionalPlot5()
	{
		super(5);
	}
	
	@Override
    public final float getPricePerHour()
    {
		return 5000 / week;
    }
}