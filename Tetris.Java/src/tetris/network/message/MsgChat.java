package tetris.network.message;

import core.io.In;
import core.io.Out;
import tetris.network.Message;
import tetris.network.MessageType;

public class MsgChat extends Message
{
	String text;
	
	public MsgChat() {
		super(MessageType.CHAT);
	}
	
	public MsgChat(String text)
	{
		super(MessageType.CHAT);
		this.text = text;
	}
	
	@Override
	public void serialize(Out out)
	{
		out.writeString(text);
	}
	
	@Override
	public void deserialize(In in)
	{
		text = in.readString();
	}
	
	public String getText()
	{
		return text;
	}

}
