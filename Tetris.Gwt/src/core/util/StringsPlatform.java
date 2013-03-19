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

import com.google.gwt.core.client.JsArrayInteger;

public class StringsPlatform 
{
	public static native String jsToString (byte[] bytes) /*-{
		return $wnd.Utf.toString(bytes);
	}-*/;
	
	public static native JsArrayInteger jsToBytes (String s) /*-{
		return $wnd.Utf.toBytes(s);
	}-*/;
	
	public static String toString(byte[] bytes)
	{
		return jsToString(bytes);
	}
	
	public static byte[] toBytes(String s)
	{
		JsArrayInteger a = jsToBytes(s);
		byte[] bytes = new byte[a.length()];
		
		for (int i=0; i<a.length(); ++i)
			bytes[i] = (byte)a.get(i);
		
		return bytes;
	}
}
