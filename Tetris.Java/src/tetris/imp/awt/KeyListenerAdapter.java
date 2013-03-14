package tetris.imp.awt;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import tetris.imp.view.ViewPlatform;
import tetris.view.KeyListener;
import tetris.view.View;

public class KeyListenerAdapter extends KeyAdapter
{
	KeyListener listener;
	ViewPlatform view;
	
	public KeyListenerAdapter (ViewPlatform view, KeyListener listener)
	{
		this.listener = listener;
		this.view = view;
	}
	
	public void keyPressed(KeyEvent event)
	{
		listener.keyPress(event.getKeyCode());
		view.keyPress(event.getKeyCode());
	}
}
