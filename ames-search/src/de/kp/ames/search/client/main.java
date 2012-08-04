package de.kp.ames.search.client;

/**
 * Copyright 2012. All rights reserved by Dr. Krusche & Partner PartG
 * Please contact: team@dr-kruscheundpartner.de
 */

import com.google.gwt.core.client.EntryPoint;

import de.kp.ames.search.client.control.MainController;
import de.kp.ames.search.client.widget.SimilarityFeedbackImpl;
import de.kp.ames.thejit.client.HyperTreeExplorer;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class main implements EntryPoint {

	/**
	 * This is the entry point method.
	 */
	 public void onModuleLoad() {
	 MainController.getInstance().createWelcome();
//	  MainController.getInstance().createTestCase();
	
//		 SimilarityFeedbackImpl s = new SimilarityFeedbackImpl();
//		 HyperTreeExplorer s = new HyperTreeExplorer();

//		 s.update("{\"id\":\"1\", \"name\":\"Core\", \"data\":[], \"children\":[" +
//					"{\"id\":\"2\", \"name\":\"Leaf1\", \"data\":[]}, " +
//					"{\"id\":\"3\", \"name\":\"Leaf2\", \"data\":[]}, " +
//					"{\"id\":\"4\", \"name\":\"Leaf3\", \"data\":[]}, " +
//					"{\"id\":\"5\", \"name\":\"Leaf4\", \"data\":[], \"children\":[" +
//						"{\"id\":\"6\", \"name\":\"SubLeaf1\", \"data\":[]}, " +
//						"{\"id\":\"7\", \"name\":\"SubLeaf2\", \"data\":[]} " +
//					"] }" + 
//				"] }");
//		 s.draw();
//		 s.update("{\"id\":\"1\", \"name\":\"Core\", \"children\":[" +
//					"{\"id\":\"2\", \"name\":\"Leaf1\", \"data\":[], \"children\":[]}, " +
//					"{\"id\":\"3\", \"name\":\"Leaf2\", \"data\":[], \"children\":[]}" +
//		 		"], \"data\":[]}");
//		 
	 }
}
