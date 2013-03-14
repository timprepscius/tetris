package tetris.network.message;

import core.util.Strings;
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
	
	public byte[] serialize()
	{
		return Strings.toBytes(text);
	}
	
	public void deserialize(byte[] bytes)
	{
		text = Strings.toString(bytes);
	}
	
	public String getText()
	{
		return text;
	}

}
