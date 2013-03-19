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

import tetris.TetrisException;
import tetris.model.Bag;
import tetris.model.Chat;
import tetris.model.Game;
import tetris.model.GameList;
import tetris.model.LocalBoard;
import tetris.model.Model;
import tetris.model.RemoteBoard;
import tetris.model.Tetris;
import tetris.network.Connection;
import tetris.network.ConnectionAdapter;
import tetris.network.ConsoleConnection;
import tetris.network.GameConnection;
import tetris.network.PlayerConnection;

public class NetFactory
{
	protected static NetFactory instance;
	public static void setInstance(NetFactory _instance)
	{
		instance = _instance;
	}
	
	public static NetFactory getInstance ()
	{
		return instance;
	}

	public Net instantiateFor(Model model)
	{
		if (model instanceof LocalBoard)
			return instantiateFor((LocalBoard)model);
		if (model instanceof RemoteBoard)
			return instantiateFor((RemoteBoard)model);
		if (model instanceof Game)
			return instantiateFor((Game)model);
		if (model instanceof GameList)
			return instantiateFor((GameList)model);
		if (model instanceof Chat)
			return instantiateFor((Chat)model);
		if (model instanceof Bag)
			return null;
		
		throw new TetrisException("Unknown net to instantiate");
	}
	
	public Net instantiateFor(LocalBoard model)
	{
		return new LocalBoardNet(model, new PlayerConnection(model.getID(), model.getParent().getNet().getConnection()));
	}
	
	public Net instantiateFor(RemoteBoard model)
	{
		return new RemoteBoardNet(model, new PlayerConnection(model.getID(), model.getParent().getNet().getConnection()));
	}
	
	public Net instantiateFor(Game model)
	{
		return new GameNet(model, new GameConnection(model.getID(), model.getParent().getNet().getConnection()));
	}

	public Net instantiateFor(GameList model)
	{
		return new GameListNet(model, new ConnectionAdapter(model.getParent().getNet().getConnection()));
	}

	public Net instantiateFor(Chat model)
	{
		return new ChatNet(model, new ConnectionAdapter(model.getParent().getNet().getConnection()));
	}

	public Net instantiateFor(Tetris model, Connection connection)
	{
		return new TetrisNet(model, new ConsoleConnection(null, connection));
	}

}
