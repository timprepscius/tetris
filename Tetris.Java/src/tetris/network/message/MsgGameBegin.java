package tetris.network.message;

import tetris.network.Message;
import tetris.network.MessageType;

public class MsgGameBegin extends Message
{
	public MsgGameBegin ()
	{
		super(MessageType.GAME_BEGIN);
	}

}
