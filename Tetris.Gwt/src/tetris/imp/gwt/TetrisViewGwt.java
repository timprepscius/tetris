package tetris.imp.gwt;

import tetris.model.Model;
import tetris.view.TetrisView;

public class TetrisViewGwt extends TetrisView
{

	public TetrisViewGwt(Model model) {
		super(model);
	}
	
	public void onJoinGame ()
	{
		Bridge.hide(Bridge.chooseRoomContainer);
		Bridge.show(Bridge.playContainer);
	}
	
	public void onLeaveGame ()
	{
		Bridge.hide(Bridge.playContainer);
		Bridge.hide(Bridge.chooseRoomContainer);
	}
}
