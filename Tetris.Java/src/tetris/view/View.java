package tetris.view;

import tetris.imp.view.ViewPlatform;
import tetris.model.KeyState;
import tetris.model.Model;

public class View extends ViewPlatform
{
	protected Model model;
	KeyListener keyListener;

	public View (Model model)
	{
		this.model = model;
	}
	
	public final void start ()
	{
		onStart();
	}
	
	public void onStart ()
	{
		
	}
	
	public void onTick()
	{
		testDraw();
	}
	
	public final void stop ()
	{
		onStop();
	}
	
	public void onStop ()
	{
		
	}

	public Model getModel ()
	{
		return model;
	}
	
	public void testDraw ()
	{
		requestDraw();
	}
	
	@Override
	public void draw ()
	{
		onDraw();
	}
	
	public void onDraw ()
	{
		
	}
	
	@Override
	public void requestDraw ()
	{
		super.requestDraw();
	}
	
	public void setKeyListener (KeyListener keyListener)
	{
		this.keyListener = keyListener;
	}
	
	public void onEvent ()
	{
		testDraw();
	}
	
	public boolean onKeyPress (int keyCode)
	{
		onEvent();
		return false;
	}
	
	public void onKeyRepeat (KeyState keyState)
	{
		onEvent();
	}
	
	@Override
	public final boolean keyPress (int keyCode)
	{
		boolean result = false;
		
		if (keyListener != null)
			if (keyListener.keyPress(keyCode))
				result = true;
		
		if (onKeyPress(keyCode))
			result = true;

		return result;
	}
	
	public final void keyRepeat (KeyState keyState)
	{
		if (keyListener != null)
			keyListener.keyRepeat(keyState);
		
		onKeyRepeat(keyState);
	}

	public void onText (String text)
	{
		
	}
	
	@Override
	public void requestFocus ()
	{
		super.requestFocus();
	}
}
