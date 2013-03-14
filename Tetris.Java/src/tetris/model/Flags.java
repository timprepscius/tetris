package tetris.model;

public class Flags
{
	protected int v;
	
	public Flags()
	{
		v = 0;
	}
	
	public void mark (int flags)
	{
		v |= flags;
	}
	
	public boolean has (int flags)
	{
		return (v & flags) != 0;
	}

	/*
	public void clear (int flags)
	{
		v &= ~flags;
	}
	*/
	
	public void clear ()
	{
		v = 0;
	}
}
