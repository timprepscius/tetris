package tetris.imp.gwt;

import java.util.HashSet;
import java.util.Set;

import tetris.imp.view.ViewPlatform;
import tetris.model.KeyState;
import com.google.gwt.core.client.JavaScriptObject;

public class Bridge 
{
	public static JavaScriptObject 
		chooseRoomContainer,
		playContainer,
		countdown,
		
		gameList,
		localBoard, 
		localBag,
		localHold,
		remoteBoards[];
	
	public static ViewPlatform focus;
	
	public static native JavaScriptObject getElement (String id) /*-{
		return $wnd.$('#'+id);
	}-*/;
	
	public static void link ()
	{
		chooseRoomContainer = getElement("chooseRoomContainer");
		playContainer = getElement("playContainer");
		
		countdown = getElement("countdown");
		gameList = getElement("gameList");
		localBoard = getElement("localBoard");
		localBag = getElement("localBag");
		localHold = getElement("localHold");
		remoteBoards = new JavaScriptObject[5];
		for (int i=0; i<Bridge.remoteBoards.length; ++i)
			remoteBoards[i] = getElement("remoteBoard" + i);
	}
	
	public static Set<JavaScriptObject> remoteBoardsInUse = new HashSet<JavaScriptObject>();

	public static KeyState keyState = new KeyState();
	
	public static boolean onKeyDown (int keyCode)
	{
		keyState.setState(keyCode, true);
		
		if (focus!=null)
			return focus.keyPress(keyCode);
		
		return false;
	}
	
	public static boolean onKeyUp (int keyCode)
	{
		keyState.setState(keyCode, false);
		
		return false;
	}
	
	public static void onKeyRepeat ()
	{
		if (focus!=null)
			focus.keyRepeat(keyState);
	}
	
	public static void onChatEntry (String string)
	{
		if (focus!=null)
			focus.onText(string);
	}
	
	public static void onGameFocus ()
	{
		LocalBoardViewGwt.getInstance().requestFocus();
	}
	
	public static void onChatFocus ()
	{
		ChatViewGwt.getInstance().requestFocus();
	}

	public static native JavaScriptObject createGameListItem (GameListViewGwt _parent, String _id, String title) /*-{
		var parent = _parent;
		var id = _id;
		
		var i = $wnd.$('#gameListItemTemplate').clone();
		i.find('.title').text(title);
		i.find('.join').click(function() { $entry(@tetris.imp.gwt.GameListViewGwt::onJoin(Ljava/lang/Object;Ljava/lang/String;))(parent, id); });
		return i;
	}-*/;
	
	public static native void addItemToContainer (JavaScriptObject container, JavaScriptObject item) /*-{
		container.append(item);
	}-*/;
	
	public static native void clearContainer (JavaScriptObject container) /*-{
		container.html("");
	}-*/;
	
	public static native void hide (JavaScriptObject element) /*-{
		element.hide();
	}-*/;

	public static native void show (JavaScriptObject element) /*-{
		element.show();
	}-*/;

	public static native void installKeyHandlerHook (int initialDelay, int interval) /*-{
		
		$wnd.onChatEntry = $entry(@tetris.imp.gwt.Bridge::onChatEntry(Ljava/lang/String;));
		$wnd.onChatFocus = $entry(@tetris.imp.gwt.Bridge::onChatFocus());
		$wnd.onGameFocus = $entry(@tetris.imp.gwt.Bridge::onGameFocus());
		
		keyRepeatInitialDelay = initialDelay;
		keyRepeatInterval = interval;
		keyRepeater = null;
		keyRepeatDelayCycle = false;
		keysDown = [];
		
		keyRepeatFunc = function() { 
			if (keysDown.length > 0)
			{
				if (!keyRepeatDelayCycle)
					$entry(@tetris.imp.gwt.Bridge::onKeyRepeat())(); 
				
				keyRepeatDelayCycle = false;
			}
		};
		
		keyRepeater = setInterval(keyRepeatFunc, interval);
		
	    $wnd.onkeydown = function(evt) {
    		var key = evt.keyCode;
    		
    		var consumed = $entry(@tetris.imp.gwt.Bridge::onKeyDown(I))(key);
    		if (consumed)
    		{
	    		for (var i=0; i<keysDown.length; ++i)
	    			if (keysDown[i] == key)
	    				return false;
	    				
	    		keysDown.push(key);
	    		keyRepeatDelayCycle = true;
    			evt.preventDefault();
    		}
    		
    		return !consumed;
    	}
	    
	    $wnd.onkeyup = function(evt) {
    		var key = evt.keyCode;
    		
    		var consumed = $entry(@tetris.imp.gwt.Bridge::onKeyUp(I))(key);
    		if (consumed)
    			evt.preventDefault();
    			
    		for (var i=0; i<keysDown.length; ++i)
    			if (keysDown[i] == key)
    				keysDown.splice(i,1);
    				
    		return !consumed;
    	}
	    
	    $wnd.$('#entry').click(function() { window.onChatFocus(); });
	    $wnd.$('#entry').keyup(function(event) {
    		if(event.keyCode == 13)
    		{
    			$wnd.onChatEntry($wnd.$('#entry').val());
    			$wnd.$('#entry').val('');
    		}
	    });
	    
	    $wnd.$('#localBoardContainer').click(function() { window.onGameFocus(); });
	}-*/;

	public static native void setChatText(String text) /*-{
		$wnd.$('#chat').html($wnd.htmlForTextWithEmbeddedNewlines(text));
	}-*/;
}
