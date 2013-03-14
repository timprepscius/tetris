package tetris.server;

import java.util.Date;

import tetris.model.PlayerInfo;
import tetris.network.Connection;
import tetris.network.ID;

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
