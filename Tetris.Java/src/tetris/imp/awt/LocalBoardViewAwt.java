package tetris.imp.awt;

import tetris.model.Model;
import tetris.renderer.BoardRenderer;
import tetris.view.LocalBoardView;

public class LocalBoardViewAwt extends LocalBoardView
{
	public LocalBoardViewAwt(Model view, BoardRenderer renderer)
	{
		super(view, renderer);
		
		setFocusable(true);
	}
	
	public void onStart ()
	{
		requestFocus();
	}
}
