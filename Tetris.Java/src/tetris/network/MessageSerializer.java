package tetris.network;

import tetris.network.message.MsgFactory;
import core.util.Base16;
import core.util.LogOut;
import core.util.Strings;

public class MessageSerializer
{
	static LogOut log = new LogOut(MessageSerializer.class);
	
	public Message deserialize (byte[] bytes)
	{
		String s = Strings.toString(bytes);
		s = s.substring(0, s.indexOf('!'));
		String[] p = s.split(",");
		
		MessageType type = MessageType.values()[Integer.parseInt(p[0].trim())];
		Message message = MsgFactory.instantiate(type);
		
		message.setConsoleID(ID.fromByteArray(Base16.decode(p[1].trim())));
		message.setGameID(ID.fromByteArray(Base16.decode(p[2].trim())));
		message.setPlayerID(ID.fromByteArray(Base16.decode(p[3].trim())));
		message.deserialize(Base16.decode(p[4].trim()));
		
		log.debug("deserialized:", message);
		
		return message;
	}

	public byte[] serialize (Message message)
	{
		log.debug("serializing:", message);
		
		byte[] bytes = message.serialize();
		
		String s = 
			message.type + ", " + 
			((message.getConsoleID()!=null) ? Base16.encode(message.getConsoleID().toByteArray()) : "") + ", " +
			((message.getGameID()!=null) ? Base16.encode(message.getGameID().toByteArray()) : "") + ", " +
			((message.getPlayerID()!=null) ? Base16.encode(message.getPlayerID().toByteArray()) : "") + ", " +
			((bytes != null) ? Base16.encode(bytes) : "") +
			"!";
		
		return Strings.toBytes(s);
	}
}
