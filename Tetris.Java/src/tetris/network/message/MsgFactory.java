package tetris.network.message;

import tetris.network.Message;
import tetris.network.MessageType;

public class MsgFactory 
{
	public static Message instantiate (MessageType type)
	{
		switch (type)
		{
		case INITIALIZE_REQUEST:
			return new MsgInitializeRequest();
		case INITIALIZE:
			return new MsgInitialize();
		case ENTER_GAME:
			return new MsgEnterGame();
		case LEAVE_GAME:
			return new MsgLeaveGame();
		case LIST_GAME:
			return  new MsgListGames();
		case LIST_GAME_RESULT:
			return new MsgListGamesResult();
		case BOARD:
			return new MsgBoard();
		case CURRENT_PIECE:
			return new MsgCurrentPiece();
		case GAME_COUNTDOWN:
			return new MsgGameCountDown();
		case GAME_BEGIN:
			return new MsgGameBegin();
		case GAME_END:
			return new MsgGameEnd();
		case GAME_RANKING:
			return new MsgGameRanking();
		case ITEM:
			break;
		case LINES:
			break;
		case PING:
			return new MsgPing();
		case PLAYER_INFO:
			return new MsgPlayerInfo();
		case CHAT:
			return new MsgChat();
		}
		
		return new MsgGeneric(type);
	}
}
