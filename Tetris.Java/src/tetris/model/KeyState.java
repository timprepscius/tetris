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

import java.util.ArrayList;
import java.util.List;

public class KeyState 
{
	List<Integer> keys = new ArrayList<Integer>();
	
	public void setState (int keyCode, boolean down)
	{
		if (down)
		{
			if (!keys.contains(keyCode))
				keys.add(keyCode);
		}
		if (!down)
		{
			int index = keys.indexOf(keyCode);
			if (index != -1)
				keys.remove(index);
		}
	}
	
	public boolean isDown (int keyCode)
	{
		return keys.contains(keyCode);
	}
	
	List<Integer> getKeys ()
	{
		return keys;
	}
}
