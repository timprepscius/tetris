///////////////////////////////////////////////////////////////////////////////
// Copyright (C) 2013 Timothy Prepscius
//
// This is a viewable source license.  No copying or modification or use is
// permitted.
//
// If you would like to participate in its development, or
// if you would like a different license, please contact the author.
///////////////////////////////////////////////////////////////////////////////

package core.io;

import java.util.ArrayList;
import java.util.List;

import core.util.Base16;
import core.util.LogOut;
import core.util.Strings;

public class In 
{
	static LogOut log = new LogOut(In.class);
	List<String> segments = new ArrayList<String>();
	int cursor = 0;
	
	public In (String message)
	{
		int l=0,n=0;
		while ((n=message.indexOf(",", l)) != -1)
		{
			segments.add(message.substring(l,n));
			l = n+1;
		}
		
		segments.add(message.substring(l));
		
		log.debug(Strings.concat(segments, ":"));
	}
	
	public int readInteger()
	{
		return Integer.parseInt(segments.get(cursor++));
	}

	public float readFloat()
	{
		return Float.parseFloat(segments.get(cursor++));
	}

	public String readString()
	{
		return Strings.toString(Base16.decode(segments.get(cursor++)));
	}
	
	public byte[] readBytes ()
	{
		return Base16.decode(segments.get(cursor++));
	}
	
	public boolean readBoolean ()
	{
		return segments.get(cursor++).equals("1");
	}
}

