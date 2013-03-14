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