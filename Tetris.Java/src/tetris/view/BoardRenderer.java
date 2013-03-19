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

public interface BoardRenderer
{
	public void render (BoardView view, Board board);
	public void renderPartial(BoardView boardView, Board model);
}
