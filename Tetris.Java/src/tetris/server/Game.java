///////////////////////////////////////////////////////////////////////////////
// Copyright (C) 2013 Timothy Prepscius
//
// This is a viewable source license.  No copying or modification or use is
// permitted.
//
// If you would like to participate in its development, or
// if you would like a different license, please contact the author.
///////////////////////////////////////////////////////////////////////////////

package tetris.server;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import core.util.Strings;
import core.util.Ticker;

import tetris.model.Chat;
import tetris.model.GameInfo;
import tetris.network.Message;
import tetris.network.message.MsgChat;
import tetris.network.message.MsgEnterGame;
import tetris.network.message.MsgGameBegin;
import tetris.network.message.MsgGameCountDown;
import tetris.network.message.MsgGameRanking;
import tetris.network.message.MsgLeaveGame;
import tetris.TetrisException;

public class Game
{
	GameInfo info;
	List<Player> players = new ArrayList<Player>();
	Chat chat = new Chat();

	Ticker countDownTicker = new Ticker(1000);	
	int countDown = -1;
	
	public Game (GameInfo info)
	{
		this.info = info;
	}
	
	public GameInfo getInfo()
	{
		return info;
	}
	
	public void broadcastMessage(Player origin, Message message)
	{
		for (Player player : players)
		{
			if (player == origin)
				continue;
			
			player.getConnection().sendMessage(message);
		}
	}
	
	public Message postProcessMessage (Message message)
	{
		message.setGameID(info.getID());
		return message;
	}
	
	public void handleEnter (Player player)
	{
		players.add(player);
		player.currentGame = info.getID();
		info.getPlayers().add(player.getInfo());

		for (Player other : players)
		{
			if (other != player)
			{
				player.getConnection().sendMessage(
					postProcessMessage(new MsgEnterGame(other.getInfo()))
				);
			}
		}
		
		player.getConnection().sendMessage(
			postProcessMessage(
				new MsgChat(Strings.concat(chat.getChatter(), "\n"))
			)
		);
	}
	
	public void onChat (Player player, Message message)
	{
		chat.receiveChat(((MsgChat)message).getText());
	}
	
	public void handleMessage (Player player, Message message)
	{
		switch (message.getType())
		{
		case LIST_GAME:
			throw new TetrisException("Invalid message");
		case ENTER_GAME:
			handleEnter(player);
			break;
		case LEAVE_GAME:
			players.remove(player);
			break;
		case GAME_END:
			player.endTime = new Date();
			break;
		case PING:
			onTick();
			break;
		case CHAT:
			onChat(player, message);
			break;
		default:
			// nothing to do
			break;
		}
		
		broadcastMessage(player, message);
	}

	void onTick() 
	{
		boolean gameStillGoing = false;
		for (Player player : players)
		{
			if (player.beginTime != null && player.endTime == null)
				gameStillGoing = true;
		}
		
		if (!gameStillGoing)
		{
			if (countDown < 0)
			{
				broadcastMessage(null, postProcessMessage(new MsgGameRanking()));
				countDownTicker.start(true);
				countDown = 10;
			}
			
			if (countDown > 0)
			{
				if (countDownTicker.tick())
				{
					broadcastMessage(null, postProcessMessage(new MsgGameCountDown(countDown)));
					countDown--;
				}
			}
			
			if (countDown == 0)
			{
				for (Player player : players)
				{
					player.beginTime = new Date();
					player.endTime = null;
				}
				
				broadcastMessage(null, postProcessMessage(new MsgGameBegin()));
				countDown--;
			}
		}
	}

	public void onCloseConnection(Player player)
	{
		Message message = new MsgLeaveGame();
		message.setGameID(info.getID());
		message.setPlayerID(player.id);
		handleMessage(player, message);
	}
}
