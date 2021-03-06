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

public class LogOut
{
	String prefix;
	String cached = "";
	
	public LogOut (Class<?> clazz)
	{
		prefix = clazz.getName()+":";
	}
	
	public LogOut(String prefix) 
	{
		this.prefix = prefix;
	}

	public final String build (Object...arguments)
	{
		StringBuilder builder = new StringBuilder();
		builder.append(prefix);

		for (int j=0;j<arguments.length; ++j)
		{
			builder.append(" ");
			builder.append(arguments[j]);
		}
	
		return builder.toString();
	}
	
	public final void println (Object...arguments)
	{
		LogPlatform.println(build(arguments));
	}
	
	public final void print (String s)
	{
		cached += s;
	}
	
	public final void flush ()
	{
		LogPlatform.println(cached);
		cached = "";
	}

	public final void debug (Object...arguments)
	{
		println(arguments);
	}
	
	public final void error (Object...arguments)
	{
		println(arguments);
	}

	public final void debugPart (Object...arguments)
	{
		print(Strings.concat(arguments, " "));
	}
	
	public final void debugFlush ()
	{
		flush();
	}
	
	public final String format (String format, Object...args)
	{
		String s = "";
		s += format;
		for (Object o : args)
		{
			s += o;
			s += " ";
		}
		
		return s;
	}
	
	public void exception (TetrisException e)
	{
		LogPlatform.printException(e);
	}
	
	public void trace(Object...args) {
	}
}
