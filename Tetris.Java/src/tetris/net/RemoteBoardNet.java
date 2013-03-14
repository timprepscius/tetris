package tetris.net;

import tetris.model.RemoteBoard;
import tetris.network.Message;
import tetris.network.PlayerConnection;
import tetris.network.message.MsgGeneric;
import tetris.operation.BoardSync;
import tetris.operation.CurrentPiece;

public class RemoteBoardNet extends Net
{
	RemoteBoardNet (RemoteBoard board, PlayerConnection playerConnection)
	{
		super(board, playerConnection);
		playerConnection.addReceiver(this);
	}
	
	public RemoteBoard getModel ()
	{
		return (RemoteBoard)model;
	}
	
	public void onMessage (Message message)
	{
		switch (message.getType())
		{
		case BOARD:
			BoardSync.apply((MsgGeneric)message, getModel());
		break;
			
		case CURRENT_PIECE:
			CurrentPiece.apply((MsgGeneric)message, getModel());
		break;
		
		case GAME_END:
			getModel().onGameEnd();
		break;
		
		default:
		break;
		}
	}
	
}
