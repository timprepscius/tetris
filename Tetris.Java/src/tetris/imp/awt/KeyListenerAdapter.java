///////////////////////////////////////////////////////////////////////////////
// Copyright (C) 2013 Timothy Prepscius
//
// This is a viewable source license.  No copying or modification or use is
// permitted.
//
// If you would like to participate in its development, or
// if you would like a different license, please contact the author.
///////////////////////////////////////////////////////////////////////////////

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
