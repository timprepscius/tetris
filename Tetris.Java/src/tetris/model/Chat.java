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

import tetris.net.ChatNet;
import java.util.List;

public class Chat extends Model
{
	public static final int DIRTY = 0x01;
	
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

		getFlags().mark(DIRTY);
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
	
	public List<String> getChatter ()
	{
		return chatter;
	}
}
