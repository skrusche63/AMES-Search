package de.kp.ames.search.client.event;

import com.smartgwt.client.data.Record;

public interface SearchResultConfirmedListener {
	public void doAfterResultRecordConfirmed(Record record);
}
