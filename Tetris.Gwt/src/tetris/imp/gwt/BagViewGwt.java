package tetris.imp.gwt;

import tetris.model.BoardMatrix;
import tetris.model.Model;
import tetris.model.Piece;
import tetris.model.Shape;
import tetris.view.BagView;

public class BagViewGwt extends BagView
{
	static final int 
		NUM_SHOWN = 5, 
		BAG_HEIGHT = 22, 
		BAG_WIDTH = 3;
	
	static final int
		HOLD_HEIGHT = 4,
		HOLD_WIDTH = 3;
	
	PieceRendererGwt pieceRenderer = new PieceRendererGwt();
	
	CanvasInfoGwt bagCanvas = new CanvasInfoGwt();
	BoardMatrix bagBoardMatrix = new BoardMatrix();
	
	CanvasInfoGwt holdCanvas = new CanvasInfoGwt();
	BoardMatrix holdBoardMatrix = new BoardMatrix();

	public BagViewGwt(Model model) 
	{
		super(model);
	}
	
	public void onDraw ()
	{
		bagCanvas.calculate(getJS());
		bagBoardMatrix.calculate(bagCanvas.w, bagCanvas.h, BAG_WIDTH, BAG_HEIGHT);
		bagCanvas.clear();
		
		float ph = BAG_HEIGHT/NUM_SHOWN;
		int ii = NUM_SHOWN;
		for (int i=0; i<NUM_SHOWN; ++i, --ii)
		{
			int y = (int) (ii * ph);
			int x = 1;
			
			Shape shape = getModel().getShape(i);
			Piece piece = new Piece();
			piece.applyDelta(0, x, y);
			piece.setShape(shape);
			pieceRenderer.drawPiece(bagCanvas, piece, bagBoardMatrix, false);
		}

		holdCanvas.calculate(Bridge.localHold);
		holdBoardMatrix.calculate(holdCanvas.w, holdCanvas.h, HOLD_WIDTH, HOLD_HEIGHT);
		holdCanvas.clear();
		{
			Shape shape = getModel().getHold();
			if (shape != null)
			{
				Piece piece = new Piece();
				piece.setShape(shape);
				piece.applyDelta(0, 1, 2);
				pieceRenderer.drawPiece(holdCanvas, piece, holdBoardMatrix, false);
			}
		}
	}

}
