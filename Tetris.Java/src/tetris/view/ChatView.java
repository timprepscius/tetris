///////////////////////////////////////////////////////////////////////////////
// Copyright (C) 2013 Timothy Prepscius
//
// This is a viewable source license.  No copying or modification or use is
// permitted.
//
// If you would like to participate in its development, or
// if you would like a different license, please contact the author.
///////////////////////////////////////////////////////////////////////////////

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
