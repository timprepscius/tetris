///////////////////////////////////////////////////////////////////////////////
// Copyright (C) 2013 Timothy Prepscius
//
// This is a viewable source license.  No copying or modification or use is
// permitted.
//
// If you would like to participate in its development, or
// if you would like a different license, please contact the author.
///////////////////////////////////////////////////////////////////////////////

package tetris.imp.mina;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import tetris.server.Server;

public class ServerDriverMina
{
	public static void main (String[] args) throws Exception
	{
		// Create TCP/IP connector.
		NioSocketAcceptor acceptor = new NioSocketAcceptor();

		DefaultIoFilterChainBuilder chain = acceptor.getFilterChain();

		// Start communication.
//		acceptor.getFilterChain().addLast("logger", new LoggingFilter());
		TextLineCodecFactory textLineCodec = new TextLineCodecFactory(Charset.forName("UTF-8"));
		textLineCodec.setDecoderMaxLineLength(50 * 1000);
		textLineCodec.setEncoderMaxLineLength(50 * 1000);
		acceptor.getFilterChain().addLast(
			"codec",
			new ProtocolCodecFilter(textLineCodec)
		);

		Server server = new Server();
		server.setupOneGame();
		
		acceptor.setHandler(new ServerHandler(server));
		acceptor.bind(new InetSocketAddress(ConstantsMina.PORT));

		System.out.println("Listening on port " + ConstantsMina.PORT);
		
	}
}
