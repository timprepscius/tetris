///////////////////////////////////////////////////////////////////////////////
// Copyright (C) 2013 Timothy Prepscius
//
// This is a viewable source license.  No copying or modification or use is
// permitted.
//
// If you would like to participate in its development, or
// if you would like a different license, please contact the author.
///////////////////////////////////////////////////////////////////////////////

package tetris.imp.gwt;

import java.util.HashSet;
import java.util.Set;

import org.timepedia.exporter.client.Export;
import org.timepedia.exporter.client.Exportable;

import tetris.imp.view.ViewPlatform;
import tetris.model.GameInfo;
import tetris.model.ID;
import tetris.model.KeyState;

import com.google.gwt.core.client.JavaScriptObject;

@Export
public class Bridge implements Exportable
{
	public static ViewPlatform focus;
	public static JavaScriptObject 
	
		delegate,
		chooseRoomContainer,
		playContainer,
		countdown,
		
		gameList,
		localBoard, 
		localBag,
		localHold,
		remoteBoards[];
	
	public static native JavaScriptObject getElement (String id) /*-{
		return $wnd.$('#'+id);
	}-*/;
	
	public static native JavaScriptObject getScriptObject (String id) /*-{
		return $wnd[id];
	}-*/;
	
	public static Object invoke (Object oo, String f, Object...p)
	{
		return JSInvoker.invokeR(oo, f, p);
	}
	
	public static Object invoke (Object oo, String f)
	{
		return JSInvoker.invokeR(oo, f);
	}

	public static void link ()
	{
		chooseRoomContainer = getElement("chooseRoomContainer");
		playContainer = getElement("playContainer");
		
		delegate = getScriptObject("delegate");
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
	
	public static void onJoin(GameListViewGwt gameListView, ID id)
	{
		gameListView.onJoin(id);
	}
	
	public static void onGameFocus ()
	{
		LocalBoardViewGwt.getInstance().requestFocus();
	}
	
	public static void onChatFocus ()
	{
		ChatViewGwt.getInstance().requestFocus();
	}

	public static Object createGameListItem (GameListViewGwt parent, GameInfo info) 
	{
		return invoke(delegate, "createGameListItem", parent, info);
	}
	
	public static void installKeyHandlerHook (int initialDelay, int interval) {
		invoke(delegate, "installKeyHandlerHook", initialDelay, interval);
	}

	public static void setChatText(String text) {
		invoke(delegate, "setChatText", text);
	}

	public static native boolean isProduction() /*-{
		return $wnd.IS_PRODUCTION;
	}-*/;
}
