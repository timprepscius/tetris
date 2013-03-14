package tetris.view;

import tetris.model.GameList;
import tetris.model.Model;

public class GameListView extends View
{
	public GameListView(Model model)
	{
		super(model);
	}
	
	public GameList getModel()
	{
		return (GameList)model;
	}
	
	public void testDraw ()
	{
		if (getModel().getFlags().has(GameList.DIRTY))
			requestDraw();
	}
}
