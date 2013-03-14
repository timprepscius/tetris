package tetris.net;

import tetris.model.Model;
import tetris.model.Tetris;
import tetris.network.Connection;
import tetris.network.ID;
import tetris.network.Message;
import tetris.network.MessageType;
import tetris.network.message.MsgInitialize;
import tetris.network.message.MsgInitializeRequest;

public class TetrisNet extends Net
{

	public TetrisNet(Model model, Connection connection)
	{
		super(model, connection);
		connection.addReceiver(this);
	}
	
	public Tetris getModel ()
	{
		return (Tetris)model;
	}
	
	@Override
	public void onMessage(Message message)
	{
		if (message.getType() == MessageType.INITIALIZE)
		{
			MsgInitialize msg = (MsgInitialize)message;
			getModel().setID(msg.getConsoleID());
			getModel().setPlayerInfo(msg.getPlayerInfo());
			getModel().startGameList();
		}
	}
	
	public void onStart()
	{
		getConnection().sendMessage(new MsgInitializeRequest(getModel().getUserToken()));
	}
}
