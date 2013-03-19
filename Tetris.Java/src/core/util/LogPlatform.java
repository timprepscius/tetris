///////////////////////////////////////////////////////////////////////////////
// Copyright (C) 2013 Timothy Prepscius
//
// This is a viewable source license.  No copying or modification or use is
// permitted.
//
// If you would like to participate in its development, or
// if you would like a different license, please contact the author.
///////////////////////////////////////////////////////////////////////////////

package core.util;

import tetris.TetrisException;

public class LogPlatform
{
	final static void println (Object o)
	{
		System.out.println(o);
	}
	
	final static void printException (Object o)
	{
		TetrisException e = (TetrisException)o;
		e.printStackTrace();
	}
}
