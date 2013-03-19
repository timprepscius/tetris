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

import java.util.HashMap;
import java.util.Map;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import tetris.server.Server;

import core.util.LogNull;
import core.util.LogOut;


/**
 * {@link IoHandler} implementation for NetCat client.  This class extended
 * {@link IoHandlerAdapter} for convenience.
 *
 * @author <a href="http://mina.apache.org">Apache MINA Project</a>
 */
public class ServerHandler extends IoHandlerAdapter 
{
	LogNull log = new LogNull(ServerHandler.class);
	
	static final int TIMEOUT_SECONDS = ConstantsMina.TIMEOUT;
	Map<IoSession, ConnectionMina> sessions = new HashMap<IoSession, ConnectionMina>();
	Server server;
	
	public ServerHandler (Server server) throws Exception
	{
		this.server = server;
	}
	
    @Override
    public void exceptionCaught(IoSession session, Throwable cause) 
    {
    	log.debug("exceptionCaught", session, cause);
        session.close(true);
    }

    @Override
    public void sessionOpened(IoSession session) 
    {
    	log.debug("sessionOpened", session);
        
        // Set reader idle time to 10 seconds.
        // sessionIdle(...) method will be invoked when no data is read
        // for 10 seconds.
        session.getConfig().setIdleTime(IdleStatus.READER_IDLE, TIMEOUT_SECONDS);
        
        ConnectionMina connection = new ConnectionMina(session);
        sessions.put(session, connection);
        server.onNewConnection(connection);
    }

    @Override
    public void sessionClosed(IoSession session)
    {
        log.debug("sessionClosed", session, "Total", session.getReadBytes(), "byte(s)");
        ConnectionMina connection = sessions.get(session);
        connection.onClose();
        sessions.remove(session);
    }

    @Override
    public void sessionIdle(IoSession session, IdleStatus status) {
        // Close the connection if reader is idle.
        if (status == IdleStatus.READER_IDLE) {
            session.close(true);
        }
    }

    @Override
    public void messageReceived(IoSession session, Object message) {
        log.debug("messageReceived");
        
        try
        {
            String str = message.toString();
            ConnectionMina connection = sessions.get(session);
            connection.onMessage(str);
        }
        catch (Exception e)
        {
        	log.debug ("messageReceived caught", e);
        	e.printStackTrace();
        	session.close(true);
        } 
    }
}