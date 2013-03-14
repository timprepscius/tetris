package tetris.network.message;

import core.util.Strings;
import tetris.network.Message;
import tetris.network.MessageType;

public class MsgGameCountDown extends Message
{
	int countdown;
	
	public MsgGameCountDown ()
	{
		super(MessageType.GAME_COUNTDOWN);
	}
	
	public MsgGameCountDown (int countdown)
	{
		super(MessageType.GAME_COUNTDOWN);
		this.countdown = countdown;
	}
	
	public byte[] serialize ()
	{
		return ("" + countdown).getBytes();
	}
	
	public void deserialize (byte[] bytes)
	{
		countdown = Integer.parseInt(Strings.toString(bytes));
	}
	
	public int getCountDown ()
	{
		return countdown;
	}
}
