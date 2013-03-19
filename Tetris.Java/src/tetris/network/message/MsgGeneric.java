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
