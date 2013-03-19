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

import core.util.FastRandom;

public class Bag extends Model
{
	public static int DIRTY = 0x01;
	
	long seed;
	int step;
	
	FastRandom random = new FastRandom();
	List<Shape> bag = new ArrayList<Shape>();
	List<Shape> sevenBag = new ArrayList<Shape>();
	Shape hold;
	
	public Bag (long seed)
	{
		this.seed = seed;
		random.setSeed(seed);
	}
	
	public Shape switchHold (Shape shape)
	{
		Shape result = hold;
		hold = shape;
		
		if (result == null)
			result = getAndConsumeNextShape();
		
		getFlags().mark(DIRTY);

		return result;
	}
	
	public Shape getHold ()
	{
		return hold;
	}
	
	Shape calculateNextPiece ()
	{
		if (sevenBag.isEmpty())
		{
			for (Shape shape : Shape.values())
				if (shape != Shape.NONE && shape != Shape.GARBAGE)
					sevenBag.add(shape);
		}
		
		int nextPiece = random.nextInt(sevenBag.size());
		Shape shape = sevenBag.get(nextPiece);
		sevenBag.remove(nextPiece);
		
		return shape;
	}
	
	public Shape getShape(int future)
	{
		while(bag.size() <= future)
			bag.add(calculateNextPiece());
		
		return bag.get(future);
	}
	
	public Shape getAndConsumeNextShape ()
	{
		Shape shape = getShape(0);
		bag.remove(0);
		
		getFlags().mark(DIRTY);
		
		return shape;
	}
}
