package tetris.imp.view;

import tetris.imp.gwt.Bridge;
import tetris.model.KeyState;
import com.google.gwt.core.client.JavaScriptObject;

public abstract class ViewPlatform 
{
	private JavaScriptObject js;
	
	public ViewPlatform ()
	{
	}
	
	public abstract void draw ();
	public abstract boolean keyPress (int keyCode);
	public abstract void keyRepeat(KeyState keyState);
	public abstract void onText(String string);
	
	public void requestDraw ()
	{
		draw();
	}
	
	public void requestFocus ()
	{
		Bridge.focus = this;
	}
	
	public JavaScriptObject getJS() 
	{
		return js;
	}

	public void setJS(JavaScriptObject js) 
	{
		this.js = js;
	}


}
