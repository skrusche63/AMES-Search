package de.kp.ames.search.client.widget;
/**
 * Copyright 2012. All rights reserved by Dr. Krusche & Partner PartG
 * Please contact: team@dr-kruscheundpartner.de
 */

import com.smartgwt.client.data.Record;
import com.smartgwt.client.widgets.layout.HLayout;

import de.kp.ames.search.client.layout.LeftportImpl;
import de.kp.ames.search.client.layout.RightportImpl;
import de.kp.ames.search.client.layout.SimilarityPortImpl;

public class ResultFeedbackImpl extends HLayout {
	
	private LeftportImpl leftPort;
	private ResultPortImpl centerPort;
	private RightportImpl rightPort;

	/**
	 * Constructor
	 * 
	 * @param record
	 */
	public ResultFeedbackImpl(Record record) {
		
		this.setWidth100();
		this.setHeight100();
		
		/*
		 * Left viewport is empty
		 */
		leftPort   = new LeftportImpl();
		
		/*
		 * Center viewport holds search results
		 */
		centerPort = new ResultPortImpl(record);
		
		/*
		 * Right viewport is empty
		 */
		rightPort  = new SimilarityPortImpl();

		this.setMembers(leftPort, centerPort, rightPort);
		
	};

}
