package de.kp.ames.search.client.event;

/**
 *	Copyright 2012 Dr. Krusche & Partner PartG
 *
 *	AMES-Web-GUI is free software: you can redistribute it and/or 
 *	modify it under the terms of the GNU General Public License 
 *	as published by the Free Software Foundation, either version 3 of 
 *	the License, or (at your option) any later version.
 *
 *	AMES- Web-GUI is distributed in the hope that it will be useful,
 *	but WITHOUT ANY WARRANTY; without even the implied warranty of
 *	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. 
 * 
 *  See the GNU General Public License for more details. 
 *
 *	You should have received a copy of the GNU General Public License
 *	along with this software. If not, see <http://www.gnu.org/licenses/>.
 *
 */

import java.util.ArrayList;

import com.google.gwt.json.client.JSONValue;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.util.SC;

public class SearchEventManager implements SearchResultSelectedListener, SearchResultConfirmedListener,
		SearchUpdateListener, SuggestListener, SimilarityListener {

	private static SearchEventManager instance = new SearchEventManager();

	/*
	 * List of registered symbol listeners
	 */
	private ArrayList<SearchResultSelectedListener> searchResultSelectedListener;
	private ArrayList<SearchResultConfirmedListener> searchResultConfirmedListener;
	private ArrayList<SearchUpdateListener> searchUpdateListener;
	private ArrayList<SuggestListener> suggestListener;
	private ArrayList<SimilarityListener> similarityListener;

	/**
	 * Constructor
	 */
	private SearchEventManager() {

		searchResultSelectedListener = new ArrayList<SearchResultSelectedListener>();
		searchResultConfirmedListener = new ArrayList<SearchResultConfirmedListener>();
		suggestListener = new ArrayList<SuggestListener>();
		searchUpdateListener = new ArrayList<SearchUpdateListener>();
		similarityListener = new ArrayList<SimilarityListener>();

	}

	/**
	 * Retrieve singleton instance of SearchEventManager
	 * 
	 * @return
	 */
	public static SearchEventManager getInstance() {
		if (instance == null)
			instance = new SearchEventManager();
		return instance;
	}

	/**
	 * Register search result selected listener
	 * 
	 * @param listener
	 */
	public void addSearchResultSelectedListener(SearchResultSelectedListener listener) {
		searchResultSelectedListener.add(listener);
	}

	/**
	 * Unregister search-result selected listener
	 * 
	 * @param listener
	 */
	public void removeSearchResultSelectedListener(SearchResultSelectedListener listener) {
		searchResultSelectedListener.remove(listener);
	}

	/**
	 * Register search result confirmed listener
	 * 
	 * @param listener
	 */
	public void addSearchResultConfirmedListener(SearchResultConfirmedListener listener) {
		searchResultConfirmedListener.add(listener);
	}

	/**
	 * Unregister search-result confirmed listener
	 * 
	 * @param listener
	 */
	public void removeSearchResultConfirmedListener(SearchResultConfirmedListener listener) {
		searchResultConfirmedListener.remove(listener);
	}

	/**
	 * Register search update listener
	 * 
	 * @param listener
	 */
	public void addSearchUpdateListener(SearchUpdateListener listener) {
		searchUpdateListener.add(listener);
	}

	/**
	 * Unregister search-result listener
	 * 
	 * @param listener
	 */
	public void removeSearchUpdateListener(SearchUpdateListener listener) {
		searchUpdateListener.remove(listener);
	}

	/**
	 * Register suggest-result listener
	 * 
	 * @param listener
	 */
	public void addSuggestListener(SuggestListener listener) {
		suggestListener.add(listener);
	}

	/**
	 * Unregister suggest listener
	 * 
	 * @param listener
	 */
	public void removeSuggestListener(SuggestListener listener) {
		suggestListener.remove(listener);
	}

	/**
	 * Register similarity listener
	 * 
	 * @param listener
	 */
	public void addSimilarityListener(SimilarityListener listener) {
		similarityListener.add(listener);
	}

	/**
	 * Unregister similarity listener
	 * 
	 * @param listener
	 */
	public void removeSimilarityListener(SimilarityListener listener) {
		similarityListener.remove(listener);
	}

	@Override
	public void doAfterSuggest(Record record) {

		SC.logWarn("======> SearchEventManager.doAfterSuggest # " + suggestListener.size());

		for (SuggestListener listener : suggestListener) {
			listener.doAfterSuggest(record);
		}
	}

	@Override
	public void doAfterSearchResultSelected(Record record) {
		SC.logWarn("======> SearchEventManager.doAfterSearchResultSelected # " + searchResultSelectedListener.size());
		for (SearchResultSelectedListener listener : searchResultSelectedListener) {
			listener.doAfterSearchResultSelected(record);
		}

	}

	@Override
	public void doAfterSearchUpdate(Record record) {
		SC.logWarn("======> SearchEventManager.doAfterSearchResultSelected # " + searchUpdateListener.size());
		for (SearchUpdateListener listener : searchUpdateListener) {
			listener.doAfterSearchUpdate(record);
		}

	}

	@Override
	public void doAfterSearchResultConfirmed(Record record) {
		SC.logWarn("======> SearchEventManager.doAfterSearchResultConfirmed # " + searchResultConfirmedListener.size());
		for (SearchResultConfirmedListener listener : searchResultConfirmedListener) {
			listener.doAfterSearchResultConfirmed(record);
		}

	}

	@Override
	public void doShowSimilarity(JSONValue jValue) {
		SC.logWarn("======> SearchEventManager.doShowSimilarity # " + similarityListener.size());
		for (SimilarityListener listener : similarityListener) {
			listener.doShowSimilarity(jValue);
		}

	}

}
