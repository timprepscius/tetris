package tetris.net;

import tetris.model.Model;
import tetris.network.Connection;

public class ChatNet extends Net
{
	public ChatNet(Model model, Connection connection) {
		super(model, connection);
	}

	public void sendChat(String text) 
	{
	}

}
