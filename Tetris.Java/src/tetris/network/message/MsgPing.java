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

import tetris.network.Message;
import tetris.network.MessageType;

public class MsgPing extends Message
{
	public MsgPing ()
	{
		super(MessageType.PING);
	}
}
