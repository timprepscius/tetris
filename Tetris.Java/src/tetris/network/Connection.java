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

public interface Connection
{
	public void close();
	public void addReceiver (MessageReceiver receiver);
	public void removeReceiver (MessageReceiver receiver);
	public void sendMessage (Message message);
}
