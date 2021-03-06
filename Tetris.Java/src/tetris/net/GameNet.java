///////////////////////////////////////////////////////////////////////////////
// Copyright (C) 2013 Timothy Prepscius
//
// This is a viewable source license.  No copying or modification or use is
// permitted.
//
// If you would like to participate in its development, or
// if you would like a different license, please contact the author.
///////////////////////////////////////////////////////////////////////////////

package tetris.net;

import core.util.Ticker;
import tetris.model.Game;
import tetris.network.Connection;
import tetris.network.Message;
import tetris.network.message.MsgEnterGame;
import tetris.network.message.MsgGameCountDown;
import tetris.network.message.MsgLeaveGame;
import tetris.network.message.MsgPing;

public class GameNet extends Net
{
	Ticker pingTicker = new Ticker(1000);
	
	public GameNet(Game model, Connection connection)
	{
		super(model, connection);
		connection.addReceiver(this);
	}
	
	public Game getModel ()
	{
		return (Game)model;
	}

	public void onTick ()
	{
		if (pingTicker.tick())
			getConnection().sendMessage(new MsgPing());
	}
	
	public void onStart ()
	{
		getConnection().sendMessage(new MsgEnterGame(getModel().getLocalBoard().getPlayerInfo()));
		pingTicker.start(false);
	}
	
	public void onStop ()
	{
		pingTicker.stop();
		getConnection().sendMessage(new MsgLeaveGame());
	}
	
	public void onMessage(Message message)
	{
		switch (message.getType())
		{
		case ENTER_GAME:
			getModel().handleEnter(((MsgEnterGame)message).getPlayerInfo());
		break;
			
		case LEAVE_GAME:
			getModel().handleLeave(message.getPlayerID());
		break;
		
		case GAME_COUNTDOWN:
			getModel().handleGameCountdown(((MsgGameCountDown)message).getCountDown());
		break;
		
		case GAME_BEGIN:
			getModel().handleGameBegin();
		break;
		
		default: 
			break;
		}
		
	}
}
