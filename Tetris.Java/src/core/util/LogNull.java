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

public class LogNull
{
	LogOut out;
	
	public LogNull (Class<?> clazz)
	{
		out = new LogOut(clazz);
	}
	
	public LogNull(String string) {
		out = new LogOut(string);
	}

	public final void debug (Object...arguments)
	{
//		out.debug(arguments);
	}
	
	public final void debugPart (Object...arguments)
	{
//		out.debugPart(arguments);
	}
	
	public final void debugFlush ()
	{
//		out.debugFlush();
	}
	
	public final void error (Object...arguments)
	{
		out.println(arguments);
	}
	
	public final String format (String format, Object...args)
	{
		return out.format(format,  args);
	}
	
	public final void trace (Object...args)
	{
		
	}
	
	public void exception (TetrisException e)
	{
		out.exception(e);
	}
}
