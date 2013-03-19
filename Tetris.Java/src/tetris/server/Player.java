///////////////////////////////////////////////////////////////////////////////
// Copyright (C) 2013 Timothy Prepscius
//
// This is a viewable source license.  No copying or modification or use is
// permitted.
//
// If you would like to participate in its development, or
// if you would like a different license, please contact the author.
///////////////////////////////////////////////////////////////////////////////

package tetris.server;

import java.util.Date;

import tetris.model.PlayerInfo;
import tetris.network.Connection;
import tetris.model.ID;

public class Player
{
	public Player (PlayerInfo info, Connection connection)
	{
		this.connection = connection;
		this.info = info;
	}
	
	protected Connection connection;
	
	PlayerInfo info;
	ID currentGame;
	ID id;
	
	Date beginTime, endTime;

	Connection getConnection()
	{
		return connection;
	}
	
	PlayerInfo getInfo ()
	{
		return info;
	}
}
