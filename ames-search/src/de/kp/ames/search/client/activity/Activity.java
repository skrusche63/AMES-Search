package de.kp.ames.search.client.activity;
/**
 * Copyright 2012. All rights reserved by Dr. Krusche & Partner PartG
 * Please contact: team@dr-kruscheundpartner.de
 */

import com.google.gwt.json.client.JSONValue;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.tree.TreeNode;

public interface Activity {

	public void execute();
	
	/**
	 * @param text
	 */
	public void execute(String text);
	
	/**
	 * @param jValue
	 */
	public void execute(JSONValue jValue);

	/**
	 * @param record
	 */
	public void execute(ListGridRecord record);

	/**
	 * @param node
	 */
	public void execute(TreeNode node);
	
}
