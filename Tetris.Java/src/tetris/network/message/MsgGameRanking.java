package tetris.network.message;

import tetris.network.Message;
import tetris.network.MessageType;

public class MsgGameRanking extends Message
{
	public MsgGameRanking ()
	{
		super (MessageType.GAME_RANKING);
	}
	
}
