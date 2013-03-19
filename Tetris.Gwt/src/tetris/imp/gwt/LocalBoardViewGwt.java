///////////////////////////////////////////////////////////////////////////////
// Copyright (C) 2013 Timothy Prepscius
//
// This is a viewable source license.  No copying or modification or use is
// permitted.
//
// If you would like to participate in its development, or
// if you would like a different license, please contact the author.
///////////////////////////////////////////////////////////////////////////////

package tetris.imp.gwt;

import tetris.model.Model;
import tetris.view.BoardRenderer;
import tetris.view.LocalBoardView;

public class LocalBoardViewGwt extends LocalBoardView
{
	static LocalBoardViewGwt instance;
	
	public LocalBoardViewGwt(Model view, BoardRenderer renderer) {
		super(view, renderer);
		
		instance = this;
	}

	public static LocalBoardViewGwt getInstance ()
	{
		return instance;
	}
}
