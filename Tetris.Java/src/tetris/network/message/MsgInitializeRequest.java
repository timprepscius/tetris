package tetris.network.message;

import core.io.In;
import core.io.Out;
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
	
	@Override
	public void serialize (Out out)
	{
		out.writeString(token);
	}
	
	public void deserialize(In in)
	{
		token = in.readString();
	}
}
