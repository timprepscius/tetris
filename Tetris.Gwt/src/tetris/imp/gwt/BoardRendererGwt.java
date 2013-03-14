package tetris.imp.gwt;

import tetris.model.Board;
import tetris.model.BoardMatrix;
import tetris.model.LocalBoard;
import tetris.model.Piece;
import tetris.model.Shape;
import tetris.renderer.BoardRenderer;
import tetris.view.BoardView;
import tetris.view.View;

public class BoardRendererGwt implements BoardRenderer
{
	CanvasInfoGwt canvas = new CanvasInfoGwt();
	BoardMatrix boardMatrix = new BoardMatrix();
	
	PieceRendererGwt pieceRenderer = new PieceRendererGwt();
	
	public void renderPartial (BoardView view, Board board)
	{
		canvas.calculate(view.getJS());
		boardMatrix.calculate(canvas, board);

		if (view.getLastPiece()!=null)
		{
			pieceRenderer.clearPiece(canvas, view.getLastPiece(), boardMatrix);
			
			if (view.getLastGhostPiece()!=null)
				pieceRenderer.clearPiece(canvas, view.getLastGhostPiece(), boardMatrix);
		}

		if (board.getCurrentPiece().getShape() != Shape.NONE) 
		{
			pieceRenderer.drawPiece(canvas, board.getCurrentPiece(), boardMatrix, false);
			
			if (board instanceof LocalBoard)
				pieceRenderer.drawPiece(canvas, ((LocalBoard)board).getGhostPiece(), boardMatrix, true);
		}
		
	}
	
	public void render(BoardView view, Board board)
	{ 
		canvas.calculate(view.getJS());
		boardMatrix.calculate(canvas, board);
		
		canvas.clear();

		for (int i = 0; i < board.height; ++i) 
		{
			for (int j = 0; j < board.width; ++j) 
			{
				Shape shape = board.shapeAt(j, i);
				if (shape != Shape.NONE)
				{
					pieceRenderer.drawBlock(
						canvas,
						pieceRenderer.getColor(shape),
						j, i, 
						boardMatrix
					);
				}
			}
		}

		if (board.getCurrentPiece().getShape() != Shape.NONE) 
		{
			pieceRenderer.drawPiece(canvas, board.getCurrentPiece(), boardMatrix, false);
			
			if (board instanceof LocalBoard)
				pieceRenderer.drawPiece(canvas, ((LocalBoard)board).getGhostPiece(), boardMatrix, true);
		}
	}

}
