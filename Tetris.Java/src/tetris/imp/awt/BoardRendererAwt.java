package tetris.imp.awt;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import tetris.model.Board;
import tetris.model.Piece;
import tetris.model.Shape;
import tetris.renderer.BoardRenderer;
import tetris.view.BoardView;
import tetris.view.View;

public class BoardRendererAwt implements BoardRenderer
{
	public BoardRendererAwt ()
	{
	}
	
	Dimension size;
	int squareWidth, squareHeight;
	
	@Override
	public void renderPartial(BoardView boardView, Board model) {
		render(boardView, model);
	}

	@Override
	public void render(BoardView view, Board board)
	{
		render(view, (Graphics)view.getGraphicsContext(), board);
	}

    void calculateDimensions(View v, Board b) 
    { 
    	size = v.getSize();
    	squareWidth = (int) size.getWidth() / b.width; 
    	squareHeight = (int) size.getHeight() / b.height;
    }
    
	public void render(View view, Graphics g, Board board)
    { 
		calculateDimensions(view, board);
        int boardTop = (int) size.getHeight() - board.height * squareHeight;

        for (int i = 0; i < board.height; ++i) {
            for (int j = 0; j < board.width; ++j) {
                Shape shape = board.shapeAt(j, board.height - i - 1);
                if (shape != Shape.NONE)
                    drawSquare(g, 0 + j * squareWidth,
                               boardTop + i * squareHeight, shape);
            }
        }

        if (board.getCurrentPiece().getShape() != Shape.NONE) {
            for (int i = 0; i < 4; ++i) 
            {
            	Piece piece = board.getCurrentPiece();
            	
                int x = piece.X(i);
                int y = piece.Y(i);
                drawSquare(g, 0 + x * squareWidth,
                           boardTop + (board.height - y - 1) * squareHeight,
                           board.getCurrentPiece().getShape());
            }
        }
    }
    private void drawSquare(Graphics g, int x, int y, Shape shape)
    {
        Color colors[] = { 
        	new Color(0, 0, 0), new Color(204, 102, 102), 
            new Color(102, 204, 102), new Color(102, 102, 204), 
            new Color(204, 204, 102), new Color(204, 102, 204), 
            new Color(102, 204, 204), new Color(218, 170, 0)
        };

        Color color = colors[shape.ordinal()];

        g.setColor(color);
        g.fillRect(x + 1, y + 1, squareWidth - 2, squareHeight - 2);

        g.setColor(color.brighter());
        g.drawLine(x, y + squareHeight - 1, x, y);
        g.drawLine(x, y, x + squareWidth - 1, y);

        g.setColor(color.darker());
        g.drawLine(x + 1, y + squareHeight - 1,
                         x + squareWidth - 1, y + squareHeight - 1);
        g.drawLine(x + squareWidth - 1, y + squareHeight - 1,
                         x + squareWidth - 1, y + 1);

    }
}
