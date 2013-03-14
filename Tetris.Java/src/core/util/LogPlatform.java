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
