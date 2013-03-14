package tetris.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tetris.network.ID;

public class Game extends ModelSet
{
	public static int
		GAME_BEGIN = 0x01,
		GAME_COUNTDOWN = 0x02;

	GameInfo info;
	Chat chat;
	LocalBoard localBoard;
	Map<ID, RemoteBoard> boards = new HashMap<ID, RemoteBoard>();
	List<RemoteBoard> orderedBoards = new ArrayList<RemoteBoard>();
	int countdown = 0;
	
	public Game (GameInfo info)
	{
		this.info = info;
		setID(info.id);
	}
	
	public Tetris getParent ()
	{
		return (Tetris)parent;
	}
	
	public void onStart ()
	{
		chat = new Chat();
		addModel (chat);
		chat.start();
		
		localBoard = new LocalBoard(getParent().getPlayerInfo(), new Bag(1));
		addModel(localBoard);
		localBoard.start();
	}
	
	public void onStop ()
	{
		localBoard = null;
		removeModel(localBoard);
	}
	
	public Board getLocalBoard()
	{
		return localBoard;
	}

	public List<RemoteBoard> getRemoteBoards()
	{
		return orderedBoards;
	}
	
	public void handleEnter (PlayerInfo player)
	{
		RemoteBoard board = new RemoteBoard(player);
		boards.put(player.id, board);
		addModel(board);
		orderedBoards.add(board);

		board.start();
	}
	
	public void handleLeave (ID playerID)
	{
		Board board = boards.get(playerID);
		board.stop();

		orderedBoards.remove(board);
		boards.remove(playerID);
		removeModel(board);
	}

	public void handleGameCountdown(int countdown) {
		this.countdown = countdown;	
		
		getFlags().mark(GAME_COUNTDOWN);
	}

	public void handleGameBegin() {
		countdown = 0;
		localBoard.onGameBegin();

		getFlags().mark(GAME_BEGIN);
	}	
	
	public int getCountDown ()
	{
		return countdown;
	}
}
