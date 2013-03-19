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

public class BoardMatrix 
{
	public void calculate(CanvasInfo canvas, Board board)
	{
		calculate(canvas.w, canvas.h, board.width, board.height);
	}
	
	public void calculate(float cw, float ch, int bw, int bh)
	{
		pw = cw / bw;
		ph = ch / bh;

		mx = cw / bw;
		my = -(ch / bh);
		ax = 0;
		ay = ch - ph;
	}
	
	public float mx, ax, pw;
	public float my, ay, ph;
}
