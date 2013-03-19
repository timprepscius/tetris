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

import tetris.model.Model;
import tetris.model.RemoteBoard;

public class RemoteBoardView extends BoardView
{

	public RemoteBoardView(Model view, BoardRenderer renderer)
	{
		super(view, renderer);
	}

	public RemoteBoard getModel ()
	{
		return (RemoteBoard)model;
	}
	
	public void draw ()
	{
		super.draw();
		renderer.render(this, getModel());
	}
}
