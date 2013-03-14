package tetris.renderer;

import tetris.model.Board;
import tetris.view.BoardView;

public interface BoardRenderer
{
	public void render (BoardView view, Board board);
	public void renderPartial(BoardView boardView, Board model);
}
