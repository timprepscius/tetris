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

import tetris.network.Message;

public class RemoteBoard extends Board
{
	public RemoteBoard(PlayerInfo playerInfo)
	{
		super(playerInfo);
	}

	public void handleMessage (Message message)
	{
		
	}
}
