///////////////////////////////////////////////////////////////////////////////
// Copyright (C) 2013 Timothy Prepscius
//
// This is a viewable source license.  No copying or modification or use is
// permitted.
//
// If you would like to participate in its development, or
// if you would like a different license, please contact the author.
///////////////////////////////////////////////////////////////////////////////

package tetris.imp.awt;

import tetris.model.Model;
import tetris.view.BoardRenderer;
import tetris.view.LocalBoardView;

public class LocalBoardViewAwt extends LocalBoardView
{
	public LocalBoardViewAwt(Model view, BoardRenderer renderer)
	{
		super(view, renderer);
		
		setFocusable(true);
	}
	
	public void onStart ()
	{
		requestFocus();
	}
}
