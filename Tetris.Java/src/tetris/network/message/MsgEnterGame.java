package tetris.network.message;

import tetris.model.PlayerInfo;
import tetris.network.Message;
import tetris.network.MessageType;

public class MsgEnterGame extends MsgPlayerInfo
{
	public MsgEnterGame (PlayerInfo playerInfo)
	{
		super(MessageType.ENTER_GAME, playerInfo);
	}
	
	public MsgEnterGame()
	{
		super(MessageType.ENTER_GAME);
	}
	
}
