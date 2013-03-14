package tetris.imp.mina;

import tetris.network.Connection;
import tetris.network.ConnectionFactory;

public class ConnectionFactoryMina implements ConnectionFactory
{

	@Override
	public Connection newConnection(String address, int port)
	{
		return new ConnectionMina(address, port);
	}
}
