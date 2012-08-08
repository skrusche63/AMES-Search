package de.kp.ames.search.client.widget;

import java.util.HashMap;

import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.SelectionType;
import com.smartgwt.client.types.VisibilityMode;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.ImgButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;

import de.kp.ames.search.client.control.CheckoutController;
import de.kp.ames.search.client.event.SearchEventManager;
import de.kp.ames.search.client.event.SearchResultConfirmedListener;
import de.kp.ames.search.client.event.SearchUpdateListener;
import de.kp.ames.search.client.layout.CenterportImpl;

public class ResultPortImpl extends CenterportImpl implements SearchUpdateListener, SearchResultConfirmedListener {

	private final static String CART_TITLE = "Semantic Cart";
	private SuggestFeedbackImpl suggestFeedback;
	private ResultImpl searchResult;
	private CartImpl resultCartResult;

	private Record suggestFeedbackRecord;
	final private SectionStack sectionStack;
	private ImgButton checkoutButton;

	public ResultPortImpl(Record record) {
		super();

		SC.logWarn("======> ResultPortImpl.CTOR");

		/*
		 * remember suggestion record
		 */
		this.suggestFeedbackRecord = record;

		

		suggestFeedback = new SuggestFeedbackImpl(record);
		suggestFeedback.setHeight("120");
		searchResult = new ResultImpl(record);
		resultCartResult = new CartImpl();
		
        checkoutButton = new ImgButton();  
        checkoutButton.setSize(16);  
        checkoutButton.setShowRollOver(false);  
        checkoutButton.setShowFocused(false);  
        checkoutButton.setShowDown(false);  
        
        checkoutButton.setActionType(SelectionType.BUTTON);
        checkoutButton.setSrc("silk/cart_go.png");  
        checkoutButton.setTooltip("Checkout your semantic research");
        checkoutButton.setAltText("Checkout your semantic research");
        //checkoutButton.disable(); // switches image to *_disable.png
        checkoutButton.setVisible(false);
        
        checkoutButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				SC.logWarn("====> ResultPortImpl.checkoutButton.onClick");

				HashMap<String,String> attributes = new HashMap<String,String>();
				new CheckoutController().doView(attributes, resultCartResult.getGridData());

			}
		});

		
		sectionStack = new SectionStack();
		sectionStack.setVisibilityMode(VisibilityMode.MULTIPLE);
		sectionStack.setWidth100();
		sectionStack.setHeight100();

		SectionStackSection sectionSuggestFeedback = new SectionStackSection("Suggestion");
		sectionSuggestFeedback.setExpanded(true);
		sectionSuggestFeedback.setCanCollapse(false);
		sectionSuggestFeedback.addItem(suggestFeedback);
		sectionStack.addSection(sectionSuggestFeedback);

		SectionStackSection sectionSearchResult = new SectionStackSection("Results");
		sectionSearchResult.setExpanded(true);
		sectionSearchResult.setCanCollapse(true);
		sectionSearchResult.addItem(searchResult);
		sectionStack.addSection(sectionSearchResult);

		SectionStackSection sectionResultCart = new SectionStackSection(CART_TITLE);
		sectionResultCart.setExpanded(false);
		sectionResultCart.setCanCollapse(true);
		sectionResultCart.setControls(checkoutButton);
		sectionResultCart.addItem(resultCartResult);
		sectionStack.addSection(sectionResultCart);
		
		this.setMembers(sectionStack);

		/*
		 * register listener
		 */
//		SearchEventManager.getInstance().addSearchResultSelectedListener(this);
		SearchEventManager.getInstance().addSearchResultConfirmedListener(this);
		SearchEventManager.getInstance().addSearchUpdateListener(this);

		SC.logWarn("======> ResultPortImpl.CTOR end");

	};
	

//	@Override
//	public void doAfterSearchResultSelected(Record resultRecord) {
//		SC.logWarn("======> ResultPortImpl.doAfterSearchResultSelected");
//		// TODO: update HyperTree
//	}

	@Override
	public void doAfterResultRecordConfirmed(Record resultRecord) {
		
		// get SectionStackSection (field does not work)
		SectionStackSection sectionResultCart = sectionStack.getSection(2);
		
		SC.logWarn("======> ResultPortImpl.doAfterSearchResultConfirmed: isExpanded? " + sectionResultCart.getAttributeAsBoolean("expanded"));
		resultCartResult.addChoice(suggestFeedbackRecord, resultRecord);

		int cartCount = resultCartResult.getCartCount();
		SC.logWarn("======> ResultPortImpl.doAfterSearchResultConfirmed: cartCount? " + cartCount);
		// update section title
		sectionResultCart.setTitle(CART_TITLE + " (" + cartCount + ")");
		
		// expand on first cart item
		if (cartCount==1) {
			sectionResultCart.setExpanded(true);
			checkoutButton.setVisible(true);
		}
		
	}

	@Override
	public void doAfterSearchUpdate(Record suggestionRecord) {
		SC.logWarn("======> ResultPortImpl.doAfterSearchUpdate");
		/*
		 * update suggestion record
		 */
		this.suggestFeedbackRecord = suggestionRecord;

		suggestFeedback.update(suggestionRecord);
		searchResult.update(suggestionRecord);
	}


}
