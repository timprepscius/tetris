package tetris.imp.gwt;

import tetris.model.Color;
import core.util.Base16;

public class ColorGwt extends Color
{
	String string;
	byte[] bytes;
	
	public ColorGwt (int r, int g, int b)
	{
		bytes = new byte[] {
			(byte)r,
			(byte)g,
			(byte)b
		};
	}
	
	public ColorGwt (String string)
	{
		this.string = string;
	}
	
	public ColorGwt (byte[] bytes)
	{
		this.bytes = bytes;
	}
	
	public String getString()
	{
		if (string == null)
			string = "#" + Base16.encode(bytes);
		
		return string;
	}
	
	public byte[] getBytes ()
	{
		if (bytes == null)
			bytes = Base16.decode(string.substring(1)); // no # on front
		
		return bytes;
	}
	
	public ColorGwt makeBrighter ()
	{
		byte[] bytes = getBytes();
		for (int i=0; i<bytes.length; ++i)
			bytes[i] = (byte)Math.min(255, (int)bytes[i] + 30);
		
		return new ColorGwt(bytes);
	}
}
