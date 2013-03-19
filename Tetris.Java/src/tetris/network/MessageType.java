///////////////////////////////////////////////////////////////////////////////
// Copyright (C) 2013 Timothy Prepscius
//
// This is a viewable source license.  No copying or modification or use is
// permitted.
//
// If you would like to participate in its development, or
// if you would like a different license, please contact the author.
///////////////////////////////////////////////////////////////////////////////

package tetris.network;

public enum MessageType
{
	INITIALIZE,
	LIST_GAME, ENTER_GAME, LEAVE_GAME,
	BOARD, LINES, ITEM, CURRENT_PIECE, LIST_GAME_RESULT, PLAYER_INFO, INITIALIZE_REQUEST, 
	GAME_COUNTDOWN, GAME_BEGIN, GAME_END, PING, GAME_RANKING, CHAT;
}
