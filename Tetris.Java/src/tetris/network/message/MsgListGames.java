package tetris.network.message;

import tetris.network.Message;
import tetris.network.MessageType;

public class MsgListGames extends Message
{
	public MsgListGames ()
	{
		super(MessageType.LIST_GAME);
	}
}
