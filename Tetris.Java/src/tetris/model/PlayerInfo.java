package tetris.model;

import core.io.IO;
import core.io.In;
import core.io.Out;
import tetris.network.ID;

public class PlayerInfo implements IO
{
	public ID id;
	public String name;
	public int rank;
	@Override
	
	public void deserialize(In in) 
	{
		id = ID.fromByteArray(in.readBytes());
		name = in.readString();
		rank = in.readInteger();
	}
	
	@Override
	public void serialize(Out out) 
	{
		out.writeBytes(id.toByteArray());
		out.writeString(name);
		out.writeInteger(rank);
	}
}
