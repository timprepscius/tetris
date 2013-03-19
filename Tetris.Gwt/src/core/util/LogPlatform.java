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

public class LogPlatform
{
	public native static void println(String s) /*-{
		$wnd.log(s);
	}-*/;
	
	public native static void printException(Object e) /*-{
		$wnd.logException(e);
	}-*/;

}
