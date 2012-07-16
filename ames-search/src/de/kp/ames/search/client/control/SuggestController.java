package de.kp.ames.search.client.control;

import java.util.HashMap;

import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.ui.RootPanel;

import de.kp.ames.search.client.activity.ActivityImpl;
import de.kp.ames.search.client.globals.GuiGlobals;
import de.kp.ames.search.client.globals.MethodConstants;
import de.kp.ames.search.client.service.ServiceImpl;
import de.kp.ames.search.client.widget.SuggestImpl;

public class SuggestController {

	/*
	 * Reference to SuggestImpl
	 */
	private SuggestImpl suggestor;
	
	/*
	 * Coordinates
	 */
	private int x;
	private int y;
	
	private static SuggestController instance = new SuggestController();
	
	private SuggestController() {
	}
	
	public static SuggestController getInstance() {
		if (instance == null) instance = new SuggestController();
		return instance;
	}
	
	public void createSuggestor(int x, int y, String query) {

		/*
		 * Register coordinates
		 */
		this.x = x;
		this.y = y;
		
		/*
		 * Remove suggestor from root panel
		 */
		RootPanel rp = RootPanel.get();
		
		if (suggestor != null) {
			rp.remove(suggestor);
			
			suggestor.destroy();
			suggestor = null;

		}
				
		/*
		 * Build request data
		 */
		HashMap<String,String> attributes = new HashMap<String,String>();
		attributes.put(MethodConstants.ATTR_QUERY, query);
		
		/*
		 * Create Service
		 */
		ServiceImpl service = new ServiceImpl(GuiGlobals.SEARCH_URL, "search");
		service.doSuggest(attributes, new ActivityImpl() {

			public void execute(JSONValue jValue) {
				doAfterDataArrived(jValue);				
			}
			
		});
		
		
	}
	
	public void moveSuggestorTo(int x, int y) {
		if (suggestor != null) suggestor.moveTo(x, y);
	}
	
	public void removeSuggestor() {

		RootPanel rp = RootPanel.get();
		
		if (suggestor != null) {
			rp.remove(suggestor);
			
			suggestor.destroy();
			suggestor = null;

		}
	}

	private void doAfterDataArrived(JSONValue jValue) {
		suggestor = new SuggestImpl(jValue);

		RootPanel rp = RootPanel.get();
		rp.add(suggestor);

		moveSuggestorTo(x,y);
		
	}

}
