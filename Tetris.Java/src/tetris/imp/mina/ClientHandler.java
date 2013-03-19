///////////////////////////////////////////////////////////////////////////////
// Copyright (C) 2013 Timothy Prepscius
//
// This is a viewable source license.  No copying or modification or use is
// permitted.
//
// If you would like to participate in its development, or
// if you would like a different license, please contact the author.
///////////////////////////////////////////////////////////////////////////////

package tetris.imp.mina;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

class ClientHandler extends IoHandlerAdapter 
{
	ConnectionMina connectionMina;
	
    public ClientHandler(ConnectionMina connectionMina)
	{
    	this.connectionMina = connectionMina;
	}

	@Override
    public void sessionOpened(IoSession session) {

    }

    @Override
    public void messageReceived(IoSession session, Object message) {
    	connectionMina.onMessage((String)message);
    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause) {
        cause.printStackTrace();
    }
}