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

import tetris.model.CanvasInfo;

public class CanvasInfoGwt extends CanvasInfo
{
	public void calculate (Object canvas)
	{
		this.canvas = canvas;
		context = getContext(canvas);
		w = getWidth(context);
		h = getHeight(context);
	}

	Object canvas;
	Object context;
	
	public void clear ()
	{
		clear(context, 0, 0, w,h);
	}
	
	public void clear (float x, float y, float w, float h)
	{
		clear(context, x, y, w, h);
	}
	
	native public void clear(Object context, float x, float y, float width, float height) /*-{
		context.clearRect(x,y,width, height);
	}-*/;
	
	//--------------------------------------------------------------------
	
	native int getWidth(Object context) /*-{
		return context.canvas.width;
	}-*/;
	
	native int getHeight(Object context) /*-{
		return context.canvas.height;
	}-*/;
	
	native Object getContext(Object v) /*-{
		// there must be some func instead of get(0), blech
		return v.get(0).getContext('2d');
	}-*/;
	
}
