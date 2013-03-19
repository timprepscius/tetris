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

import tetris.model.Model;
import tetris.network.Connection;
import tetris.network.Message;
import tetris.network.MessageReceiver;

public class Net implements MessageReceiver
{
	Model model;
	Connection connection;

	public Net (Model model, Connection connection)
	{
		this.model = model;
		this.connection = connection;
	}
	
	public Model getModel ()
	{
		return model;
	}
	
	public Connection getConnection ()
	{
		return connection;
	}
	
	public final void start ()
	{
		onStart();
	}
	
	public void onStart()
	{
		
	}
	
	public final void stop ()
	{
		onStop();
	}
	
	public void onStop ()
	{
		
	}
	
	public void onTick ()
	{
		
	}
	
	public void onMessage(Message message)
	{
		
	}
	
	@Override
	public final void onMessage(Connection connection, Message message)
	{
		onMessage(message);
	}

	public void onClose ()
	{
		
	}
	
	@Override
	public final void onClose(Connection connection)
	{
		onClose();
	}
}
