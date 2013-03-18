package tetris.net;

import tetris.model.Board;
import tetris.model.LocalBoard;
import tetris.model.operation.BoardSync;
import tetris.model.operation.CurrentPiece;
import tetris.network.Connection;
import tetris.network.Message;
import tetris.network.message.MsgGameEnd;

public class LocalBoardNet extends Net
{
	public static final int 
		NEEDS_INCREMENTAL_NETWORK = Board.PIECE_MOVED,
		NEEDS_FULL_NETWORK = Board.LINES_CLEARED | Board.GARBAGE_RECEIVED | Board.PIECE_DROPPED;

	public LocalBoardNet(LocalBoard model, Connection connection)
	{
		super(model, connection);
	}
	
	public LocalBoard getModel ()
	{
		return (LocalBoard)model;
	}
	
	public void onTick ()
	{
		if (getModel().getFlags().has(NEEDS_INCREMENTAL_NETWORK))
		{
			Message message = CurrentPiece.create(getModel());
			getConnection().sendMessage(message);
		}

		if (getModel().getFlags().has(NEEDS_FULL_NETWORK))
		{
			Message message = BoardSync.create(getModel());
			getConnection().sendMessage(message);
		}
		
		if (getModel().getFlags().has(LocalBoard.GAME_END))
		{
			getConnection().sendMessage(new MsgGameEnd());
		}
	}

}
