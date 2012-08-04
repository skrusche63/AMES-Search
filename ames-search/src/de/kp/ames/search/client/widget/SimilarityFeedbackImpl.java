package de.kp.ames.search.client.widget;

import com.google.gwt.json.client.JSONValue;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.layout.VLayout;

import de.kp.ames.thejit.client.HyperTreeExplorer;

public class SimilarityFeedbackImpl extends VLayout {
	private HyperTreeExplorer hyperTreeExplorer;

	public SimilarityFeedbackImpl() {

		this.setWidth100();
		this.setHeight100();

		hyperTreeExplorer = new HyperTreeExplorer();

		this.setMembers(hyperTreeExplorer);

	}

	public void update(JSONValue jValue) {
		SC.logWarn("======> SimilarityFeedbackImpl.update> width: " + hyperTreeExplorer.getWidth());

//		String test = "{\"id\":\"1\", \"name\":\"Core\", \"children\":["
//				+ "{\"id\":\"2\", \"name\":\"Leaf1\", \"data\":[], \"children\":[]}, "
//				+ "{\"id\":\"3\", \"name\":\"Leaf2\", \"data\":[], \"children\":[]}" + "], \"data\":[]}";

		 hyperTreeExplorer.loadJTree(jValue.toString());
//		hyperTreeExplorer.loadJTree(test);
	}

	public void update(String jValue) {
		SC.logWarn("======> SimilarityFeedbackImpl.update> width: " + hyperTreeExplorer.getWidth());
		hyperTreeExplorer.loadJTree(jValue);
		hyperTreeExplorer.resizeTree();
	}

}
