///////////////////////////////////////////////////////////////////////////////
// Copyright (C) 2013 Timothy Prepscius
//
// This is a viewable source license.  No copying or modification or use is
// permitted.
//
// If you would like to participate in its development, or
// if you would like a different license, please contact the author.
///////////////////////////////////////////////////////////////////////////////

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
