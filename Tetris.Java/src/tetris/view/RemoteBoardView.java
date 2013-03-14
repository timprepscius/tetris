package tetris.view;

import tetris.model.Model;
import tetris.model.RemoteBoard;
import tetris.renderer.BoardRenderer;

public class RemoteBoardView extends BoardView
{

	public RemoteBoardView(Model view, BoardRenderer renderer)
	{
		super(view, renderer);
	}

	public RemoteBoard getModel ()
	{
		return (RemoteBoard)model;
	}
	
	public void draw ()
	{
		super.draw();
		renderer.render(this, getModel());
	}
}
