package tetris.web;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;

import org.apache.catalina.websocket.MessageInbound;

import tetris.TetrisException;
import tetris.network.Connection;
import tetris.network.Message;
import tetris.network.MessageReceiver;
import tetris.network.MessageSerializer;
import core.util.LogOut;
import core.util.Strings;

class ConnectionWs extends MessageInbound implements Connection
{
	static LogOut log = new LogOut (ConnectionWs.class);
	
	MessageReceiver receiver;
	MessageSerializer serializer = new MessageSerializer();
	
	ConnectionWs (MessageReceiver receiver)
	{
		this.receiver = receiver;
		log.debug(this, "constructing");
	}
	
	@Override
	protected void onBinaryMessage(ByteBuffer arg0) throws IOException
	{
		log.debug(this, "onBinaryMessage");
	}

	@Override
	protected void onTextMessage(CharBuffer chars) throws IOException
	{
		chars.flip();
		chars.compact();
		String string = new String(chars.array(), 0, chars.limit());
		chars.clear();

		/*
		// there is a bug in the apache websocket, it doesn't seem to be clearing the buffers or something
		int firstNewLine = string.indexOf('\n');
		if (firstNewLine != -1)
            string = string.substring(0, firstNewLine+1);	
		*/
		
		log.debug(this, "onTextMessage", string);
		Message message = serializer.deserialize(Strings.toBytes(string));
		receiver.onMessage(this, message);
	}

	public void onClose(int status)
	{
		super.onClose(status);
		receiver.onClose(this);
	}
	
	@Override
	public void close()
	{
		log.debug(this, "close");
		try
		{
			this.getWsOutbound().close(1000, null);
		}
		catch (IOException e)
		{
			throw new TetrisException(e);
		}
	}

	@Override
	public void addReceiver(MessageReceiver receiver)
	{
		log.debug(this, "addReceiver");
	}

	@Override
	public void removeReceiver(MessageReceiver receiver)
	{
		log.debug(this, "removeReceiver");
	}

	@Override
	public void sendMessage(Message message)
	{
		log.debug(this, "sendMessage");
		try
		{
			this.getWsOutbound().writeTextMessage(
				CharBuffer.wrap(Strings.toString(serializer.serialize(message)).toCharArray())
			);
		}
		catch (IOException e)
		{
			this.receiver.onClose(this);
		}
	}
	
}
