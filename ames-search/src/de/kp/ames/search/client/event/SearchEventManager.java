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

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.util.SC;

public class SearchEventManager implements SearchResultConfirmedListener,
		SearchUpdateListener, SuggestListener, SimilarityListener, CheckoutListener, DownloadListener {

	private static SearchEventManager instance = new SearchEventManager();

	/*
	 * List of registered symbol listeners
	 */
//	private ArrayList<SearchResultSelectedListener> searchResultSelectedListener;
	private ArrayList<SearchResultConfirmedListener> searchResultConfirmedListener;
	private ArrayList<SearchUpdateListener> searchUpdateListener;
	private ArrayList<SuggestListener> suggestListener;
	private ArrayList<SimilarityListener> similarityListener;
	private ArrayList<CheckoutListener> checkoutListener;
	private ArrayList<DownloadListener> downloadListener;

	/**
	 * Constructor
	 */
	private SearchEventManager() {

		searchResultConfirmedListener = new ArrayList<SearchResultConfirmedListener>();
		suggestListener = new ArrayList<SuggestListener>();
		searchUpdateListener = new ArrayList<SearchUpdateListener>();
		similarityListener = new ArrayList<SimilarityListener>();
		checkoutListener = new ArrayList<CheckoutListener>();
		downloadListener = new ArrayList<DownloadListener>();

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


	public void addDownloadListener(DownloadListener listener) {
		downloadListener.add(listener);	
		
	}

	public void removeDownloadListener(DownloadListener listener) {
		downloadListener.remove(listener);
	}

	
	public void addCheckoutListener(CheckoutListener listener) {
		checkoutListener.add(listener);	
	}

	public void removeCheckoutListener(CheckoutListener listener) {
		checkoutListener.remove(listener);
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

	/*
	 * (non-Javadoc)
	 * @see de.kp.ames.search.client.event.SuggestListener#doAfterSuggest(com.smartgwt.client.data.Record)
	 */
	@Override
	public void doAfterSuggest(Record record) {

		SC.logWarn("======> SearchEventManager.doAfterSuggest # " + suggestListener.size());

		for (SuggestListener listener : suggestListener) {
			listener.doAfterSuggest(record);
		}
	}


	/*
	 * (non-Javadoc)
	 * @see de.kp.ames.search.client.event.SearchUpdateListener#doAfterSearchUpdate(com.smartgwt.client.data.Record)
	 */
	@Override
	public void doAfterSearchUpdate(Record record) {
		SC.logWarn("======> SearchEventManager.doAfterSearchResultSelected # " + searchUpdateListener.size());
		for (SearchUpdateListener listener : searchUpdateListener) {
			listener.doAfterSearchUpdate(record);
		}

	}

	/*
	 * (non-Javadoc)
	 * @see de.kp.ames.search.client.event.SearchResultConfirmedListener#doAfterSearchResultConfirmed(com.smartgwt.client.data.Record)
	 */
	@Override
	public void doAfterResultRecordConfirmed(Record record) {
		SC.logWarn("======> SearchEventManager.doAfterResultRecordConfirmed # " + searchResultConfirmedListener.size());
		for (SearchResultConfirmedListener listener : searchResultConfirmedListener) {
			listener.doAfterResultRecordConfirmed(record);
		}

	}

	/*
	 * (non-Javadoc)
	 * @see de.kp.ames.search.client.event.SimilarityListener#doShowSimilarity(com.google.gwt.json.client.JSONValue)
	 */
	@Override
	public void doShowSimilarity(JSONValue jValue) {
		SC.logWarn("======> SearchEventManager.doShowSimilarity # " + similarityListener.size());
		for (SimilarityListener listener : similarityListener) {
			listener.doShowSimilarity(jValue);
		}

	}

	/*
	 * (non-Javadoc)
	 * @see de.kp.ames.search.client.event.CheckoutListener#doAfterUpdate(com.google.gwt.json.client.JSONObject)
	 */
	@Override
	public void doAfterUpdate(JSONObject jObject) {
		SC.logWarn("======> SearchEventManager.doAfterUpdate # " + checkoutListener.size());
		for (CheckoutListener listener : checkoutListener) {
			listener.doAfterUpdate(jObject);
		}
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see de.kp.ames.search.client.event.DownloadListener#doTriggerDownload()
	 */
	@Override
	public void doTriggerDownload() {
		SC.logWarn("======> SearchEventManager.doTriggerDownload # " + downloadListener.size());
		for (DownloadListener listener : downloadListener) {
			listener.doTriggerDownload();
		}
		
	}




}
