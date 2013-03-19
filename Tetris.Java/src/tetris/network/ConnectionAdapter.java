///////////////////////////////////////////////////////////////////////////////
// Copyright (C) 2013 Timothy Prepscius
//
// This is a viewable source license.  No copying or modification or use is
// permitted.
//
// If you would like to participate in its development, or
// if you would like a different license, please contact the author.
///////////////////////////////////////////////////////////////////////////////

package tetris.network;

import java.util.ArrayList;
import java.util.List;

public class ConnectionAdapter implements Connection, MessageReceiver
{
	List<MessageReceiver> receivers = new ArrayList<MessageReceiver>();
	Connection connection;
	
	public ConnectionAdapter(Connection connection)
	{
		this.connection = connection;
		if (connection != null)
			connection.addReceiver(this);
	}
	
	@Override
	public void onMessage(Connection connection, Message message)
	{
		MessageReceiver[] safe = receivers.toArray(new MessageReceiver[0]);

		for (MessageReceiver receiver : safe)
			receiver.onMessage(this, message);
	}

	@Override
	public void onClose(Connection connection)
	{
		MessageReceiver[] safe = receivers.toArray(new MessageReceiver[0]);

		for (MessageReceiver receiver : safe)
			receiver.onClose(this);
	}

	@Override
	public void close()
	{
		if (connection != null)
			connection.removeReceiver(this);
	}

	@Override
	public void addReceiver(MessageReceiver receiver)
	{
		this.receivers.add(receiver);
	}

	@Override
	public void removeReceiver (MessageReceiver receiver)
	{
		this.receivers.remove(receiver);
	}
	
	@Override
	public void sendMessage(Message message)
	{
		if (connection != null)
			connection.sendMessage(message);
	}
}
