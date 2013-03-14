package tetris.imp.awt;

import java.awt.Component;
import java.awt.Container;

import javax.swing.BoxLayout;

import tetris.model.Bag;
import tetris.model.Chat;
import tetris.model.Game;
import tetris.model.GameList;
import tetris.model.LocalBoard;
import tetris.model.RemoteBoard;
import tetris.model.Tetris;
import tetris.view.BagView;
import tetris.view.ChatView;
import tetris.view.GameView;
import tetris.view.LocalBoardView;
import tetris.view.RemoteBoardView;
import tetris.view.View;
import tetris.view.ViewFactory;

public class ViewFactoryAwt extends ViewFactory
{
	@Override
	public View instantiateFor(LocalBoard board)
	{
		LocalBoardView view = new LocalBoardViewAwt(board, new BoardRendererAwt());
        view.setAlignmentX(Component.CENTER_ALIGNMENT);
        view.setSize(200, 400);
		board.getParent().getView().add(view);
		
		return view;
	}

	@Override
	public View instantiateFor(RemoteBoard board)
	{
		RemoteBoardView view = new RemoteBoardView(board, new BoardRendererAwt());
        view.setAlignmentX(Component.CENTER_ALIGNMENT);
        view.setSize(200, 400);
		board.getParent().getView().add(view);
		
		return view;
	}

	@Override
	public View instantiateFor(Game game)
	{
        GameView view = new GameView(game);
        view.setLayout(new BoxLayout(view, BoxLayout.X_AXIS));
        game.getParent().getView().add(view);
        
        return view;
	}
	
	@Override
	public View instantiateFor(GameList gameList)
	{
		GameListViewAwt view = new GameListViewAwt(gameList);
        gameList.getParent().getView().add(view);
		
        return view;
	}

	public View instantiateFor (Object parent, Tetris tetris)
	{
		TetrisViewAwt view = new TetrisViewAwt(tetris);
        view.setLayout(new BoxLayout(view, BoxLayout.X_AXIS));
		((Container)parent).add(view);
		
		return view;
	}

	@Override
	public View instantiateFor(Bag model) {
		return new BagView(model);
	}

	@Override
	public View instantiateFor(Chat model) {
		return new ChatView(model);
	}
}
