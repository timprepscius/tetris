package tetris.view;

import tetris.model.Game;
import tetris.model.Model;

public class GameView extends View
{
	public GameView(Model model)
	{
		super(model);
	}
	
	public Game getModel ()
	{
		return (Game)model;
	}
}
