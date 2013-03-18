package tetris.imp.gwt;

import tetris.model.BoardMatrix;
import tetris.model.Piece;
import tetris.model.Shape;

public class PieceRendererGwt 
{
	static ColorGwt defaultColors[] = new ColorGwt[] {
		new ColorGwtOptimized(255,0,0),
		new ColorGwtOptimized(0,255,0),
		new ColorGwtOptimized(0,0,255),
		new ColorGwtOptimized(128,128,0),
		new ColorGwtOptimized(0,128,128),
		new ColorGwtOptimized(64,200,64),
		new ColorGwtOptimized(36,100,22),
		new ColorGwtOptimized(100,36,22),
		new ColorGwtOptimized(36,22,100),
	};
	
	static ColorGwt defaultGhostColor = new ColorGwtOptimized (128,128,128);

	public ColorGwt getColor (Shape shape)
	{
		return defaultColors[shape.ordinal()];
	}
	
	public ColorGwt getGhostColor ()
	{
		return defaultGhostColor;
	}
	
	public void drawPiece (CanvasInfoGwt canvas, Piece piece, BoardMatrix b, boolean isGhost)
	{
		for (int i = 0; i < 4; ++i) 
		{
			int x = piece.X(i);
			int y = piece.Y(i);
			
			ColorGwt color = isGhost ? getGhostColor() : getColor(piece.getShape());
			
			drawBlock(
				canvas,
				color,
				x, y, 
				b
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

	public void drawBlock (CanvasInfoGwt canvas, ColorGwt color, int x, int y, BoardMatrix b)
	{
		float bx = b.mx * x + b.ax;
		float by = b.my * y + b.ay;
		
		drawBlockWithBevel(
			canvas.context, 
			color.getString(),
			bx, by, 
			b.pw, b.ph, 
			0.05f,
			color.makeBrighter().getString()
		);
	}
	
	public void clearBlock (CanvasInfoGwt canvas, int x, int y, BoardMatrix b)
	{
		float bx = b.mx * x + b.ax;
		float by = b.my * y + b.ay;
		canvas.clear(bx, by, b.pw, b.ph);
	}
	
	public native void drawBlockWithBevel(
		Object context, String color, 
		float x, float y, float w, float h, 
		float bevel, String bevelColor
	) /*-{
	    context.fillStyle = color;
	    context.fillRect(x, y, w, h);
	    context.fillStyle = bevelColor;
	    context.fillRect(
	    	x + (bevel*w), y + (bevel*h), 
	    	w - (2*bevel*w), h - (2*bevel*h)
	    );
	}-*/;


}
