package tetris.network;

public interface ConnectionFactory
{
	public Connection newConnection (String address, int port);
}
