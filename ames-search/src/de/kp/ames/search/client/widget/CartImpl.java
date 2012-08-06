package de.kp.ames.search.client.widget;

import java.util.HashMap;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.user.client.ui.NamedFrame;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.types.SelectionType;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.ImgButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.toolbar.ToolStrip;

import de.kp.ames.search.client.control.CheckoutController;
import de.kp.ames.search.client.data.CartGridImpl;
import de.kp.ames.search.client.event.DownloadListener;
import de.kp.ames.search.client.event.SearchEventManager;
import de.kp.ames.search.client.globals.GUIGlobals;
import de.kp.ames.search.client.globals.MethodConstants;
import de.kp.ames.search.client.method.RequestMethodImpl;
import de.kp.ames.search.client.style.GuiStyles;

public class CartImpl extends VLayout implements DownloadListener {
	private CartGridImpl grid;
	private ImgButton checkoutButton;
	private DynamicForm form;

	public CartImpl() {

		this.setShowEdges(false);
		this.setStyleName(GuiStyles.X_BD_STYLE_3);
		
		this.setWidth100();
		this.setHeight100();
		
		SC.logWarn("========> CartImpl.CTOR 1");

		/*
		 * Build ToolStrip
		 */
		ToolStrip ts = createToolStrip();

		/*
		 * Build Grid
		 */
		grid = new CartGridImpl();

		SC.logWarn("========> CartImpl.CTOR 2");
		
		/*
		 * A dynamic form is used to submit a Post request which response 
		 * target is the NamedFrame 
		 */
		DataSource dataSource = new DataSource();

	    DataSourceTextField hiddenTextField = new DataSourceTextField("hiddenField");
	    // hiddenTextField.setHidden(true);
	    dataSource.setFields(hiddenTextField);
	    
		form = new DynamicForm();
		form.setHeight(1);
		form.setTitleSuffix(""); // default ":"
		form.setDataSource(dataSource);

		form.setVisible(true);
		
		
		/*
		 * Hidden iFrame
		 */
		NamedFrame iFrame = new NamedFrame("downloadFrame");
		
		iFrame.setWidth("1");
		iFrame.setHeight("1");
		iFrame.setVisible(false);
		
		form.setTarget("downloadFrame");
		
		
		this.setMembers(ts, grid, form);
		this.addMember(iFrame);
		
		
		/*
		 * register listener
		 */
		SearchEventManager.getInstance().addDownloadListener(this);

	}

	public void addChoice(Record suggestFeedbackRecord, Record resultRecord) {
		
		String combinedId = suggestFeedbackRecord.getAttribute("id") + "::" + resultRecord.getAttribute("id");
		
		if (this.hasCombinedId(combinedId)) return;
		
		Record record = new Record();
		record.setAttribute("cid", combinedId);
		
		SC.logWarn("========> CartImpl.addChoice combId: " + record.getAttribute("id"));

		/*
		 * add suggest fields
		 */
		record.setAttribute("suggest", suggestFeedbackRecord.getAttribute("term"));

		/*
		 * add result fields
		 */
		record.setAttribute("choice", resultRecord.getAttribute("title"));
		record.setAttribute("id", resultRecord.getAttribute("id"));
		grid.addData(record);
	}

	private boolean hasCombinedId(String id) {
		return grid.hasCombinedId(id);
	}

	/**
 	 * Create ToolStrip element
 	 * 
	 * @param url
	 * @param params
	 * @param fields
	 * @return
	 */
	private ToolStrip createToolStrip() {

		ToolStrip ts = new ToolStrip();
//		ts.setStyleName("x-searchbox");

		ts.setWidth100();
		ts.setHeight(25);
		ts.addFill();

        checkoutButton = new ImgButton();  
        checkoutButton.setSize(24);  
        checkoutButton.setShowRollOver(true);  
        checkoutButton.setActionType(SelectionType.BUTTON);
        checkoutButton.setSrc("[SKIN]/headerIcons/cart.png");  
        checkoutButton.setTooltip("Checkout your semantic research");
        checkoutButton.setAltText("Checkout your semantic research");
        checkoutButton.disable(); // switches image to *_disable.png
        
        checkoutButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				SC.logWarn("====> CartImpl.checkoutButton.onClick");

				HashMap<String,String> attributes = new HashMap<String,String>();
				new CheckoutController().doView(attributes, getGridData());

			}
		});
		
		
        ts.addMember(checkoutButton);  
		return ts;

	}

	/**
	 * enable Checkout button
	 */
	public void activateCheckout() {
		SC.logWarn("====> CartImpl.activateCheckout");
        checkoutButton.enable();

	}
	/**
	 * Delegate call to grid
	 * @return
	 */
	public int getCartCount() {
//		return grid.getRecord().length;
		return grid.getTotalRows();
	}

	/**
	 * Delegate call to grid
	 * @return
	 */
	public JSONArray getGridData() {
		return grid.getGridData();
	}


	/*
	 * (non-Javadoc)
	 * @see de.kp.ames.search.client.event.DownloadListener#doTriggerDownload()
	 */
	@Override
	public void doTriggerDownload() {
		SC.logWarn("====> CartImpl.doTriggerDownload");

//		HashMap<String,String> attributes = new HashMap<String,String>();
//		new CheckoutController().doDownload(attributes, getGridData());
		
	    form.setValue("hiddenField", getGridData().toString());
	    form.setAction(getUri());
	    form.submitForm();

		
	}

	/**
	 * A helper method to retrieve the form action url
	 * 
	 * @return
	 */
	private String getUri() {

		/*
		 * Configure service call to provide data for Checkout Viewer
		 */
		HashMap<String, String> attributes = new HashMap<String, String>();
		attributes.put(MethodConstants.ATTR_TYPE, "download");
		attributes.put(MethodConstants.ATTR_SOURCE, GUIGlobals.SEARCH_SOURCE);
			
		/*
		 * Build method
		 */
		RequestMethodImpl requestMethod = new RequestMethodImpl();
		requestMethod.setName(MethodConstants.METH_APPLY);

		requestMethod.setAttributes(attributes);
		
		
		SC.logWarn("====> CartImpl.getUri: " + getRequestUrl() + requestMethod.toQuery());
		/*
		 * Build request uri
		 */
		return getRequestUrl() + requestMethod.toQuery();
		
	}
		
	/**
	 * Build base request url for mulitpart request
	 * 
	 * @return
	 */
	private String getRequestUrl() {
		return GUIGlobals.SEARCH_URL + "/" + "search";
		
	}

	
}
