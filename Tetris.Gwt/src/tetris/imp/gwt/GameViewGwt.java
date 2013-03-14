package tetris.imp.gwt;

import tetris.model.Model;
import tetris.view.GameView;

public class GameViewGwt extends GameView
{
	boolean countdownVisible = false;
	
	public GameViewGwt(Model model) 
	{
		super(model);
	}

	public void onDraw ()
	{
		countdownVisible = updateCountdown(Bridge.countdown, countdownVisible, getModel().getCountDown());
	}
	
	public native boolean updateCountdown (Object countdown, boolean visible, int value) /*-{
		if (visible != (value > 0))
		{
			if (visible)
				countdown.hide();
			else
				countdown.show();
				
			visible = !visible;
		}
			
		countdown.find('.value').text('' + value);
		
		return visible;
	}-*/;
}
