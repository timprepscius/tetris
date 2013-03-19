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

import tetris.model.ID;
import tetris.network.message.MsgFactory;
import core.io.In;
import core.io.Out;
import core.util.LogOut;

public class MessageSerializer
{
	static LogOut log = new LogOut(MessageSerializer.class);
	
	public Message deserialize (String string)
	{
		string = string.substring(0, string.indexOf("!"));
		In in = new In(string);
		
		MessageType type = MessageType.values()[in.readInteger()];
		Message message = MsgFactory.instantiate(type);
		
		message.setConsoleID(ID.fromByteArray(in.readBytes()));
		message.setGameID(ID.fromByteArray(in.readBytes()));
		message.setPlayerID(ID.fromByteArray(in.readBytes()));
		message.deserialize(in);
		
		log.debug("deserialized:", message);
		
		return message;
	}

	public String serialize (Message message)
	{
		log.debug("serializing:", message);
		
		Out out = new Out();
		out.writeInteger(message.type);
		out.writeBytes((message.getConsoleID()!=null) ? message.getConsoleID().toByteArray() : null);
		out.writeBytes((message.getGameID()!=null) ? message.getGameID().toByteArray() : null);
		out.writeBytes((message.getPlayerID()!=null) ? message.getPlayerID().toByteArray() : null);
		message.serialize(out);
		
		return out.toString() + "!";
	}
}
