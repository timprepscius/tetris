package tetris.model;

import tetris.network.Message;

public class RemoteBoard extends Board
{
	public RemoteBoard(PlayerInfo playerInfo)
	{
		super(playerInfo);
	}

	public void handleMessage (Message message)
	{
		
	}
}
