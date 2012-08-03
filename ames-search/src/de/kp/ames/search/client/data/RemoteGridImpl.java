package de.kp.ames.search.client.data;
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

import java.util.Map;

import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.data.RestDataSource;
import com.smartgwt.client.types.DSDataFormat;
import com.smartgwt.client.types.DSProtocol;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.events.DrawEvent;
import com.smartgwt.client.widgets.events.DrawHandler;
import com.smartgwt.client.widgets.grid.events.DataArrivedEvent;
import com.smartgwt.client.widgets.grid.events.DataArrivedHandler;

import de.kp.ames.search.client.globals.MethodConstants;
import de.kp.ames.search.client.method.RequestMethod;
import de.kp.ames.search.client.method.RequestMethodImpl;

public class RemoteGridImpl extends GridImpl {

	/*
	 * Reference to DataSource
	 */
	protected RestDataSource dataSource;
	
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
	

	/**
	 * Constructor
	 * 
	 * @param base
	 * @param sid
	 */
	public RemoteGridImpl(String base, String sid) {
		super();
		
		/*
		 * Register basic connection parameters
		 */
		this.base = base;
		this.sid  = sid;
		
		/*
		 * Page size support
		 */
		this.setDataPageSize(pageSize);

		/*
		 * Data handling
		 */
		this.setAutoFetchData(false);		
		this.setShowAllRecords(false);
		
		/*
		 * Event handling
		 */
		final RemoteGridImpl self = this;
		
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
		
	}
	
	/**
	 * Get request url
	 * 
	 * @return
	 */
	public String getRequestUrl() {
		
		if ((this.sid == null) || (this.base == null)) return null;
		return this.base + "/" + this.sid;
		
	}

	/**
	 * Create request method from attributes
	 * 
	 * @param attributes
	 * @return
	 */
	public RequestMethod createMethod() {

		RequestMethodImpl requestMethod = new RequestMethodImpl();
		requestMethod.setName(MethodConstants.METH_GET);
		
		//requestMethod.addAttribute(MethodConstants.ATTR_FORMAT, FormatConstants.FNC_FORMAT_ID_Grid);

		requestMethod.setAttributes(attributes);
		return requestMethod;
		
	}

	/**
	 * Retrieve request parameters
	 * 
	 * @return
	 */
	public Map<String,String> getRequestParams() {
		
		RequestMethod requestMethod = createMethod();
		return requestMethod.toParams();

	}

	/**
	 * Create Data source for remote access
	 * 
	 * @param attributes
	 */
	public void createScGridDS() {

		/*
		 * Retrieve request url
		 */
		String requestUrl = getRequestUrl();

		/*
		 * Retrieve request fields from attributes
		 */
		DataSourceField[] requestFields = createDataFields();

		dataSource = new RestDataSource() {
			  
			protected Object transformRequest(DSRequest dsRequest) {  
				SC.logWarn("====> RemoteGridImpl transformRequest 0");
				dsRequest.setParams(getRequestParams());
				SC.logWarn("====> RemoteGridImpl transformRequest1: " + dsRequest.getAttribute("query"));
				return super.transformRequest(dsRequest);  
			}  

			protected void transformResponse(DSResponse response, DSRequest request, Object data) { 
				super.transformResponse(response, request, data);  
			}  
			
		};
		
		dataSource.setDataFormat(DSDataFormat.JSON);
		dataSource.setDataProtocol(DSProtocol.GETPARAMS);  
		
		dataSource.setFetchDataURL(requestUrl);		
		dataSource.setFields(requestFields);
		
		/*
		 * finally set data source
		 */
		setDataSource(dataSource);

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
	 * Fetch data after rendering; this method MUST
	 * be overridden for local grids that get their
	 * data directly assigned.
	 * 
	 * @param event
	 */
	public void afterDraw(DrawEvent event) {		
		this.fetchData();
	}
	
}
