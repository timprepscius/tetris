package tetris;

@SuppressWarnings("serial")
public class TetrisException extends RuntimeException
{
	public TetrisException(String string)
	{
		super(string);
	}
	
	public TetrisException(java.lang.Exception e)
	{
		super(e);
	}

}
