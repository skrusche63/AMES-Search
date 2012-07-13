package de.kp.ames.search.client.handler;
/**
 * Copyright 2012. All rights reserved by Dr. Krusche & Partner PartG
 * Please contact: team@dr-kruscheundpartner.de
 */

public interface SearchHandler {

	/**
	 * Initiate Fulltext-search for a selected application
	 * 
	 * @param query
	 */
	public void doSearch(String query);

}
