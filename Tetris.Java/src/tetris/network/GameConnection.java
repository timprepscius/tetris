///////////////////////////////////////////////////////////////////////////////
// Copyright (C) 2013 Timothy Prepscius
//
// This is a viewable source license.  No copying or modification or use is
// permitted.
//
// If you would like to participate in its development, or
// if you would like a different license, please contact the author.
///////////////////////////////////////////////////////////////////////////////

package tetris.network;

import tetris.model.ID;

public class GameConnection extends ConnectionAdapter
{
	ID gameID;
	
	public GameConnection(ID gameID, Connection connection)
	{
		super(connection);
		this.gameID = gameID;
	}
	
	@Override
	public void onMessage(Connection connection, Message message)
	{
		if (!gameID.equals(message.getGameID()))
			return;
		
		super.onMessage(connection, message);
	}

	@Override
	public void sendMessage(Message message)
	{
		message.setGameID(gameID);
		super.sendMessage(message);
	}

}
