package tetris.network.message;

import tetris.network.MessageType;

public class MsgBoard extends MsgGeneric {

	public MsgBoard() {
		super(MessageType.BOARD);
	}
	
	public MsgBoard(byte[] bytes)
	{
		super(MessageType.BOARD, bytes);
	}

}
