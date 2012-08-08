package de.kp.ames.search.client.control;

import java.util.HashMap;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;
import com.smartgwt.client.util.SC;

import de.kp.ames.search.client.activity.ActivityImpl;
import de.kp.ames.search.client.event.SearchEventManager;
import de.kp.ames.search.client.globals.GUIGlobals;
import de.kp.ames.search.client.globals.MethodConstants;
import de.kp.ames.search.client.service.ServiceImpl;
import de.kp.ames.search.client.widget.CheckoutViewerImpl;

public class CheckoutController {


	/**
	 * @param attributes
	 * @param record
	 */
	public void doView(HashMap<String,String> attributes, JSONArray jCheckoutData) {

		/*
		 * Build viewer
		 */
		String title  = "Checkout Cart Viewer";
		String slogan = "This is the cart with your researched items and their suggested contexts";
		// this widget is a listener for checkout html
		new CheckoutViewerImpl(title, slogan);

		/*
		 * Configure service call to provide data for Checkout Viewer
		 */
		attributes.put(MethodConstants.ATTR_TYPE, "checkout");
		attributes.put(MethodConstants.ATTR_SOURCE, GUIGlobals.SEARCH_SOURCE);
			

		/*
		 * Send post request
		 */
		ServiceImpl service = new ServiceImpl(GUIGlobals.SEARCH_URL, "search");
		service.doApply(attributes, jCheckoutData.toString(), new ActivityImpl() {
			
			@Override
			public void execute(JSONValue jValue) {
				
				SC.logWarn("======> CheckoutController.execute on JSONCallback");

				doAfterDataArrived((JSONObject)jValue);		
			}
		});
		
	}
	
	/*
	 * this callback is replaced by form download
	 */
	private void doAfterDataArrived(JSONObject jObject) {
		SC.logWarn("======> CheckoutController.doAfterDataArrived");
		SearchEventManager.getInstance().doAfterUpdate(jObject);
		
	}

}
