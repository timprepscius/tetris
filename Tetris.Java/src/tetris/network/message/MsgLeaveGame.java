package tetris.network.message;

import tetris.network.Message;
import tetris.network.MessageType;

public class MsgLeaveGame extends Message
{
	public MsgLeaveGame ()
	{
		super(MessageType.LEAVE_GAME);
	}
}
