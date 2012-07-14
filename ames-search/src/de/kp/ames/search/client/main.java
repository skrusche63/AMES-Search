package de.kp.ames.search.client;
/**
 * Copyright 2012. All rights reserved by Dr. Krusche & Partner PartG
 * Please contact: team@dr-kruscheundpartner.de
 */

import com.google.gwt.core.client.EntryPoint;

import de.kp.ames.search.client.control.MainController;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class main implements EntryPoint {

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		 MainController.getInstance().createWelcome();
//		MainController.getInstance().createTestCase();

	}

}
