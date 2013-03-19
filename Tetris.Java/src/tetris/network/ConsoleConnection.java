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

public class ConsoleConnection extends ConnectionAdapter
{
	ID consoleID;
	
	public ConsoleConnection(ID consoleID, Connection connection)
	{
		super(connection);
		this.consoleID = consoleID;
	}
	
	@Override
	public void onMessage(Connection connection, Message message)
	{
		if (message.getType() == MessageType.INITIALIZE)
			consoleID = message.getConsoleID();
		
		if (!consoleID.equals(message.getConsoleID()) && message.getType() != MessageType.INITIALIZE_REQUEST)
			return;
		
		super.onMessage(connection, message);
	}

	@Override
	public void sendMessage(Message message)
	{
		message.setConsoleID(consoleID);
		super.sendMessage(message);
	}

}
