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
		Bridge.invoke(Bridge.chooseRoomContainer, "hide");
		Bridge.invoke(Bridge.playContainer, "show");
	}
	
	public void onLeaveGame ()
	{
		Bridge.invoke(Bridge.playContainer, "hide");
		Bridge.invoke(Bridge.chooseRoomContainer, "show");
	}
}
