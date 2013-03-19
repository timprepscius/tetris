///////////////////////////////////////////////////////////////////////////////
// Copyright (C) 2013 Timothy Prepscius
//
// This is a viewable source license.  No copying or modification or use is
// permitted.
//
// If you would like to participate in its development, or
// if you would like a different license, please contact the author.
///////////////////////////////////////////////////////////////////////////////

package tetris.model;

import tetris.net.Net;
import tetris.net.NetFactory;
import tetris.view.View;
import tetris.view.ViewFactory;

public class Model
{
	Model parent;
	
	View view;
	Net net;
	Flags flags = new Flags();
	ID id;
	
	public void onStart()
	{
		
	}
	
	public final void start ()
	{
		if (getView() == null)
			view = ViewFactory.getInstance().instantiateFor(this);
		if (getNet() == null)
			net = NetFactory.getInstance().instantiateFor(this);
		
		onStart();
		
		if (getView() != null)
			getView().start();
		
		if (getNet() != null)
			getNet().start();
	}
	
	public void onTick()
	{
		
	}
	
	public void onStop ()
	{
		
	}
		
	public final void stop ()
	{
		onStop();
		
		if (getView() != null)
			getView().stop();
		
		if (getNet() != null)
			getNet().stop();
	}
	
	public void tick ()
	{
		onTick();
		
		if (getView() != null)
			getView().onTick();
		
		if (getNet() != null)
			getNet().onTick();
		
		getFlags().clear();
	}
	
	public Flags getFlags ()
	{
		return flags;
	}
	
	public void setID (ID id)
	{
		this.id = id;
	}
	
	public ID getID ()
	{
		return id;
	}
	
	public Model getParent ()
	{
		return parent;
	}
	
	public View getView ()
	{
		return view;
	}
	
	public void setView (View view)
	{
		this.view = view;
	}
	
	public Net getNet ()
	{
		return net;
	}
	
	public void setNet (Net net)
	{
		this.net = net;
	}
}
