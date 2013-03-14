package tetris.network.message;

import core.util.Strings;
import tetris.network.Message;
import tetris.network.MessageType;

public class MsgInitializeRequest extends Message
{
	String token;
	
	public MsgInitializeRequest()
	{
		super(MessageType.INITIALIZE_REQUEST);
	}
	
	public MsgInitializeRequest(String token)
	{
		super(MessageType.INITIALIZE_REQUEST);
		this.token = token;
	}
	
	public byte[] serialize ()
	{
		return Strings.toBytes(token);
	}
	
	public void deserialize(byte[] bytes)
	{
		token = Strings.toString(bytes);
	}
}
