package tetris.imp.gwt;

import tetris.model.Model;
import tetris.renderer.BoardRenderer;
import tetris.view.LocalBoardView;

public class LocalBoardViewGwt extends LocalBoardView
{
	static LocalBoardViewGwt instance;
	
	public LocalBoardViewGwt(Model view, BoardRenderer renderer) {
		super(view, renderer);
		
		instance = this;
	}

	public static LocalBoardViewGwt getInstance ()
	{
		return instance;
	}
}
