package tetris.model;

import tetris.view.KeyListener;

public class LocalBoard extends Board
{
	public static final int GAME_END = 0x01;

	protected Piece ghostPiece = new Piece();
	protected Piece testPiece = new Piece();

	KeyListener keyListener = new TAdapter();
	boolean heldOnceAlready = false;
	Bag bag;
	
	public LocalBoard(PlayerInfo playerInfo, Bag bag)
	{
		super(playerInfo);
		this.bag = bag;
		
		addModel(bag);
		bag.start();
	}

	public Piece getGhostPiece() 
	{
		return ghostPiece;
	}
	
	public KeyListener getKeyListener ()
	{
		return keyListener;
	}
	
	public void onStart ()
	{
	}
	
	public void onGameBegin ()
	{
		super.onGameBegin();
		newPiece();
	}
	
	public void onGameEnd ()
	{
		super.onGameEnd();
	}
	
	public void endGame ()
	{
		isRunning = false;
		getFlags().mark(GAME_END);
	}

	public void onTick() 
	{
		if (isRunning)
			oneLineDown();
	}

	public void dropDown()
	{
		while (tryMove(0, 0, -1))
		{
			// continue moving it
		}

		pieceDropped();
	}

	public void oneLineDown()
	{
		if (!tryMove(0, 0, -1))
			pieceDropped();
	}

	public void pieceDropped()
	{
		for (int i = 0; i < 4; ++i) 
		{
			int x = currentPiece.X(i);
			int y = currentPiece.Y(i);
			board.get(y)[x] = currentPiece.getShape();
		}

		getFlags().mark(PIECE_DROPPED);
		removeFullLines();
		newPiece();
	}

	public void calculateGhostPiece ()
	{
		ghostPiece.copyFrom(currentPiece);
		
		while (tryMove(ghostPiece, 0,0,-1))
		{
			// keep on moving
		}
	}
	
	void testEnd ()
	{
		if (!tryMove(0, 0, 0))
			endGame();
	}
	
	private void newPiece()
	{
		/*
		if (numPieces++ % 10 == 0)
			clearBoard();
		*/
		
		heldOnceAlready = false;
		currentPiece.initialize(bag.getAndConsumeNextShape(), width / 2 + 1, height - 1);
		testEnd();
	}
	
	void hold ()
	{
		currentPiece.initialize(bag.switchHold(currentPiece.getShape()), width / 2 + 1, height - 1);
		getFlags().mark(PIECE_MOVED);
		testEnd();
	}
	
	boolean tryMove(int dr, int dx, int dy)
	{
		boolean result = tryMove(currentPiece, dr, dx, dy);
		if (result)
		{
			getFlags().mark(PIECE_MOVED);
			calculateGhostPiece();
		}
		
		return result;
	}
	
	boolean tryMove(Piece piece, int dr, int dx, int dy)
	{
		testPiece.copyFrom(piece);
		testPiece.applyDelta(dr, dx, dy);

		for (int i = 0; i < 4; ++i) {
			int x = testPiece.X(i);
			int y = testPiece.Y(i);

			if (y < 0 || x < 0 || x >= width)
				return false;

			if (shapeAt(x, y) != Shape.NONE)
				return false;
		}

		piece.copyFrom(testPiece);
		return true;
	}

	boolean tryHold ()
	{
		if (!heldOnceAlready)
		{
			hold();
			heldOnceAlready = true;
			return true;
		}
		
		return false;
	}
	
	private void removeFullLines()
	{
		int numFullLines = 0;

		for (int i = height - 1; i >= 0; --i) {
			boolean lineIsFull = true;

			for (int j = 0; j < width; ++j) {
				if (shapeAt(j, i) == Shape.NONE) {
					lineIsFull = false;
					break;
				}
			}

			if (lineIsFull) {
				++numFullLines;
				board.remove(i);
			}
		}

		if (numFullLines > 0) {
			numLinesRemoved += numFullLines;
			getFlags().mark(LINES_CLEARED);
		}
	}


	class TAdapter implements KeyListener 
	{
		public void keyRepeat(KeyState keyState)
		{
			for (Integer i : keyState.getKeys())
				keyPress(i);
		}
		
		public boolean keyPress(int keycode) 
		{
			if (isRunning)  
			{
				switch (keycode) {
				case KeyCodes.LEFT:
					tryMove(0, -1, 0);
					break;
				case KeyCodes.RIGHT:
					tryMove(0, 1, 0);
					break;
				case KeyCodes.DOWN:
					tryMove(0, 0, -1);
					break;
				case KeyCodes.UP:
					tryMove(1, 0, 0);
					break;
				case KeyCodes.SPACE:
					dropDown();
					break;
				case 'd':
					oneLineDown();
					break;
				case 'D':
					oneLineDown();
					break;
				case KeyCodes.LSHIFT:
					tryHold();
					break;
				}
			}
			return true;
		}
	}
}
