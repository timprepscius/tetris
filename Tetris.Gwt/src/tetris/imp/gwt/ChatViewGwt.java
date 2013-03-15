package tetris.imp.gwt;

import core.util.Strings;
import tetris.model.Chat;
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
	
	public void onDraw ()
	{
		if (getModel().getFlags().has(Chat.DIRTY))
			Bridge.setChatText(Strings.concat(getModel().getChatter(),"\n"));
	}
}
