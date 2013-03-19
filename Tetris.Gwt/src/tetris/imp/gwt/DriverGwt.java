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

import tetris.model.ID;
import tetris.model.Tetris;
import tetris.net.NetFactory;
import tetris.network.ConnectionFactory;
import tetris.view.ViewFactory;

public class DriverGwt 
{
	Tetris tetris;
	
	public void main ()
	{
		Bridge.link();
		Bridge.installKeyHandlerHook(100, 100);
		
        ConnectionFactory connectionFactory = new ConnectionFactoryGwt();
        NetFactory.setInstance(new NetFactory());
        ViewFactory.setInstance(new ViewFactoryGwt());
        
        String userToken = ID.random().toString();
        tetris = new Tetris(userToken);
        tetris.setView(ViewFactory.getInstance().instantiateFor(null, tetris));
        tetris.setNet(
        	NetFactory.getInstance().instantiateFor(
                tetris, 
                connectionFactory.newConnection(
                	ConstantsGwt.HOST, 
                	ConstantsGwt.PORT
                )
            )
        );
        
        tetris.start();
        
        doCreateTimer (this, 300);
	}
	
	public native void doCreateTimer (DriverGwt self, int interval) /*-{
		setInterval(function() { $entry(@tetris.imp.gwt.DriverGwt::onTick(Ljava/lang/Object;))(self); }, interval);
	}-*/;
	
	public void onTick ()
	{
		tetris.tick();
	}
	
	public static void onTick(Object self)
	{
		((DriverGwt)self).onTick();
	}
}
