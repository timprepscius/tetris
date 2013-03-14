package tetris.operation;

import tetris.model.Board;
import tetris.model.Shape;

import tetris.network.Message;
import tetris.network.MessageType;
import tetris.network.message.MsgGeneric;
import tetris.TetrisException;

public class BoardSync extends Operation
{
	public static Message create (Board board)
	{
		byte[] bytes = new byte[board.height * board.width];
		
		int k = 0;
		for (int i=0; i<board.width; ++i)
		{
			for (int j=0; j<board.height; ++j)
			{
				bytes[k++] = (byte)(board.shapeAt(i, j).ordinal());
			}
		}
		
		return new MsgGeneric(MessageType.BOARD, bytes);
	}
	
	public static void apply (MsgGeneric message, Board board)
	{
		if (board.height * board.width != message.getBytes().length)
			throw new TetrisException("Message does not match board size");
		
		Shape[] indexed = Shape.values();
		
		int k = 0;
		for (int i=0; i<board.width; ++i)
		{
			for (int j=0; j<board.height; ++j)
			{
				board.setShapeAt(i, j, indexed[message.getBytes()[k++]]);
			}
		}
		
		board.getFlags().mark(Board.LINES_CLEARED);
	}
}
