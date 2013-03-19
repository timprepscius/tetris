package tetris.imp.awt;

import java.awt.Container;

import javax.swing.JFrame;

import tetris.imp.mina.ConnectionFactoryMina;
import tetris.imp.mina.ConstantsMina;
import tetris.imp.view.ViewPlatform;
import tetris.model.Tetris;
import tetris.net.NetFactory;
import tetris.network.ConnectionFactory;
import tetris.model.ID;
import tetris.view.ViewFactory;

public class TetrisDriverAwt
{
	public static void main(String[] args) 
	{
		JFrame frame = new JFrame();
		frame.setFocusable(true);
		frame.setTitle("Tetris");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1000,400);
		ViewPlatform.frame = frame;

		Container pane = frame.getContentPane();

		ConnectionFactory connectionFactory = new ConnectionFactoryMina();
		NetFactory.setInstance(new NetFactory());
		ViewFactory.setInstance(new ViewFactoryAwt());
		
		String userToken = ID.random().toString();
		Tetris tetris = new Tetris(userToken);
		tetris.setView(ViewFactory.getInstance().instantiateFor(pane, tetris));
		tetris.setNet(NetFactory.getInstance().instantiateFor(
			tetris, 
			connectionFactory.newConnection(ConstantsMina.HOST, ConstantsMina.PORT))
		);
		
		tetris.start();
		
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}
