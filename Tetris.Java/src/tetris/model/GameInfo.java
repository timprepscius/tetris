package tetris.model;

import java.util.ArrayList;
import java.util.List;

import core.io.IO;
import core.io.In;
import core.io.Out;

import tetris.network.ID;

public class GameInfo implements IO
{
	public ID id;
	public String name;
	
	public int maxPlayers;
	public boolean watchable;
	public List<PlayerInfo> players = new ArrayList<PlayerInfo>();
	
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
