package tetris.network;

import tetris.model.ID;
import core.io.In;
import core.io.Out;

public class Message
{
	protected int type;
	protected ID consoleID, gameID, playerID;

	public Message()
	{
		
	}
	
	public Message(MessageType messageType)
	{
		this.type = messageType.ordinal();
	}
	
	public ID getConsoleID ()
	{
		return consoleID;
	}
	
	public void setConsoleID (ID consoleID)
	{
		this.consoleID = consoleID;
	}

	public ID getGameID ()
	{
		return gameID;
	}
	
	public void setGameID (ID gameID)
	{
		this.gameID = gameID;
	}
	
	public ID getPlayerID ()
	{
		return this.playerID;
	}
	
	public void setPlayerID (ID id)
	{
		this.playerID = id;
	}
	
	public MessageType getType ()
	{
		MessageType[] values = MessageType.values();
		return values[type];
	}
	
	public void deserialize(In in)
	{
	}

	public void serialize(Out out)
	{
	}
}
