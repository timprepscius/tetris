///////////////////////////////////////////////////////////////////////////////
// Copyright (C) 2013 Timothy Prepscius
//
// This is a viewable source license.  No copying or modification or use is
// permitted.
//
// If you would like to participate in its development, or
// if you would like a different license, please contact the author.
///////////////////////////////////////////////////////////////////////////////

IS_PRODUCTION = __IS_PRODUCTION__;

function htmlForTextWithEmbeddedNewlines(text) 
{
    var htmls = [];
    var lines = text.split(/\n/);
    // The temporary <div/> is to perform HTML entity encoding reliably.
    //
    // document.createElement() is *much* faster than jQuery('<div/>')
    // http://stackoverflow.com/questions/268490/
    //
    // You don't need jQuery but then you need to struggle with browser
    // differences in innerText/textContent yourself
    var tmpDiv = jQuery(document.createElement('div'));
    for (var i = 0 ; i < lines.length ; i++) {
        htmls.push(tmpDiv.text(lines[i]).html());
    }
    return htmls.join("<br>");
}

function log(s)
{
	console.log(s);
}

delegate = {
	installKeyHandlerHook: function(initialDelay, interval) {
		keyRepeatInitialDelay = initialDelay;
		keyRepeatInterval = interval;
		keyRepeater = null;
		keyRepeatDelayCycle = false;
		keysDown = [];
		
		keyRepeatFunc = function() { 
			if (keysDown.length > 0)
			{
				if (!keyRepeatDelayCycle)
					tetris.imp.gwt.Bridge.onKeyRepeat(); 
				
				keyRepeatDelayCycle = false;
			}
		};
		
		keyRepeater = setInterval(keyRepeatFunc, interval);
		
	    window.onkeydown = function(evt) {
    		var key = evt.keyCode;
    		
    		var consumed = tetris.imp.gwt.Bridge.onKeyDown(key);
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
    	};
	    
	    window.onkeyup = function(evt) {
    		var key = evt.keyCode;
    		
    		var consumed = tetris.imp.gwt.Bridge.onKeyUp(key);
    		if (consumed)
    			evt.preventDefault();
    			
    		for (var i=0; i<keysDown.length; ++i)
    			if (keysDown[i] == key)
    				keysDown.splice(i,1);
    				
    		return !consumed;
    	};
	    
	    $('#entry').click(function() { tetris.imp.gwt.Bridge.onChatFocus(); });
	    $('#entry').keyup(function(event) {
    		if(event.keyCode == 13)
    		{
    			tetris.imp.gwt.Bridge.onChatEntry($('#entry').val());
    			$('#entry').val('');
    		}
	    });
	    
	    $('#localBoardContainer').click(function() { tetris.imp.gwt.Bridge.onGameFocus(); });
	},
	
	setChatText: function(text) {
		var div = $('#chat');
		div.html(htmlForTextWithEmbeddedNewlines(text));
		
		if (div.scrollTop() > (0.9 * div[0].scrollHeight));
			div.scrollTop(div[0].scrollHeight);
	},
	
	createGameListItem: function(parent, info) {
		var instance = $('#gameListItemTemplate').clone();
		instance.find('.title').text(info.getName());
		instance.find('.join').click(function() { tetris.imp.gwt.Bridge.onJoin(parent, info.getID()); });
		return instance;
	}
};
