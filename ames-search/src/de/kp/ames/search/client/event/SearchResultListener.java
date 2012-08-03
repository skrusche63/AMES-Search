package de.kp.ames.search.client.event;

import com.smartgwt.client.data.Record;

public interface SearchResultListener {
	public void doAfterSearchResultSelected(Record record);

}
