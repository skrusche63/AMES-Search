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

import com.smartgwt.client.data.Record;
import com.smartgwt.client.util.SC;


public class SearchEventManager implements SearchResultListener, SearchUpdateListener, SuggestListener {

	private static SearchEventManager instance = new SearchEventManager();
	
	/*
	 * List of registered symbol listeners
	 */
	private ArrayList<SearchResultListener> searchResultListener;
	private ArrayList<SearchUpdateListener> searchUpdateListener;
	private ArrayList<SuggestListener> suggestListener;

	/**
	 * Constructor
	 */
	private SearchEventManager() {
		
		searchResultListener = new ArrayList<SearchResultListener>();
		suggestListener = new ArrayList<SuggestListener>();
		searchUpdateListener = new ArrayList<SearchUpdateListener>();
		
	}
	
	/**
	 * Retrieve singleton instance of SearchEventManager
	 * 
	 * @return
	 */
	public static SearchEventManager getInstance() {
		if (instance == null) instance = new SearchEventManager();
		return instance;
	}
	
	/**
	 * Register search listener
	 * 
	 * @param listener
	 */
	public void addSearchResultListener(SearchResultListener listener) {
		searchResultListener.add(listener);
	}
	
	/**
	 * Unregister search-result listener
	 * 
	 * @param listener
	 */
	public void removeSearchResultListener(SearchResultListener listener) {
		searchResultListener.remove(listener);
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
	

	@Override
	public void doAfterSuggest(Record record) {

		SC.logWarn("======> SearchEventManager.doAfterSuggest # " + suggestListener.size());
		
		for (SuggestListener listener:suggestListener) {
			listener.doAfterSuggest(record);
		}		
	}

	@Override
	public void doAfterSearchResultSelected(Record record) {
		SC.logWarn("======> SearchEventManager.doAfterSearchResultSelected # " + searchResultListener.size());
		for (SearchResultListener listener:searchResultListener) {
			listener.doAfterSearchResultSelected(record);
		}
		
	}

	@Override
	public void doAfterSearchUpdate(Record record) {
		SC.logWarn("======> SearchEventManager.doAfterSearchResultSelected # " + searchUpdateListener.size());
		for (SearchUpdateListener listener:searchUpdateListener) {
			listener.doAfterSearchUpdate(record);
		}
		
	}

}
