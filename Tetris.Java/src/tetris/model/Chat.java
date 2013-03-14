package tetris.model;

import java.util.ArrayList;

import tetris.net.ChatNet;
import java.util.List;

public class Chat extends Model
{
	static final int MAX_CHAT_SIZE = 50;
	
	List<String> chatter = new ArrayList<String>();
	
	@Override
	public ChatNet getNet()
	{
		return (ChatNet)net;
	}
	
	protected void addChat (String text)
	{
		chatter.add(text);
		prune();
	}
	
	public void receiveChat (String text)
	{
		addChat(text);
	}

	public void sendChat (String text)
	{
		addChat(text);
		getNet().sendChat(text);
	}
	
	public void prune ()
	{
		while (chatter.size() > MAX_CHAT_SIZE)
			chatter.remove(0);
	}
}
