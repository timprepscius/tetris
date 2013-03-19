///////////////////////////////////////////////////////////////////////////////
// Copyright (C) 2013 Timothy Prepscius
//
// This is a viewable source license.  No copying or modification or use is
// permitted.
//
// If you would like to participate in its development, or
// if you would like a different license, please contact the author.
///////////////////////////////////////////////////////////////////////////////

package tetris.view;

import tetris.model.Board;
import tetris.model.Model;
import tetris.model.Piece;
import tetris.model.Shape;

public class BoardView extends View
{
	BoardRenderer renderer;

	public static int 
		PARTIAL_REDRAW = Board.PIECE_MOVED,
		FULL_REDRAW = Board.GARBAGE_RECEIVED | Board.LINES_CLEARED | Board.PIECE_DROPPED;
	
	Piece lastPiece = new Piece();
	Piece lastGhostPiece = new Piece();

	public BoardView(Model model, BoardRenderer renderer) 
	{
		super(model);
		this.renderer = renderer;
	}

	public Board getModel () 
	{
		return (Board)model;
	}
	
	public void onPartialDraw ()
	{
		renderer.renderPartial(this, getModel());
	}
	
	public void onFullDraw ()
	{
		renderer.render(this, getModel());
	}

	public void onDraw ()
	{
		if (getModel().getFlags().has(FULL_REDRAW))
			onFullDraw();
		else
		if (getModel().getFlags().has(PARTIAL_REDRAW))
			onPartialDraw();
		
		lastPiece.copyFrom(getModel().getCurrentPiece());
	}

	public Piece getLastPiece() 
	{
		if (lastPiece.getShape() == Shape.NONE)
			return null;
		
		return lastPiece;
	}
	
	public Piece getLastGhostPiece ()
	{
		if (lastGhostPiece.getShape() == Shape.NONE)
			return null;
		
		return lastGhostPiece;
	}
}
