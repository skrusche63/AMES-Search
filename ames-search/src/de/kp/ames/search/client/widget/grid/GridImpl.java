package de.kp.ames.search.client.widget.grid;
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

import java.util.HashMap;
import java.util.Map;

import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.types.DSDataFormat;
import com.smartgwt.client.types.DSProtocol;
import com.smartgwt.client.types.ExpansionMode;
import com.smartgwt.client.widgets.events.DrawEvent;
import com.smartgwt.client.widgets.events.DrawHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.events.DataArrivedEvent;
import com.smartgwt.client.widgets.grid.events.DataArrivedHandler;
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;

import de.kp.ames.search.client.globals.JsonConstants;
import de.kp.ames.search.client.handler.GridRecordHandler;
import de.kp.ames.search.client.method.RequestMethod;
import de.kp.ames.search.client.model.DataObject;


public class GridImpl extends ListGrid implements Grid {
	
	/*
	 * Reference to DataObject
	 */
	protected DataObject dataObject;

	/*
	 * Reference to DataSource
	 */
	protected DataSource dataSource;

	/*
	 * Reference to RecordHandler
	 */
	protected GridRecordHandler recordHandler;
	
	/*
	 * The base url necessary to invoke the
	 * web service that refers to this service
	 */	
	protected String base;
	
	/*
	 * The unique service identifier
	 */
	protected String sid;

	/*
	 * Page size
	 */
	protected int pageSize = 25;
	
	/*
	 * Reference to name of detail field
	 */
	String detailFieldName;
	

	/**
	 * Constructor
	 * 
	 * @param base
	 * @param sid
	 */
	public GridImpl(String base, String sid) {

		/*
		 * Register basic connection parameters
		 */
		this.base = base;
		this.sid  = sid;
		
		
		/*
		 * Dimensions
		 */		
		this.setWidth100();
		this.setHeight100();

		/*
		 * Page size support
		 */
//		this.setDataPageSize(pageSize);
		
		/*
		 * List entry may be expanded on click
		 */
		String detailFieldName = getDetailFieldName();

		if (detailFieldName != null) {

			this.setCanExpandRecords(true);
			this.setExpansionMode(ExpansionMode.DETAIL_FIELD);

			this.setDetailField(detailFieldName);
			
		}

		/*
		 * Data handling
		 */
		this.setAutoFetchData(false);		
		this.setShowAllRecords(true);
		
		// http://www.smartclient.com/smartgwt/showcase/#grid_autofit_values
		this.setWrapCells(true);  
        this.setFixedRecordHeights(false); 

		/*
		 * Event handling
		 */
		final GridImpl self = this;
		
		this.addDataArrivedHandler(new DataArrivedHandler() {
			public void onDataArrived(DataArrivedEvent event) {								
		    	self.afterDataArrived(event);
			}			
		});
		
		this.addDrawHandler(new DrawHandler() {
			public void onDraw(DrawEvent event) {
				self.afterDraw(event);				
			}			
		});
		
		this.addRecordClickHandler(new RecordClickHandler() {
			public void onRecordClick(RecordClickEvent event) {
				self.afterRecordClick(event);				
			}
			
		});
		

	}

	
	/* (non-Javadoc)
	 * @see de.kp.ames.search.client.core.grid.Grid#addRecordHandler(de.kp.ames.search.client.handler.GridRecordHandler)
	 */
	public void addRecordHandler(GridRecordHandler recordHandler) {
		/*
		 * Set Record Handler and register grid
		 * for later processing
		 */
		this.recordHandler = recordHandler;
		this.recordHandler.setGrid(this);
		
	}
	
	/* (non-Javadoc)
	 * @see de.kp.ames.search.client.core.grid.Grid#getDetailFieldName()
	 */
	public String getDetailFieldName() {
		return JsonConstants.J_DESC;
	}
	
	/* (non-Javadoc)
	 * @see de.kp.ames.search.client.core.grid.Grid#reload()
	 */
	public void reload() {
		
		/* 
		 * REMARK:
		 * 
		 * To invalidate the cache is essential to 
		 * retrieve data from the server again
		 */
		this.invalidateCache();
		this.redraw();

	}
	
	/* (non-Javadoc)
	 * @see de.kp.ames.search.client.core.grid.BaseGrid#getRequestUrl()
	 */
	public String getRequestUrl() {
		
		if ((this.sid == null) || (this.base == null)) return null;
		return this.base + "/" + this.sid;
		
	}

	/* (non-Javadoc)
	 * @see de.kp.ames.search.client.core.grid.GridImpl#createFields(java.util.HashMap)
	 */
	public DataSourceField[] createDataFields(HashMap<String,String> attributes) {
		return this.dataObject.createDataFields();
	}

	/* (non-Javadoc)
	 * @see de.kp.ames.search.client.core.grid.GridImpl#createGridFields(java.util.HashMap)
	 */
	public ListGridField[] createGridFields(HashMap<String,String> attributes) {
		return this.dataObject.createGridFields();
	}

	/* (non-Javadoc)
	 * @see de.kp.ames.search.client.core.grid.BaseGrid#createMethod(java.util.HashMap)
	 */
	public RequestMethod createMethod(HashMap<String,String> attributes) {

		// must be overridden
		return null;
		
	}

	/* (non-Javadoc)
	 * @see de.kp.ames.search.client.core.grid.BaseGrid#createScGridDS()
	 */
	public void createScGridDS(HashMap<String,String> attributes) {
		/*
		 * Retrieve request url
		 */
		String requestUrl = getRequestUrl();
		
		
		/*
		 * Retrieve request fields from attributes
		 */
		DataSourceField[] requestFields = createDataFields(attributes);
		
		/*
		 * Finally create data source
		 */
		createScGridDS(requestUrl, requestFields);
		
	}

	/* (non-Javadoc)
	 * @see de.kp.ames.search.client.core.gui.grid.BaseGrid#createScGridDS(java.lang.String, de.kp.ames.search.client.core.method.RequestMethod, com.smartgwt.client.data.DataSourceField[])
	 */
	public void createScGridDS(final String url, final DataSourceField[] fields) {
		
		dataSource = new DataSource() {
			  
			protected Object transformRequest(DSRequest dsRequest) {  
				dsRequest.setParams(getRequestParams());				
				return super.transformRequest(dsRequest);  
			}  

			protected void transformResponse(DSResponse response, DSRequest request, Object data) {  
				super.transformResponse(response, request, data);  
			}  
			
		};
		
		dataSource.setDataFormat(DSDataFormat.JSON);
		dataSource.setDataProtocol(DSProtocol.GETPARAMS);  
		
		dataSource.setDataURL(url);		
		dataSource.setFields(fields);

		/*
		 * finally set data source
		 */
		setDataSource(dataSource);

	}

	/* (non-Javadoc)
	 * @see de.kp.ames.search.client.core.grid.Grid#afterRecordClick(com.smartgwt.client.widgets.grid.events.RecordClickEvent)
	 */
	public void afterRecordClick(RecordClickEvent event) {
		/*
		 * Must be overridden
		 */
	}
	
	/**
	 * @param event
	 */
	public void afterDataArrived(DataArrivedEvent event) {
		/*
		 * Must be overridden
		 */
	}
	
	/**
	 * Fetch data after rendering
	 * 
	 * @param event
	 */
	public void afterDraw(DrawEvent event) {
		this.fetchData();
	}


	@Override
	public void createScGridDS(String url, RequestMethod method, DataSourceField[] fields) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public Map<String, String> getRequestParams() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
