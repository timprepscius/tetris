package tetris.network.message;

import tetris.network.Message;
import tetris.network.MessageType;

public class MsgCurrentPiece extends MsgGeneric
{
	public MsgCurrentPiece() {
		super(MessageType.CURRENT_PIECE);
	}
	
	public MsgCurrentPiece(byte[] bytes)
	{
		super(MessageType.CURRENT_PIECE, bytes);
	}

}
