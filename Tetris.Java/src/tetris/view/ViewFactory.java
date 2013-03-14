package tetris.view;

import tetris.TetrisException;
import tetris.model.Bag;
import tetris.model.Chat;
import tetris.model.Game;
import tetris.model.GameList;
import tetris.model.LocalBoard;
import tetris.model.Model;
import tetris.model.RemoteBoard;
import tetris.model.Tetris;

public abstract class ViewFactory
{
	protected static ViewFactory instance;
	public static void setInstance(ViewFactory _instance)
	{
		instance = _instance;
	}
	
	public static ViewFactory getInstance ()
	{
		return instance;
	}

	public View instantiateFor(Model model)
	{
		if (model instanceof Bag)
			return instantiateFor((Bag)model);
		if (model instanceof LocalBoard)
			return instantiateFor((LocalBoard)model);
		if (model instanceof RemoteBoard)
			return instantiateFor((RemoteBoard)model);
		if (model instanceof Game)
			return instantiateFor((Game)model);
		if (model instanceof Tetris)
			return instantiateFor((Tetris)model);
		if (model instanceof GameList)
			return instantiateFor((GameList)model);
		if (model instanceof Chat)
			return instantiateFor((Chat)model);
		
		throw new TetrisException("Unknown view to instantiate");
	}
	
	abstract public View instantiateFor(Chat model);
	abstract public View instantiateFor(Bag model);
	abstract public View instantiateFor(LocalBoard model);
	abstract public View instantiateFor(RemoteBoard model);
	abstract public View instantiateFor(Game model);
	abstract public View instantiateFor(GameList model);
	abstract public View instantiateFor(Object parent, Tetris model);
}
