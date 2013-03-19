package tetris.model;

import org.timepedia.exporter.client.Exportable;
import org.timepedia.exporter.client.Export;

import java.util.ArrayList;
import java.util.List;

import core.io.IO;
import core.io.In;
import core.io.Out;

@Export
public class GameInfo implements IO, Exportable
{
	protected ID id;
	protected String name;	
	protected int maxPlayers;
	protected boolean watchable;
	protected List<PlayerInfo> players = new ArrayList<PlayerInfo>();
	
	public ID getID() 
	{
		return id;
	}

	public void setID(ID id) 
	{
		this.id = id;
	}

	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}


	public int getMaxPlayers() 
	{
		return maxPlayers;
	}

	public void setMaxPlayers(int maxPlayers) 
	{
		this.maxPlayers = maxPlayers;
	}

	public boolean isWatchable() 
	{
		return watchable;
	}

	public void setWatchable(boolean watchable) 
	{
		this.watchable = watchable;
	}

	public void setPlayers(List<PlayerInfo> players) 
	{
		this.players = players;
	}

	public List<PlayerInfo> getPlayers ()
	{
		return players;
	}
	
	@Override
	public void deserialize(In in) 
	{
		id = ID.fromByteArray(in.readBytes());
		name = in.readString();
		maxPlayers = in.readInteger();
		watchable = in.readBoolean();
		
		int numPlayers = in.readInteger();
		for (int i=0; i<numPlayers; i++)
		{
			PlayerInfo info = new PlayerInfo();
			info.deserialize(in);
		}
	}
	
	@Override
	public void serialize(Out out) 
	{
		out.writeBytes(id.toByteArray());
		out.writeString(name);
		out.writeInteger(maxPlayers);
		out.writeBoolean(watchable);
		
		out.writeInteger(players.size());
		for (PlayerInfo info : players)
		{
			info.serialize(out);
		}
	}
}
