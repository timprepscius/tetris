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

import tetris.model.PlayerInfo;
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
