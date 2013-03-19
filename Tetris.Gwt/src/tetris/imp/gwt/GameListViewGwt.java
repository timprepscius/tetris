package tetris.imp.gwt;

import tetris.model.GameInfo;
import tetris.model.ID;
import tetris.model.Model;
import tetris.view.GameListView;

public class GameListViewGwt extends GameListView {

	public GameListViewGwt(Model model) 
	{
		super(model);
	}
	
	public void onDraw ()
	{
		// clear the list
		Bridge.invoke(getJS(), "html", "");
		
		// append each item
		for (GameInfo info : getModel().getGames())
			Bridge.invoke(getJS(), "append", Bridge.createGameListItem(this, info));
	}
	
	public void onJoin (ID id)
	{
		for (GameInfo info : getModel().getGames())
		{
			if (info.getID().equals(id))
				getModel().joinGame(info);
		}
	}
	
}
