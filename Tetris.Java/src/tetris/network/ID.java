package tetris.network;

import core.util.Base16;
import core.util.FastRandom;
import core.util.Strings;

public class ID
{
	static FastRandom random = new FastRandom();
	public byte[] id;
	
	protected ID (byte[] id)
	{
		this.id = id;
	}
	
	public static ID fromLong (long v)
	{
		byte[] b = new byte[Long.SIZE/8];
		for (int i=b.length-1; i>=0; --i)
		{
			b[i] = (byte)(v & 0xFF);
			v = v >> 8;
		}
		
		return new ID(b);
	}
	
	public static ID random ()
	{
		return ID.fromLong(random.nextLong());
	}
	
	public boolean equals(Object _rhs)
	{
		if (_rhs == null)
			return false;
		
		ID rhs = (ID) _rhs;
		if (rhs.id == id)
			return true;
		
		if (rhs.id.length != id.length)
			return false;
		
		for (int i=0; i<id.length; ++i)
			if (id[i] != rhs.id[i])
				return false;
		
		return true;
	}

	public byte[] toByteArray()
	{
		return id;
	}
	
	public static ID fromByteArray (byte[] bytes)
	{
		return new ID(bytes);
	}
	
	public int hashCode ()
	{
		return toString().hashCode();
	}
	
	public String toString ()
	{
		return Base16.encode(id);
	}
}
