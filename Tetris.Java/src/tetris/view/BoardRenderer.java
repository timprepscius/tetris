package tetris.view;

import tetris.model.Board;

public interface BoardRenderer
{
	public void render (BoardView view, Board board);
	public void renderPartial(BoardView boardView, Board model);
}
