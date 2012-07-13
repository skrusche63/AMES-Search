package de.kp.ames.search.client.widget.grid;

import java.util.Map;

import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.JSONCircularReferenceMode;

import de.kp.ames.search.client.globals.GuiGlobals;
import de.kp.ames.search.client.globals.JsonConstants;
import de.kp.ames.search.client.globals.MethodConstants;
import de.kp.ames.search.client.method.RequestMethod;
import de.kp.ames.search.client.method.RequestMethodImpl;
import de.kp.ames.search.client.model.DataObject;
import de.kp.ames.search.client.model.ResultObject;

public class SearchResultGridImpl extends GridImpl {

	public SearchResultGridImpl(Record record) {
		super(GuiGlobals.SEARCH_URL, "search");

		/*
		 * No border style
		 */
		this.setBorder("0");

		this.setHeight100();
		this.setWidth100();

		setSearchData(record);

		/*
		 * Create data object
		 */
		this.dataObject = createDataObject();

		/*
		 * Create data source
		 */
		this.createScGridDS();

		/*
		 * Create grid fields
		 */
		this.setFields(createGridFields());

		this.setShowHeader(false);

	}

	public void setSearchData(Record record) {
		String queryString = record.getAttributeAsString(JsonConstants.J_QUERYSTRING);
		attributes.put(MethodConstants.ATTR_QUERY, queryString);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.kp.ames.search.client.widget.grid.GridImpl#getRequestParams()
	 */
	public Map<String, String> getRequestParams() {

		/*
		 * Update attributes
		 */
//		attributes.put(JsonConstants.J_QUERY, this.query);

		RequestMethod requestMethod = createMethod();
		return requestMethod.toParams();

	}

	/**
	 * @return
	 */
	private DataObject createDataObject() {
		return new ResultObject();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.kp.ames.search.client.widget.grid.GridImpl#createMethod()
	 */
	public RequestMethod createMethod() {

		RequestMethodImpl requestMethod = new RequestMethodImpl();
		requestMethod.setName(MethodConstants.METH_SEARCH);

		requestMethod.setAttributes(attributes);
		return requestMethod;

	}
}
