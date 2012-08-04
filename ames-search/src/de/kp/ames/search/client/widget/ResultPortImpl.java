package de.kp.ames.search.client.widget;

import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.VisibilityMode;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;

import de.kp.ames.search.client.event.SearchEventManager;
import de.kp.ames.search.client.event.SearchResultConfirmedListener;
import de.kp.ames.search.client.event.SearchResultSelectedListener;
import de.kp.ames.search.client.event.SearchUpdateListener;
import de.kp.ames.search.client.layout.CenterportImpl;

public class ResultPortImpl extends CenterportImpl implements SearchUpdateListener, SearchResultSelectedListener,
		SearchResultConfirmedListener {

	private final static String CART_TITLE = "Semantic Cart";
	private SuggestFeedbackImpl suggestFeedback;
	private SearchResultImpl searchResult;
	private ResultCartImpl resultCartResult;

	private Record suggestFeedbackRecord;
	final private SectionStack sectionStack;

	public ResultPortImpl(Record record) {
		super();

		SC.logWarn("======> ResultPortImpl.CTOR");

		/*
		 * remember suggestion record
		 */
		this.suggestFeedbackRecord = record;

		suggestFeedback = new SuggestFeedbackImpl(record);
		suggestFeedback.setHeight("160");
		searchResult = new SearchResultImpl(record);
		resultCartResult = new ResultCartImpl();
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
		sectionResultCart.addItem(resultCartResult);
		sectionStack.addSection(sectionResultCart);
		
		

		this.setMembers(sectionStack);

		/*
		 * register listener
		 */
		SearchEventManager.getInstance().addSearchResultSelectedListener(this);
		SearchEventManager.getInstance().addSearchResultConfirmedListener(this);
		SearchEventManager.getInstance().addSearchUpdateListener(this);

		SC.logWarn("======> ResultPortImpl.CTOR end");

		
		// this.setOverflow(Overflow.HIDDEN);

		// suggestFeedback = new SuggestFeedbackImpl(record);
		// suggestFeedback.setHeight("15%");
		//
		// searchResult = new SearchResultImpl(record);
		// searchResult.setHeight("70%");
		//
		// suggestFeedback.setShowResizeBar(true);
		//
		// SC.logWarn("======> ResultPortImpl.CTOR 1");
		//
		// resultCartResult = new ResultCartImpl();
		// resultCartResult.setHeight("15%");
		//
		// SC.logWarn("======> ResultPortImpl.CTOR 2");
		//
		// searchResult.setShowResizeBar(true);
		//
		// this.setMembers(suggestFeedback, searchResult, resultCartResult);
		// this.setMembers(suggestFeedback, searchResult);


	};

	@Override
	public void doAfterSearchResultSelected(Record resultRecord) {
		SC.logWarn("======> ResultPortImpl.doAfterSearchResultSelected");
		// TODO: update HyperTree
	}

	@Override
	public void doAfterSearchResultConfirmed(Record resultRecord) {
		// get SectionStackSection (field does not work)
		SectionStackSection sectionResultCart = sectionStack.getSection(2);
		
		SC.logWarn("======> ResultPortImpl.doAfterSearchResultConfirmed: isExpanded? " + sectionResultCart.getAttributeAsBoolean("expanded"));
		resultCartResult.addChoice(suggestFeedbackRecord, resultRecord);

		int cartCount = resultCartResult.getCartCount();
		SC.logWarn("======> ResultPortImpl.doAfterSearchResultConfirmed: cartCount? " + cartCount);
		// update section title
		sectionResultCart.setTitle(CART_TITLE + " (" + cartCount + ")");
		
		// expand on first cart item
		if (cartCount==1)
			sectionResultCart.setExpanded(true);
		
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
