package tetris.model;

import java.lang.Math;

public class Piece {

	static private final int[][][] coordsTable = new int[][][] 
			{
		{ { 0, 0 },   { 0, 0 },   { 0, 0 },   { 0, 0 } },
		{ { 0, -1 },  { 0, 0 },   { -1, 0 },  { -1, 1 } },
		{ { 0, -1 },  { 0, 0 },   { 1, 0 },   { 1, 1 } },
		{ { 0, -1 },  { 0, 0 },   { 0, 1 },   { 0, 2 } },
		{ { -1, 0 },  { 0, 0 },   { 1, 0 },   { 0, 1 } },
		{ { 0, 0 },   { 1, 0 },   { 0, 1 },   { 1, 1 } },
		{ { -1, -1 }, { 0, -1 },  { 0, 0 },   { 0, 1 } },
		{ { 1, -1 },  { 0, -1 },  { 0, 0 },   { 0, 1 } }
			};

	private static final int[][][][] realizedCoords;

	static
	{
		realizedCoords = new int[4][8][4][];
		for (int s=0; s<8; ++s)
			for (int i=0; i<4; ++i)
				realizedCoords[0][s][i] = 
				new int[] { coordsTable[s][i][0], coordsTable[s][i][1] };

		for (int r=1; r<4; ++r)
			for (int s=0; s<8; ++s)
				for (int i=0; i<4; ++i)
					realizedCoords[r][s][i] = 
					new int[] { realizedCoords[r-1][s][i][1], -realizedCoords[r-1][s][i][0] };
	}


	protected int s;
	protected int r;
	protected int x, y;

	public Piece() 
	{
		setShape(Shape.NONE);
		setRotation(0);
		x = 0;
		y = 0;
	}

	public void copyFrom (Piece rhs)
	{
		r = rhs.r;
		x = rhs.x;
		y = rhs.y;
		s = rhs.s;
	}
	
	public void setRotation(int r)
	{
		while (r < 0)
			r = 4 + r;

		r = r % 4;
		
		this.r = r;
	}
	
	public void applyDelta(int dr, int dx, int dy)
	{
		r += dr;
		x += dx;
		y += dy;
		
		setRotation(r);
	}

	public Shape getShape()  
	{ 
		return Shape.values()[s]; 
	}
	
	public void setShape(Shape shape) 
	{
		this.s = shape.ordinal();
	}

	public int X(int i) 
	{ 
		return realizedCoords[r][s][i][0] + x; 
	}
	
	public int Y(int i) 
	{ 
		return realizedCoords[r][s][i][1] + y; 
	}

	public void initialize (Shape s, int x, int y)
	{
		setShape(s);
		setRotation(0);
		this.y = 0;
		this.x = x;
		this.y = y - minY();
	}
	
	public int minX()
	{
		int m = Integer.MAX_VALUE;
		for (int i=0; i < 4; i++) {
			m = Math.min(m, X(i));
		}
		return m;
	}


	public int minY() 
	{
		int m = Integer.MAX_VALUE;
		for (int i=0; i < 4; i++) {
			m = Math.min(m, Y(i));
		}
		return m;
	}

	public int getRotation()
	{
		return r;
	}

	public int getX()
	{
		return x;
	}

	public int getY()
	{
		return y;
	}

	public void setX(int x)
	{
		this.x = x;
	}
	
	public void setY(int y)
	{
		this.y = y;
	}
	
}