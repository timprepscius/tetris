package com.benith.tetris.client;

import tetris.imp.gwt.DriverGwt;

import com.google.gwt.core.client.EntryPoint;

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
