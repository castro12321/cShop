package castro.cshop.exceptions;

import java.io.IOException;

public class BadTimeException extends IOException
{
	private static final long serialVersionUID = 1L;

	public BadTimeException (String msg)
	{
		super(msg);
	}
}
