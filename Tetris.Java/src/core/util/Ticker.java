package core.util;

import java.util.Date;

public class Ticker
{
	boolean running = false;
	long interval = 0;
	Date lastTick = null;
	
	public Ticker(long interval)
	{
		setInterval(interval);
	}

	public void setInterval (long interval)
	{
		this.interval = interval;
	}
	
	public void start (boolean tickImmediately)
	{
		running = true;
		
		if (tickImmediately)
			lastTick = new Date(0);
		else
			lastTick = new Date();
	}
	
	public void stop ()
	{
		running = false;
		lastTick = null;
	}
	
	public boolean tick ()
	{
		if (!running)
			return false;
		
		Date now = new Date();
		boolean result = (now.getTime() - lastTick.getTime() > interval);

		if (result)
			lastTick = now;
		
		return result;
	}
}
