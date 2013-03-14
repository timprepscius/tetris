package tetris.network.message;

import java.util.ArrayList;
import java.util.List;

import core.util.Base16;
import core.util.Strings;

import tetris.model.GameInfo;
import tetris.network.ID;
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

	public byte[] serialize ()
	{
		ArrayList<String> s = new ArrayList<String>();
		for (GameInfo info : infos)
		{
			s.add(Base16.encode(info.id.toByteArray()) + ", " + info.name + ", " + info.numPlayers);
		}
		
		return Strings.toBytes(Strings.concat(s, "|"));
	}
	
	public void deserialize (byte[] bytes)
	{
		String s = Strings.toString(bytes);
		if (s.isEmpty())
			return ;
		
		String parts[] = s.split("\\|");
		
		for (String part : parts)
		{
			String sub[] = part.split(",");
			
			GameInfo info = new GameInfo();
			info.id = ID.fromByteArray(Base16.decode(sub[0].trim()));
			info.name = sub[1].trim();
			info.numPlayers = Integer.parseInt(sub[2].trim());
			infos.add(info);
		}
	}

	public List<GameInfo> getInfos()
	{
		return infos;
	}
}
