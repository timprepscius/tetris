package tetris.network.message;

import tetris.network.Message;
import tetris.network.MessageType;

public class MsgGameEnd extends Message
{
	public MsgGameEnd ()
	{
		super(MessageType.GAME_END);
	}
}
