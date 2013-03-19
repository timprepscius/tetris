package tetris.network.message;

import java.util.ArrayList;
import java.util.List;

import core.io.In;
import core.io.Out;
import tetris.model.GameInfo;
import tetris.network.Message;
import tetris.network.MessageType;

public class MsgListGamesResult extends Message
{
	List<GameInfo> infos = new ArrayList<GameInfo>();
	
	public MsgListGamesResult ()
	{
		super(MessageType.LIST_GAME_RESULT);
	}
	
	public void addGame(GameInfo info)
	{
		infos.add(info);
	}

	@Override
	public void serialize (Out out)
	{
		out.writeInteger(infos.size());

		for (GameInfo info : infos)
		{
			info.serialize(out);
		}
		
	}
	
	@Override
	public void deserialize (In in)
	{
		int numInfos = in.readInteger();
		for (int i=0; i<numInfos; ++i)
		{
			GameInfo info = new GameInfo();
			info.deserialize(in);
			infos.add(info);
		}
	}

	public List<GameInfo> getInfos()
	{
		return infos;
	}
}
