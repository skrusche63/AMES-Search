package de.kp.ames.search.client.control;

import java.util.HashMap;

import com.google.gwt.json.client.JSONValue;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.util.SC;

import de.kp.ames.search.client.activity.ActivityImpl;
import de.kp.ames.search.client.event.SearchEventManager;
import de.kp.ames.search.client.globals.GUIGlobals;
import de.kp.ames.search.client.globals.JsonConstants;
import de.kp.ames.search.client.globals.MethodConstants;
import de.kp.ames.search.client.service.ServiceImpl;

public class SimilarityController {

	public void doGetSimilarity(Record record) {
		
		SC.logWarn("======> SimilarityController.doGetSimilarity");

		/*
		 * Build request data
		 */
		HashMap<String,String> attributes = new HashMap<String,String>();
		attributes.put(MethodConstants.ATTR_TYPE, "similar");
		attributes.put(MethodConstants.ATTR_SOURCE, "wn"); // scm
		attributes.put(MethodConstants.ATTR_QUERY, record.getAttribute(JsonConstants.J_ID));
		
		/*
		 * Create Service
		 */
		ServiceImpl service = new ServiceImpl(GUIGlobals.SEARCH_URL, "search");
		service.doGetJson(attributes, new ActivityImpl() {

			public void execute(JSONValue jValue) {
				SC.logWarn("======> SimilarityController.execute on JSONCallback");

				doAfterDataArrived(jValue);				
			}
			
		});
		
	}

	private void doAfterDataArrived(JSONValue jValue) {
		SC.logWarn("======> SimilarityController.doAfterDataArrived");
		SearchEventManager.getInstance().doShowSimilarity(jValue);
		
	}

}
