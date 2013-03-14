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
