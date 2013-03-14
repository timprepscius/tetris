package tetris.network.message;

import tetris.model.PlayerInfo;
import tetris.network.ID;
import tetris.network.Message;
import tetris.network.MessageType;

public class MsgInitialize extends MsgPlayerInfo
{
	public MsgInitialize (PlayerInfo playerInfo)
	{
		super(MessageType.INITIALIZE, playerInfo);
	}

	public MsgInitialize()
	{
		super(MessageType.INITIALIZE);
	}
}
