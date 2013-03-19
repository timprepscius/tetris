///////////////////////////////////////////////////////////////////////////////
// Copyright (C) 2013 Timothy Prepscius
//
// This is a viewable source license.  No copying or modification or use is
// permitted.
//
// If you would like to participate in its development, or
// if you would like a different license, please contact the author.
///////////////////////////////////////////////////////////////////////////////

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
