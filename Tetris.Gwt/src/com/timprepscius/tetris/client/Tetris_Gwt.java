///////////////////////////////////////////////////////////////////////////////
// Copyright (C) 2013 Timothy Prepscius
//
// This is a viewable source license.  No copying or modification or use is
// permitted.
//
// If you would like to participate in its development, or
// if you would like a different license, please contact the author.
///////////////////////////////////////////////////////////////////////////////

package com.timprepscius.tetris.client;

import org.timepedia.exporter.client.ExporterUtil;

import tetris.imp.gwt.DriverGwt;
import com.google.gwt.core.client.EntryPoint;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Tetris_Gwt implements EntryPoint 
{
	DriverGwt driver = new DriverGwt();
	
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() 
	{
		ExporterUtil.exportAll();
		driver.main(); 
	}
}
