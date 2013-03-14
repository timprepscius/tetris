package tetris.imp.gwt;

import tetris.model.BoardMatrix;
import tetris.model.Piece;
import tetris.model.Shape;

public class PieceRendererGwt 
{
	static String defaultColors[] = new String[] {
		"green", 
		"blue", 
		"indigo", 
		"orange", 
		"red", 
		"purple", 
		"maroon", 
		"black", 
		"magenta"
	};

	public String getColor (Shape shape)
	{
		return defaultColors[shape.ordinal()];
	}
	
	public String getGhostColor ()
	{
		return "grey";
	}
	
	public void drawPiece (CanvasInfoGwt canvas, Piece piece, BoardMatrix b, boolean isGhost)
	{
		for (int i = 0; i < 4; ++i) 
		{
			int x = piece.X(i);
			int y = piece.Y(i);
			
			drawBlock(
				canvas,
				isGhost ? getGhostColor() : getColor(piece.getShape()),
				x, y, b
			);
		}
	}
	
	public void clearPiece (CanvasInfoGwt canvas, Piece piece, BoardMatrix b)
	{
		for (int i = 0; i < 4; ++i) 
		{
			int x = piece.X(i);
			int y = piece.Y(i);
			
			clearBlock(canvas, x, y, b);
		}
	}

	public void drawBlock (CanvasInfoGwt canvas, String color, int x, int y, BoardMatrix b)
	{
		float bx = b.mx * x + b.ax;
		float by = b.my * y + b.ay;
		
		drawBlockWithBevel(
			canvas.context, 
			color,
			bx, by, 
			b.pw, b.ph, 
			0.05f
		);
	}
	
	public void clearBlock (CanvasInfoGwt canvas, int x, int y, BoardMatrix b)
	{
		float bx = b.mx * x + b.ax;
		float by = b.my * y + b.ay;
		canvas.clear(bx, by, b.pw, b.ph);
	}
	
	public native void drawBlockWithBevel(Object context, Object color, float x, float y, float w, float h, float b) /*-{
	    context.globalAlpha = 1;
	    context.fillStyle = color;
	    context.fillRect(x, y, w, h);
	    context.fillRect(x + (b*w), y + (b*h), w - (2*b*w), h - (2*b*h));
	}-*/;


}
