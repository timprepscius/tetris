package tetris.renderer;

import tetris.model.Board;
import tetris.view.BoardView;
import tetris.view.View;

public interface BoardRenderer
{
	public void render (BoardView view, Board board);
	public void renderPartial(BoardView boardView, Board model);
}
