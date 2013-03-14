package tetris.server;

import java.util.HashMap;
import tetris.TetrisException;
import java.util.Map;

import tetris.model.GameInfo;
import tetris.model.PlayerInfo;
import tetris.network.Connection;
import tetris.network.ConsoleConnection;
import tetris.network.ID;
import tetris.network.Message;
import tetris.network.MessageReceiver;
import tetris.network.MessageType;
import tetris.network.message.MsgInitialize;
import tetris.network.message.MsgListGamesResult;

public class Server implements MessageReceiver
{
	public Server()
	{
	}

	Map<Connection, Player> players = new HashMap<Connection, Player>();
	Map<ID, Game> games = new HashMap<ID, Game>();
	
	public void setupOneGame ()
	{
		ID id = ID.fromLong(1);
		GameInfo info = new GameInfo();
		info.id = id;
		info.name = "Dummy Game";
		
		games.put(id, new Game(info));
	}
	
	public void handleMessage (Player player, Message message)
	{
		if (message.getType() == MessageType.ENTER_GAME)
		{
			Game game = games.get(message.getGameID());
			game.handleMessage(player, message);
		}
		else
		if (message.getType() == MessageType.LIST_GAME)
		{
			MsgListGamesResult msg = new MsgListGamesResult();
			for (Game game : games.values())
				msg.addGame(game.getInfo());
			
			player.getConnection().sendMessage(msg);
		}
		else
		if (message.getGameID() != null)
		{
			ID gameID = message.getGameID();
			
			if (gameID.equals(player.currentGame))
			{
				Game game = games.get(gameID);
				game.handleMessage(player, message);
			}
		}
	}
	
	public void onNewConnection (Connection connection)
	{
		ID id = ID.random();
		ConsoleConnection consoleConnection = new ConsoleConnection(id, connection);
		consoleConnection.addReceiver(this);
	}
	
	public void handleInitializeRequest(Connection connection, Message message)
	{
		ID id = ID.random();
		PlayerInfo playerInfo = new PlayerInfo();
		playerInfo.id = id;
		playerInfo.name = "lolz:"+id.toString();
		
		Player player = new Player(playerInfo, connection);
		players.put(connection, player);
		
		connection.sendMessage(new MsgInitialize(playerInfo));
	}
	
	public void onClose (Connection connection)
	{
		Player player = players.get(connection);
		if (player == null)
			throw new TetrisException("Unknown connection");
		
		if (player.currentGame != null)
		{
			Game game = games.get(player.currentGame);
			game.onCloseConnection(player);
		}
		
		players.remove(connection);
	}
	
	public void onMessage (Connection connection, Message message)
	{
		if (message.getType() == MessageType.INITIALIZE_REQUEST)
		{
			handleInitializeRequest(connection, message);
		}
		else
		{
			Player player = players.get(connection);
			
			if (player == null)
				throw new TetrisException("Unknown connection");
	
			handleMessage(player, message);
		}
	}
}
