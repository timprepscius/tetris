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
import tetris.model.PlayerInfo;
import tetris.network.Message;
import tetris.network.MessageType;

public class MsgPlayerInfo extends Message
{
	PlayerInfo playerInfo;
	
	protected MsgPlayerInfo(MessageType messageType)
	{
		super(messageType);
	}
	
	protected MsgPlayerInfo(MessageType messageType, PlayerInfo playerInfo)
	{
		super(messageType);
		this.playerInfo = playerInfo;
	}
	
	public MsgPlayerInfo (PlayerInfo playerInfo)
	{
		super(MessageType.PLAYER_INFO);
		this.playerInfo = playerInfo;
	}

	public MsgPlayerInfo()
	{
		super(MessageType.PLAYER_INFO);
	}
	
	@Override
	public void serialize (Out out)
	{
		playerInfo.serialize(out);
	}
	
	@Override
	public void deserialize (In in)
	{
		playerInfo = new PlayerInfo();
		playerInfo.deserialize(in);
	}
	
	public PlayerInfo getPlayerInfo ()
	{
		return playerInfo;
	}

}
