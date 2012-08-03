package de.kp.ames.search.client.widget;

import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.VisibilityMode;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;

import de.kp.ames.search.client.data.ResultCartImpl;
import de.kp.ames.search.client.event.SearchEventManager;
import de.kp.ames.search.client.event.SearchResultListener;
import de.kp.ames.search.client.event.SearchUpdateListener;
import de.kp.ames.search.client.layout.CenterportImpl;

public class ResultPortImpl extends CenterportImpl implements SearchUpdateListener, SearchResultListener {

	private SuggestFeedbackImpl suggestFeedback;
	private SearchResultImpl searchResult;
	private ResultCartImpl resultCartResult;

	private Record suggestFeedbackRecord;
	private SectionStackSection sectionResultCart;

	public ResultPortImpl(Record record) {
		super();

		// this.setOverflow(Overflow.HIDDEN);

		/*
		 * remember suggestion record
		 */
		this.suggestFeedbackRecord = record;

		suggestFeedback = new SuggestFeedbackImpl(record);
		suggestFeedback.setHeight("160");
		searchResult = new SearchResultImpl(record);
		resultCartResult = new ResultCartImpl();

		final SectionStack sectionStack = new SectionStack();
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

		sectionResultCart = new SectionStackSection("Cart");
		sectionResultCart.setExpanded(false);
		sectionResultCart.setCanCollapse(true);
		sectionResultCart.addItem(resultCartResult);
		sectionStack.addSection(sectionResultCart);

		this.setMembers(sectionStack);

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

		/*
		 * register listener
		 */
		SearchEventManager.getInstance().addSearchResultListener(this);
		SearchEventManager.getInstance().addSearchUpdateListener(this);

	};

	@Override
	public void doAfterSearchResultSelected(Record resultRecord) {
		SC.logWarn("======> ResultPortImpl.doAfterSearchResultSelected");
		resultCartResult.addChoice(suggestFeedbackRecord, resultRecord);
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
