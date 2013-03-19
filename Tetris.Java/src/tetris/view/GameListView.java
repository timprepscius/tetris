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

import tetris.model.GameList;
import tetris.model.Model;

public class GameListView extends View
{
	public GameListView(Model model)
	{
		super(model);
	}
	
	public GameList getModel()
	{
		return (GameList)model;
	}
	
	public void testDraw ()
	{
		if (getModel().getFlags().has(GameList.DIRTY))
			requestDraw();
	}
}
