package tetris.network.message;

import core.io.In;
import core.io.Out;
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

	@Override
	public void serialize (Out out)
	{
		out.writeBytes(bytes);
	}

	@Override
	public void deserialize(In in)
	{
		bytes = in.readBytes();
	}
	
	public byte[] getBytes ()
	{
		return bytes;
	}
}
