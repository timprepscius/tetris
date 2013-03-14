package tetris.imp.mina;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.mina.core.RuntimeIoException;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;

import core.util.LogOut;
import core.util.Strings;

import tetris.network.Connection;
import tetris.network.Message;
import tetris.network.MessageReceiver;
import tetris.network.MessageSerializer;
import tetris.TetrisException;

public class ConnectionMina implements Connection
{
	static LogOut log = new LogOut(ConnectionMina.class);
	
	IoSession session;
	List<MessageReceiver> receivers = new ArrayList<MessageReceiver>();
	MessageSerializer serializer = new MessageSerializer();

	public ConnectionMina(IoSession session)
	{
		this.session = session;
	}
	
	public ConnectionMina(String address, int port)
	{
		NioSocketConnector connector = new NioSocketConnector();
	    connector.getFilterChain().addLast( "codec", 
	    	new ProtocolCodecFilter( new TextLineCodecFactory( Charset.forName( "UTF-8" )))
	    );
//	    connector.getFilterChain().addLast("logger", new LoggingFilter());
	    connector.setHandler(new ClientHandler(this));
        ConnectFuture future = connector.connect(new InetSocketAddress(address, port));
        future.awaitUninterruptibly();
        session = future.getSession();

        if (session == null) {
	        throw new TetrisException("Unable to get session");
	    }		
	}

	@Override
	public void close()
	{
		session.close(true);
	}

	@Override
	public void addReceiver(MessageReceiver receiver)
	{
		receivers.add(receiver);
	}

	@Override
	public void removeReceiver (MessageReceiver receiver)
	{
		receivers.remove(receiver);
	}
	
    public void send (byte[] bytes)
    {
		log.debug("S:" + Strings.toString(bytes));

		/*
		IoBuffer out = IoBuffer.allocate(bytes.length);
        out.setAutoExpand(true);
        out.put (bytes);
        out.flip();
        */
		
        session.write(Strings.toString(bytes) + "\n");
    }

	@Override
	public void sendMessage(Message message)
	{
		send(serializer.serialize(message));
	}

	public void onMessage(String s)
	{
		if (s.trim().isEmpty())
			return ;
		
		log.debug("R:" + s);
		
		Message message = serializer.deserialize(Strings.toBytes(s));
		MessageReceiver[] safe = receivers.toArray(new MessageReceiver[0]);
		for (MessageReceiver receiver : safe)
			receiver.onMessage(this, message);
	}
	
	public void onClose()
	{
		MessageReceiver[] safe = receivers.toArray(new MessageReceiver[0]);
		for (MessageReceiver receiver : safe)
			receiver.onClose(this);
		
	}
}
