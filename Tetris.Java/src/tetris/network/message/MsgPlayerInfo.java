package tetris.network.message;

import core.util.Base16;
import core.util.Strings;
import tetris.model.PlayerInfo;
import tetris.network.ID;
import tetris.network.Message;
import tetris.network.MessageType;

public class MsgPlayerInfo extends Message
{
	PlayerInfo playerInfo;
	
	protected MsgPlayerInfo(MessageType messageType)
	{
		super(messageType);
	}
	
	protected MsgPlayerInfo(MessageType messageType, PlayerInfo playerInfo)
	{
		super(messageType);
		this.playerInfo = playerInfo;
	}
	
	public MsgPlayerInfo (PlayerInfo playerInfo)
	{
		super(MessageType.PLAYER_INFO);
		this.playerInfo = playerInfo;
	}

	public MsgPlayerInfo()
	{
		super(MessageType.PLAYER_INFO);
	}
	
	public byte[] serialize ()
	{
		String s = Base16.encode(playerInfo.id.toByteArray()) + ", " + playerInfo.name;
		return Strings.toBytes(s);
	}
	
	public void deserialize (byte[] bytes)
	{
		String p[] = Strings.toString(bytes).split(",");
		playerInfo = new PlayerInfo();
		playerInfo.id = ID.fromByteArray(Base16.decode(p[0].trim()));
		playerInfo.name = p[1].trim();
	}
	
	public PlayerInfo getPlayerInfo ()
	{
		return playerInfo;
	}

}
