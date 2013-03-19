///////////////////////////////////////////////////////////////////////////////
// Copyright (C) 2013 Timothy Prepscius
//
// This is a viewable source license.  No copying or modification or use is
// permitted.
//
// If you would like to participate in its development, or
// if you would like a different license, please contact the author.
///////////////////////////////////////////////////////////////////////////////

package tetris.net;

import tetris.model.Chat;
import tetris.model.Model;
import tetris.network.Connection;
import tetris.network.Message;
import tetris.network.message.MsgChat;

public class ChatNet extends Net
{
	public ChatNet(Model model, Connection connection) 
	{
		super(model, connection);
		connection.addReceiver(this);
	}

	public void sendChat(String text) 
	{
		getConnection().sendMessage(new MsgChat(text));
	}
	
	@Override
	public Chat getModel()
	{
		return (Chat)model;
	}
	
	@Override
	public void onMessage (Message message)
	{
		switch (message.getType())
		{
		case CHAT:
			getModel().receiveChat(((MsgChat)message).getText());
		break;
		default:
		break;
		}
	}

}
