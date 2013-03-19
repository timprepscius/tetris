package core.io;

import java.util.ArrayList;
import java.util.List;

import core.util.Base16;
import core.util.Strings;

public class Out 
{
	List<String> segments = new ArrayList<String>();
	
	public Out()
	{
		
	}
	
	public void writeInteger(int v)
	{
		segments.add("" + v);
	}
	
	public void writeFloat(float v)
	{
		segments.add("" + v);
	}
	
	public void writeString(String v)
	{
		segments.add(Base16.encode(Strings.toBytes(v)));
	}
	
	public void writeBytes(byte[] v)
	{
		if (v == null)
			segments.add("");
		else
			segments.add(Base16.encode(v));
	}
	
	public void writeBoolean (boolean v)
	{
		segments.add(v ? "1" : "0");
	}
	
	public String toString ()
	{
		return Strings.concat(segments, ",");
	}
}
