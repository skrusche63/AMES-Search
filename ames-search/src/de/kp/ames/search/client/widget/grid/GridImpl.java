package de.kp.ames.search.client.widget.grid;
/**
 * Copyright 2012. All rights reserved by Dr. Krusche & Partner PartG
 * Please contact: team@dr-kruscheundpartner.de
 */

import java.util.HashMap;
import java.util.Map;

import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.types.DSDataFormat;
import com.smartgwt.client.types.DSProtocol;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.events.DrawEvent;
import com.smartgwt.client.widgets.events.DrawHandler;
import com.smartgwt.client.widgets.events.KeyDownEvent;
import com.smartgwt.client.widgets.events.KeyDownHandler;
import com.smartgwt.client.widgets.events.KeyPressEvent;
import com.smartgwt.client.widgets.events.KeyPressHandler;
import com.smartgwt.client.widgets.events.MouseDownEvent;
import com.smartgwt.client.widgets.events.MouseDownHandler;
import com.smartgwt.client.widgets.events.RightMouseDownEvent;
import com.smartgwt.client.widgets.events.RightMouseDownHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.events.BodyKeyPressEvent;
import com.smartgwt.client.widgets.grid.events.CellClickEvent;
import com.smartgwt.client.widgets.grid.events.CellClickHandler;
import com.smartgwt.client.widgets.grid.events.DataArrivedEvent;
import com.smartgwt.client.widgets.grid.events.DataArrivedHandler;
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;
import com.smartgwt.client.widgets.grid.events.RecordDoubleClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordDoubleClickHandler;
import com.smartgwt.client.widgets.grid.events.RowContextClickEvent;
import com.smartgwt.client.widgets.grid.events.RowContextClickHandler;
import com.smartgwt.client.widgets.grid.events.SelectionChangedHandler;
import com.smartgwt.client.widgets.grid.events.SelectionEvent;

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
	 * Reference to request parameters
	 */
	protected HashMap<String,String> attributes;
	
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
		 * Initiate request parameters
		 */
		attributes = new HashMap<String,String>();
		
		/*
		 * Dimensions
		 */		
		this.setWidth100();
		this.setHeight100();
		
		/*
		 * Data handling
		 */
		this.setAutoFetchData(false);		
		this.setShowAllRecords(true);
		
		this.setSelectionType(SelectionStyle.SINGLE);
		
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
		
//		this.addRecordClickHandler(new RecordClickHandler() {
//			public void onRecordClick(RecordClickEvent event) {
//				self.afterRecordClick(event);				
//			}
//		});
//
//		this.addCellClickHandler(new CellClickHandler() {
//			public void onCellClick(CellClickEvent event) {
//				self.afterCellClick(event);
//			}
//		});
//			
//		
//        this.addRecordDoubleClickHandler(new RecordDoubleClickHandler() {  
//            public void onRecordDoubleClick(RecordDoubleClickEvent event) {  
//                self.afterRecordDoubleClick(event);  
//            }  
//        });  
        
        this.addSelectionChangedHandler(new SelectionChangedHandler() {
			
			@Override
			public void onSelectionChanged(SelectionEvent event) {
				self.afterSelectionChanged(event);  
				
			}
		});

//        this.addKeyPressHandler(new KeyPressHandler() {
//			public void onKeyPress(KeyPressEvent event) {
//				self.afterKeyPress(event);	
//			}
//		}); 
//        
        this.addKeyDownHandler(new KeyDownHandler() {
			
			@Override
			public void onKeyDown(KeyDownEvent event) {
				self.afterKeyDown(event);
			}
		}); 
//
//        this.addRightMouseDownHandler(new RightMouseDownHandler() {
//			
//			@Override
//			public void onRightMouseDown(RightMouseDownEvent event) {
//				self.afterRightMouseDown(event);
//				
//			}
//		});
//        
//        this.addMouseDownHandler(new MouseDownHandler() {
//			
//			@Override
//			public void onMouseDown(MouseDownEvent event) {
//				self.afterMouseDown(event);
//				
//			}
//		});
//        
//        this.addRowContextClickHandler(new RowContextClickHandler() {
//			
//			@Override
//			public void onRowContextClick(RowContextClickEvent event) {
//				self.afterRowContextClick(event);				
//			}
//		});
        
        /*
         * have a bug in v3.0 and blocks native navigation
         * nightys 3.0d or 3.1 are fixed  
         */
//        this.addBodyKeyPressHandler(new BodyKeyPressHandler() {
//			
//			@Override
//			public void onBodyKeyPress(BodyKeyPressEvent event) {
//				self.afterBodyKeyPress(event);
//			}
//		});
        
	}

	public void afterSelectionChanged(SelectionEvent event) {
		/*
		 * Must be overridden
		 */
	}

	public void afterRowContextClick(RowContextClickEvent event) {
		/*
		 * Must be overridden
		 */
	}

	public void afterMouseDown(MouseDownEvent event) {
		/*
		 * Must be overridden
		 */
	}

	public void afterRightMouseDown(RightMouseDownEvent event) {
		/*
		 * Must be overridden
		 */
	}

	/* (non-Javadoc)
	 * @see de.kp.ames.search.client.core.grid.Grid#afterRecordDoubleClick(com.smartgwt.client.widgets.grid.events.RecordDoubleClickEvent)
	 */
	public void afterRecordDoubleClick(RecordDoubleClickEvent event) {
		/*
		 * Must be overridden
		 */
	}
	
	
	public void afterBodyKeyPress(BodyKeyPressEvent event) {
		/*
		 * Must be overridden
		 */
	}
	public void afterCellClick(CellClickEvent event) {
		/*
		 * Must be overridden
		 */
	}

	public void afterKeyPress(KeyPressEvent event) {
		/*
		 * Must be overridden
		 */
	}

	public void afterKeyDown(KeyDownEvent event) {
		/*
		 * Must be overridden
		 */
	}

	/* (non-Javadoc)
	 * @see de.kp.ames.search.client.widget.grid.Grid#getRequestParams()
	 */
	public Map<String, String> getRequestParams() {
		// TODO Auto-generated method stub
		return null;
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
	 * @see de.kp.ames.search.client.widget.grid.Grid#reload(java.util.HashMap)
	 */
	public void reload(HashMap<String,String> attributes) {

		attributes.putAll(attributes);
		reload();
	
	}
	
	/* (non-Javadoc)
	 * @see de.kp.ames.search.client.core.grid.BaseGrid#getRequestUrl()
	 */
	public String getRequestUrl() {
		
		if ((this.sid == null) || (this.base == null)) return null;
		return this.base + "/" + this.sid;
		
	}

	/* (non-Javadoc)
	 * @see de.kp.ames.search.client.widget.grid.Grid#createDataFields()
	 */
	public DataSourceField[] createDataFields() {
		return this.dataObject.createDataFields();
	}

	/* (non-Javadoc)
	 * @see de.kp.ames.search.client.widget.grid.Grid#createGridFields()
	 */
	public ListGridField[] createGridFields() {
		return this.dataObject.createGridFields();
	}

	/* (non-Javadoc)
	 * @see de.kp.ames.search.client.widget.grid.Grid#createMethod()
	 */
	public RequestMethod createMethod() {

		// must be overridden
		return null;
		
	}

	/* (non-Javadoc)
	 * @see de.kp.ames.search.client.core.grid.BaseGrid#createScGridDS()
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
		
		/*
		 * Finally create data source
		 */

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
		
		dataSource.setDataURL(requestUrl);		
		dataSource.setFields(requestFields);

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
	
}
