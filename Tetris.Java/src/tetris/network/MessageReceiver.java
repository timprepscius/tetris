package tetris.network;

public interface MessageReceiver
{
	public void onMessage(Connection connection, Message message);
	public void onClose(Connection connection);
}
