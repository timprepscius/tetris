package tetris.view;

import tetris.model.Chat;
import tetris.model.Model;

public class ChatView extends View
{
	public ChatView(Model model) 
	{
		super(model);
	}
	
	@Override
	public Chat getModel ()
	{
		return (Chat)model;
	}
	
	public void onText (String text)
	{
		getModel().sendChat(text);
	}
}
