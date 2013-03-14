package com.benith.tetris.client;

import tetris.imp.gwt.Bridge;
import tetris.imp.gwt.DriverGwt;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dev.shell.JavaObject;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Tetris_Gwt implements EntryPoint {

	DriverGwt driver = new DriverGwt();
	
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() 
	{
		driver.main(); 
	}
}
