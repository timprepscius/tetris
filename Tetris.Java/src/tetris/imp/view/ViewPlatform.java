package tetris.imp.view;

import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import tetris.imp.awt.KeyListenerAdapter;
import tetris.view.KeyListener;

public abstract class ViewPlatform extends JPanel
{
	public static JFrame frame;
	
	public ViewPlatform ()
	{
	}
	
	public Object getGraphicsContext ()
	{
		return getGraphics();
	}
	
	public void requestDraw ()
	{
		repaint();
	}
	
	public void paint (Graphics g)
	{
        super.paint(g);

		draw();
	}
	
	public void draw ()
	{
		
	}
	
	public void setKeyListener (KeyListener keyListener)
	{
	    frame.addKeyListener(new KeyListenerAdapter(this, keyListener));
	}
	
	public abstract boolean keyPress (int keyCode);
	public abstract boolean onKeyPress (int keyCode);
	public abstract void onText(String text);

}
