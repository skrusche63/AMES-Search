package de.kp.ames.search.client.layout;
/**
 * Copyright 2012. All rights reserved by Dr. Krusche & Partner PartG
 * Please contact: team@dr-kruscheundpartner.de
 */

import com.google.gwt.json.client.JSONValue;
import com.smartgwt.client.util.SC;

import de.kp.ames.search.client.event.SearchEventManager;
import de.kp.ames.search.client.event.SimilarityListener;
import de.kp.ames.search.client.style.GuiStyles;
import de.kp.ames.search.client.widget.SimilarityFeedbackImpl;


public class SimilarityPortImpl extends RightportImpl implements SimilarityListener {

	private SimilarityFeedbackImpl similarityFeedback;

	/**
	 * Constructor
	 */
	public SimilarityPortImpl() {

		SC.logWarn("======> ResultPortImpl.CTOR");

		/*
		 * Dimensions
		 */
		this.setWidth(GuiStyles.RIGHT_LINE_WIDTH);
		this.setHeight100();

		this.setShowEdges(false);

		/*
		 * Style
		 */
		this.setStyleName(GuiStyles.X_BD_STYLE_4);
		this.setBackgroundColor(GuiStyles.RIGHT_LINE_BG);
		
		similarityFeedback = new SimilarityFeedbackImpl();
		
		this.setMembers(similarityFeedback);

		/*
		 * register listener
		 */
		SearchEventManager.getInstance().addSimilarityListener(this);
		
	};
	

	@Override
	public void doShowSimilarity(JSONValue jValue) {
		SC.logWarn("======> SimilarityPortImpl.doShowSimilarity");
		/*
		 * update suggestion record
		 */
		similarityFeedback.update(jValue);
	}
}
