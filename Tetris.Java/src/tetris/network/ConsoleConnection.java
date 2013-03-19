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
