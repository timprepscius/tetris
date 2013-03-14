package tetris.model;

import tetris.network.ID;
import tetris.view.TetrisView;

public class Tetris extends ModelSet
{
	String userToken;
	PlayerInfo playerInfo;
	GameList gameList;
	Game game;
    
    public Tetris(String userToken) 
    {
    	this.userToken = userToken;
    }
    
    public TetrisView getView ()
    {
    	return (TetrisView)view;
    }
    
    public void onStart ()
    {
    	
    }
    
    public Game getGame ()
    {
    	return game;
    }
    
    public void onJoinGame ()
    {
    	getView().onJoinGame();
    }
    
    public void onLeaveGame ()
    {
    	getView().onLeaveGame();
    }
    
    public void joinGame (GameInfo info)
    {
    	game = new Game(info);
    	addModel (game);

    	game.start();
    	onJoinGame();
    }
    
    public void leaveGame (Game game)
    {
    	game.stop();
    	removeModel (game);
    	onLeaveGame();
    }

	public void startGameList()
	{
		gameList = new GameList();
		addModel(gameList);

		gameList.start();
	}
	
	public void stopGameList ()
	{
		gameList.stop();
		removeModel(gameList);
	}

	public void setPlayerInfo(PlayerInfo playerInfo)
	{
		this.playerInfo = playerInfo;
	}

	public PlayerInfo getPlayerInfo()
	{
		return playerInfo;
	}
	
	public String getUserToken ()
	{
		return userToken;
	}
}