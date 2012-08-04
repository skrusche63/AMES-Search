package de.kp.ames.search.client.widget;

import com.smartgwt.client.util.SC;

import de.kp.ames.thejit.client.HyperTreeExplorer;

public class SimilarityHyperTreeExplorer extends HyperTreeExplorer {
	public SimilarityHyperTreeExplorer() {
		super();
	}

	
	public void resizeTree() {
		double w = (double) this.getWidth();
		double h = (double) this.getHeight();
		SC.logWarn("======> SimilarityHyperTreeExplorer.resizeTree> h/w: " + h + "/" + w);
		super.resizeTree();
		
	}
	
    public void loadDefault() {

    	//this.setTitle("Hypertree");
 
    	// initial loading of hypertree
		this.loadJTree("{\"id\":\"1\", \"name\":\"Core\", \"children\":[" +
				"{\"id\":\"2\", \"name\":\"Leaf1\", \"data\":[], \"children\":[]}, " +
				"{\"id\":\"3\", \"name\":\"Leaf2\", \"data\":[], \"children\":[]}" +
	 		"], \"data\":[]}");
		this.resizeTree();
		
    }

	

}
