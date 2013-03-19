///////////////////////////////////////////////////////////////////////////////
// Copyright (C) 2013 Timothy Prepscius
//
// This is a viewable source license.  No copying or modification or use is
// permitted.
//
// If you would like to participate in its development, or
// if you would like a different license, please contact the author.
///////////////////////////////////////////////////////////////////////////////

package tetris.imp.gwt;

import com.google.gwt.core.client.JavaScriptObject;

import tetris.model.Bag;
import tetris.model.Chat;
import tetris.model.Game;
import tetris.model.GameList;
import tetris.model.LocalBoard;
import tetris.model.RemoteBoard;
import tetris.model.Tetris;
import tetris.view.BagView;
import tetris.view.ChatView;
import tetris.view.GameListView;
import tetris.view.GameView;
import tetris.view.LocalBoardView;
import tetris.view.RemoteBoardView;
import tetris.view.TetrisView;
import tetris.view.View;
import tetris.view.ViewFactory;

public class ViewFactoryGwt extends ViewFactory
{
	@Override
	public View instantiateFor(Bag bag) 
	{
		BagView view = new BagViewGwt(bag);
		view.setJS(Bridge.localBag);
		
		return view;
	}

	@Override
	public View instantiateFor(LocalBoard board) 
	{
		LocalBoardView view = new LocalBoardViewGwt(board, new BoardRendererGwt());
		view.setJS(Bridge.localBoard);
		
		return view;
	}

	@Override
	public View instantiateFor(RemoteBoard board) 
	{
		RemoteBoardView view = new RemoteBoardView(board, new BoardRendererGwt());
		for (JavaScriptObject link : Bridge.remoteBoards)
		{
			if (!Bridge.remoteBoardsInUse.contains(link))
			{
				view.setJS(link);
				Bridge.remoteBoardsInUse.add(link);
				break;
			}
		}
		
		return view;
	}

	@Override
	public View instantiateFor(Game arena) 
	{
		GameView view = new GameViewGwt (arena);
		return view;
	}

	@Override
	public View instantiateFor(GameList gameList) 
	{
		GameListView view = new GameListViewGwt (gameList);
		view.setJS(Bridge.gameList);
		
		return view;
	}

	@Override
	public View instantiateFor(Chat model) 
	{
		ChatView view = new ChatViewGwt (model);
		return view;
	}

	@Override
	public View instantiateFor(Object parent, Tetris tetris) 
	{
		TetrisView view = new TetrisViewGwt(tetris);
		return view;
	}
}
