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

import tetris.model.GameInfo;
import tetris.model.ID;
import tetris.model.Model;
import tetris.view.GameListView;

public class GameListViewGwt extends GameListView {

	public GameListViewGwt(Model model) 
	{
		super(model);
	}
	
	public void onDraw ()
	{
		// clear the list
		Bridge.invoke(getJS(), "html", "");
		
		// append each item
		for (GameInfo info : getModel().getGames())
			Bridge.invoke(getJS(), "append", Bridge.createGameListItem(this, info));
	}
	
	public void onJoin (ID id)
	{
		for (GameInfo info : getModel().getGames())
		{
			if (info.getID().equals(id))
				getModel().joinGame(info);
		}
	}
	
}
