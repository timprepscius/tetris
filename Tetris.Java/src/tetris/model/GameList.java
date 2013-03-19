///////////////////////////////////////////////////////////////////////////////
// Copyright (C) 2013 Timothy Prepscius
//
// This is a viewable source license.  No copying or modification or use is
// permitted.
//
// If you would like to participate in its development, or
// if you would like a different license, please contact the author.
///////////////////////////////////////////////////////////////////////////////

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
