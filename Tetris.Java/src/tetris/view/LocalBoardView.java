package tetris.view;

import tetris.model.LocalBoard;
import tetris.model.Model;
import tetris.renderer.BoardRenderer;

public class LocalBoardView extends BoardView
{
	public LocalBoardView(Model view, BoardRenderer renderer)
	{
		super(view, renderer);
		
		setKeyListener(((LocalBoard)model).getKeyListener());
		requestFocus();
	}

	@Override
	public LocalBoard getModel ()
	{
		return (LocalBoard)model;
	}
	
	@Override
	public void onDraw ()
	{
		super.onDraw();
		
		lastGhostPiece.copyFrom(getModel().getGhostPiece());
	}
}
