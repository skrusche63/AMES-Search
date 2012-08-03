package de.kp.ames.search.client.data;

import java.util.HashMap;

import com.google.gwt.json.client.JSONArray;
import com.smartgwt.client.util.SC;

import de.kp.ames.search.client.model.DataObject;
import de.kp.ames.search.client.model.ResultCartObject;


public class ResultCardGridImpl extends LocalGridImpl {

	public ResultCardGridImpl() {
		super();

		SC.logWarn("==========> ResultCardGridImpl.CTOR 1");

        /*
		 * Register data
		 */
		attributes = new HashMap<String,String>();

		/*
		 * Create data object
		 */
		this.dataObject = createDataObject();
		SC.logWarn("==========> ResultCardGridImpl.CTOR 2");
				
		/*
		 * Create grid fields
		 */
		this.setFields(createGridFields());
		SC.logWarn("==========> ResultCardGridImpl.CTOR 3");

		/*
		 * Create Grid Data
		 */
		// there are none initially
//		this.setData(createGridRecords());
//		SC.logWarn("==========> ResultCardGridImpl.CTOR 4");


	}

	@Override
	public String getDetailFieldName() {
		return null;
	}

	@Override
	public JSONArray getGridData() {
		/*
		 * TODO: 
		 */
		return null;
	}

	
	/**
	 * @return
	 */
	private DataObject createDataObject() {
		return new ResultCartObject();
	}

	
}
