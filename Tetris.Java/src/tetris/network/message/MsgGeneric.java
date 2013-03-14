package tetris.network.message;

import tetris.network.Message;
import tetris.network.MessageType;

public class MsgGeneric extends Message
{
	byte[] bytes;
	
	public MsgGeneric (MessageType messageType, byte[] bytes)
	{
		super(messageType);
		this.bytes = bytes;
	}
	
	public MsgGeneric(MessageType messageType)
	{
		super(messageType);
	}

	public byte[] serialize ()
	{
		return bytes;
	}

	public void deserialize(byte[] bytes)
	{
		this.bytes = bytes;
	}
	
	public byte[] getBytes ()
	{
		return bytes;
	}
}
