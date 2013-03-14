package tetris.imp.gwt;

import tetris.model.Model;
import tetris.view.ChatView;

public class ChatViewGwt extends ChatView
{
	static ChatViewGwt instance;
	
	public ChatViewGwt(Model model) {
		super(model);
		
		instance = this;
	}

	public static ChatViewGwt getInstance()
	{
		return instance;
	}
}
