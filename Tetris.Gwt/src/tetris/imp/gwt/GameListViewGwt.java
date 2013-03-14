package tetris.imp.gwt;

import tetris.model.GameInfo;
import tetris.model.Model;
import tetris.view.GameListView;

public class GameListViewGwt extends GameListView {

	public GameListViewGwt(Model model) 
	{
		super(model);
	}
	
	public void onDraw ()
	{
		Bridge.clearContainer(getJS());
		for (GameInfo info : getModel().getGames())
		{
			Bridge.addItemToContainer(getJS(), Bridge.createGameListItem(this, info.id.toString(), info.name));
		}
	}
	
	public void onJoin (String id)
	{
		for (GameInfo info : getModel().getGames())
		{
			if (info.id.toString().equals(id))
				getModel().joinGame(info);
		}
	}
	
	//------------------------------------------------
	
	public static void onJoin (Object self, String id)
	{
		((GameListViewGwt)self).onJoin(id);
	}

}
