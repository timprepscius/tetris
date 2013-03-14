package tetris.imp.gwt;

import tetris.model.CanvasInfo;

import com.google.gwt.core.client.JavaScriptObject;

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
