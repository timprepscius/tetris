package tetris.model;

import java.util.ArrayList;

public class Board extends ModelSet
{
	public static final int
		PIECE_MOVED = 0x02,
		LINES_CLEARED = 0x04,
		GARBAGE_RECEIVED = 0x08,
		PIECE_DROPPED = 0x10;

	PlayerInfo playerInfo;
	
	public final int width = 10;
	public final int height = 22;

	boolean isRunning = false;
	int numLinesRemoved = 0;
	int numPieces = 0;

	protected Piece currentPiece;

	ArrayList<Shape[]> board;

	public Board(PlayerInfo playerInfo) 
	{
		currentPiece = new Piece();
		clearBoard();  
		
		this.playerInfo = playerInfo;
		setID(playerInfo.id);
	}

	public PlayerInfo getPlayerInfo()
	{
		return playerInfo;
	}

	public Piece getCurrentPiece ()
	{
		return currentPiece;
	}
	
	public void ensureRow (int y)
	{
		while (board.size() <= y)
		{
			Shape[] row = new Shape[width];
			for (int i=0; i<row.length; ++i)
				row[i] = Shape.NONE;

			board.add(row);
		}
	}

	public Shape shapeAt(int x, int y) 
	{
		ensureRow(y);
		return board.get(y)[x]; 
	}

	public void setShapeAt(int x, int y, Shape t)
	{
		ensureRow(y);
		board.get(y)[x] = t;
	}

	public void onGameBegin()
	{
		isRunning = true;
		numLinesRemoved = 0;
		clearBoard();
	}
	
	public void onGameEnd ()
	{
		isRunning = false;
	}

	protected void clearBoard()
	{
		board = new ArrayList<Shape[]>();
		getFlags().mark(LINES_CLEARED);
	}
}