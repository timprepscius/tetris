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

import tetris.model.LocalBoard;
import tetris.model.Model;

public class LocalBoardView extends BoardView
{
	public LocalBoardView(Model view, BoardRenderer renderer)
	{
		super(view, renderer);
		
		setKeyListener(((LocalBoard)model).getKeyListener());
		requestFocus();
	}

	@Override
	public LocalBoard getModel ()
	{
		return (LocalBoard)model;
	}
	
	@Override
	public void onDraw ()
	{
		super.onDraw();
		
		lastGhostPiece.copyFrom(getModel().getGhostPiece());
	}
}
