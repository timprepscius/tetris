///////////////////////////////////////////////////////////////////////////////
// Copyright (C) 2013 Timothy Prepscius
//
// This is a viewable source license.  No copying or modification or use is
// permitted.
//
// If you would like to participate in its development, or
// if you would like a different license, please contact the author.
///////////////////////////////////////////////////////////////////////////////

package tetris.web;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import org.apache.catalina.websocket.StreamInbound;
import org.apache.catalina.websocket.WebSocketServlet;

import tetris.server.Server;

/**
 * Servlet implementation class WsServer
 */
@WebServlet("/WsServer")
public class WsServer extends WebSocketServlet 
{
	private static final long serialVersionUID = 1L;
       
	Server server;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WsServer() {
        super();
        
        server = new Server();
        server.setupOneGame();
    }

    
	@Override
	protected StreamInbound createWebSocketInbound(String arg, HttpServletRequest request)
	{
		ConnectionWs connection = new ConnectionWs(server);
		server.onNewConnection(connection);
		return connection;
	}
}
