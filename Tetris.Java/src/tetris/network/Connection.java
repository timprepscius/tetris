package tetris.network;


public interface Connection
{
	public void close();
	public void addReceiver (MessageReceiver receiver);
	public void removeReceiver (MessageReceiver receiver);
	public void sendMessage (Message message);
}
