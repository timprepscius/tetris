package tetris.imp.gwt;

import tetris.network.Connection;
import tetris.network.ConnectionFactory;

public class ConnectionFactoryGwt implements ConnectionFactory
{

	@Override
	public Connection newConnection(String address, int port) {
		return new ConnectionGwt("ws://" + address + ":" + port + "/Tetris/WsServer");
	}

}
