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

import java.io.UnsupportedEncodingException;

public class StringsPlatform
{
	static public String toString(byte[] bytes)
	{
		try
		{
			return new String(bytes, "UTF-8");
		}
		catch (UnsupportedEncodingException e)
		{
			throw new RuntimeException(e);
		}
	}

	static public byte[] toBytes(String string)
	{
		try
		{
			return string.getBytes("UTF-8");
		}
		catch (UnsupportedEncodingException e)
		{
			throw new RuntimeException(e);
		}
	}

}
