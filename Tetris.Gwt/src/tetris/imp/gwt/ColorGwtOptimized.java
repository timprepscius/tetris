package tetris.imp.gwt;

public class ColorGwtOptimized extends ColorGwt
{
	public ColorGwtOptimized(byte[] bytes) 
	{
		super(bytes);
	}
	
	public ColorGwtOptimized(String string)
	{
		super(string);
	}

	public ColorGwtOptimized(int r, int g, int b) 
	{
		super(r,g,b);
	}

	ColorGwt brighter;

	@Override
	public ColorGwt makeBrighter() 
	{
		if (brighter == null)
			brighter = makeBrighter();
		
		return brighter;
	}
}
