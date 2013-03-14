package tetris.imp.gwt;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.JavaScriptObject;

import core.util.Strings;
import tetris.network.ConnectionAdapter;
import tetris.network.Message;
import tetris.network.MessageSerializer;

public class ConnectionGwt extends ConnectionAdapter
{
	boolean opened = false;
	JavaScriptObject connection;
	MessageSerializer serializer = new MessageSerializer();
	List<String> queue = new ArrayList<String>();

	public ConnectionGwt() 
	{
		super(null);
	}

	public ConnectionGwt(String url) 
	{
		super(null);
		connection = doConnect(this, url);
	}
	
	public void onOpen ()
	{
		opened = true;
		List<String> safe = queue;
		queue = null;
		
		for (String string : safe)
			sendMessage(string);
	}

	@Override
	public void close() 
	{
		doClose(connection);
	}
	
	@Override
	public void sendMessage(Message message) 
	{
		sendMessage(Strings.toString(serializer.serialize(message)));
	}
		
	public void onClose ()
	{
		super.onClose(null);
	}

	public void onMessage (String message)
	{
		super.onMessage(null, serializer.deserialize(Strings.toBytes(message)));
	}
	
	public void sendMessage (String message)
	{
		if (opened)
			doSendMessage(connection, message);
		else
			queue.add(message);
	}
	
	//---------------------------------------------------------
	
	public static void onClose (Object self)
	{
		((ConnectionGwt)self).onClose();
	}
	
	public static void onMessage (Object self, String message)
	{
		((ConnectionGwt)self).onMessage(message);
	}
	
	public static void onOpen (Object self)
	{
		((ConnectionGwt)self).onOpen();
	}

	public native JavaScriptObject doConnect (ConnectionGwt _self, String url) /*-{
		var self = _self;
		var connection = new WebSocket(url);
		connection.onclose = function() { $entry(@tetris.imp.gwt.ConnectionGwt::onClose(Ljava/lang/Object;))(self); }
		connection.onmessage = function(m) { $entry(@tetris.imp.gwt.ConnectionGwt::onMessage(Ljava/lang/Object;Ljava/lang/String;))(self, m.data); }
		connection.onopen = function() { $entry(@tetris.imp.gwt.ConnectionGwt::onOpen(Ljava/lang/Object;))(self); }
		return connection;
	}-*/;

	public native void doClose(JavaScriptObject connection) /*-{
		connection.close();
	}-*/;
	
	public native void doSendMessage(JavaScriptObject connection, String message) /*-{
		connection.send(message);
	}-*/;

}
