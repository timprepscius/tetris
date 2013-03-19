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

public class PlayerConnection extends ConnectionAdapter
{
	ID playerID;
	
	public PlayerConnection(ID playerID, Connection connection)
	{
		super(connection);
		this.playerID = playerID;
	}
	
	@Override
	public void onMessage(Connection connection, Message message)
	{
		if (playerID != null && !playerID.equals(message.getPlayerID()))
			return;

		super.onMessage(connection, message);
	}

	@Override
	public void sendMessage(Message message)
	{
		message.setPlayerID(playerID);
		super.sendMessage(message);
	}

}
