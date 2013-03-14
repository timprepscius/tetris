package tetris.model;

import java.util.ArrayList;
import java.util.List;

public class GameList extends Model
{
	public static int DIRTY = 0x01;

	List<GameInfo> games = new ArrayList<GameInfo>();

    public void setGames (List<GameInfo> games)
    {
    	this.games = games;
    	
    	getFlags().mark(DIRTY);
    }
    
    public Tetris getParent ()
    {
    	return (Tetris)parent;
    }
    
    public List<GameInfo> getGames ()
    {
    	return games;
    }

    public void joinGame (GameInfo info)
    {
    	getParent().joinGame(info);
    }

}
