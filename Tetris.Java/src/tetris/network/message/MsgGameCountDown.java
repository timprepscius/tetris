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
import tetris.network.Message;
import tetris.network.MessageType;

public class MsgGameCountDown extends Message
{
	int countdown;
	
	public MsgGameCountDown ()
	{
		super(MessageType.GAME_COUNTDOWN);
	}
	
	public MsgGameCountDown (int countdown)
	{
		super(MessageType.GAME_COUNTDOWN);
		this.countdown = countdown;
	}
	
	@Override
	public void serialize (Out out)
	{
		out.writeInteger(countdown);
	}
	
	public void deserialize (In in)
	{
		countdown = in.readInteger();
	}
	
	public int getCountDown ()
	{
		return countdown;
	}
}
