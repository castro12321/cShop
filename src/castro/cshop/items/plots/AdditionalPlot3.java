package castro.cshop.items.plots;


public class AdditionalPlot3 extends AdditionalPlot
{
	public AdditionalPlot3()
	{
		super(3);
	}
	
	@Override
    public final float getPricePerHour()
    {
		return 3000 / week;
    }
}