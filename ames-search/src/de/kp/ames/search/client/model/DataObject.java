package de.kp.ames.search.client.model;

import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.widgets.grid.ListGridField;

public interface DataObject {

	/**
	 * @return
	 */
	public DataSourceField[] createDataFields();

	/**
	 * @return
	 */
	public ListGridField[] createGridFields();

}
