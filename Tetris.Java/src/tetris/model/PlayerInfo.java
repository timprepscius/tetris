package tetris.model;

import org.timepedia.exporter.client.Exportable;
import org.timepedia.exporter.client.Export;

import core.io.IO;
import core.io.In;
import core.io.Out;

@Export
public class PlayerInfo implements IO, Exportable
{
	protected ID id;
	protected String name;
	protected int rank;
	
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

	public int getRank() 
	{
		return rank;
	}

	public void setRank(int rank) 
	{
		this.rank = rank;
	}
	
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
