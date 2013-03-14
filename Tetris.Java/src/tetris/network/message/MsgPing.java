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
