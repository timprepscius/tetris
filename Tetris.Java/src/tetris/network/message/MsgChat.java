///////////////////////////////////////////////////////////////////////////////
// Copyright (C) 2013 Timothy Prepscius
//
// This is a viewable source license.  No copying or modification or use is
// permitted.
//
// If you would like to participate in its development, or
// if you would like a different license, please contact the author.
///////////////////////////////////////////////////////////////////////////////

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
