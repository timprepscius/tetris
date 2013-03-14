package tetris.net;

import tetris.model.GameList;
import tetris.model.Model;
import tetris.network.Connection;
import tetris.network.Message;
import tetris.network.MessageType;
import tetris.network.message.MsgListGames;
import tetris.network.message.MsgListGamesResult;
import core.util.Ticker;

public class GameListNet extends Net
{
	Ticker requestAvailableGamesTicker = new Ticker(10 * 1000);
	
	public GameListNet(Model model, Connection connection)
	{
		super(model, connection);
		connection.addReceiver(this);
	}
	
	public GameList getModel ()
	{
		return (GameList)model;
	}
	
	public void onTick ()
	{
		if (requestAvailableGamesTicker.tick())
			getConnection().sendMessage(new MsgListGames());
	}

	@Override
	public void onMessage(Message message)
	{
		if (message.getType() == MessageType.LIST_GAME_RESULT)
		{
			getModel().setGames(((MsgListGamesResult)message).getInfos());
		}
	}
	
	public void onStart ()
	{
		requestAvailableGamesTicker.start(true);
	}

}
