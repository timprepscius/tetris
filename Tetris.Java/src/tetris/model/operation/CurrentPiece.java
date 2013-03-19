///////////////////////////////////////////////////////////////////////////////
// Copyright (C) 2013 Timothy Prepscius
//
// This is a viewable source license.  No copying or modification or use is
// permitted.
//
// If you would like to participate in its development, or
// if you would like a different license, please contact the author.
///////////////////////////////////////////////////////////////////////////////

package tetris.model.operation;

import tetris.model.Board;
import tetris.model.Piece;
import tetris.model.Shape;
import tetris.network.Message;
import tetris.network.MessageType;
import tetris.network.message.MsgGeneric;

public class CurrentPiece
{
	public static Message create (Board board)
	{
		byte[] bytes = new byte[4];
		Piece piece = board.getCurrentPiece();
		bytes[0] = (byte)piece.getShape().ordinal();
		bytes[1] = (byte)piece.getRotation();
		bytes[2] = (byte)piece.getX();
		bytes[3] = (byte)piece.getY();
		
		return new MsgGeneric(MessageType.CURRENT_PIECE, bytes);
	}
	
	public static void apply (MsgGeneric message, Board board)
	{
		Piece piece = board.getCurrentPiece();
		byte[] bytes = message.getBytes();
		piece.setShape(Shape.values()[bytes[0]]);
		piece.setRotation(bytes[1]);
		piece.setX(bytes[2]);
		piece.setY(bytes[3]);
		
		board.getFlags().mark(Board.PIECE_MOVED);
	}
}
