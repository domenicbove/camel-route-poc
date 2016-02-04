package com.example.camel.activemq;

import java.util.ArrayList;
import java.util.List;

import org.apache.camel.CamelContext;
import org.apache.commons.dbcp.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MSCamelRouteManager {
	private static final String CLASS_NAME = MSCamelRouteManager.class.getName();
	private static final Logger LOGGER = LoggerFactory.getLogger(CLASS_NAME);
	CamelContext camelContext;
	private List<String> directories;
	private String toURL;
	
	private BasicDataSource db;

	public List<String> getDirectories() {
		return directories;
	}

	public void setDirectories(List<String> directories) {
		this.directories = directories;
	}

	public String getToURL() {
		return toURL;
	}

	public void setToURL(String toURL) {
		this.toURL = toURL;
	}

	public CamelContext getCamelContext() {
		return camelContext;
	}

	public void setCamelContext(CamelContext camelContext) {
		this.camelContext = camelContext;
	}

	public List<String> addDetectionRoutes() {

		//		List<String> list = new ArrayList<String>();
		//		for (int i = 0; i < 25; i++) {
		//			list.add(String.valueOf(i));
		//		}
		//		this.setDirectories(list);
		/*
		* instead of doing above, can grab datasource bean and get map from it.
		* Might need to list DS as a property.
		*/
		
		final String METHOD_NAME = "addDetectionRoutes";
		LOGGER.info(CLASS_NAME + "." + METHOD_NAME + " - " + "ENTER");
		
		LOGGER.info(CLASS_NAME + ".DOMDOMDOM" + METHOD_NAME + db.getUrl());
		
		List<String> dirList = this.getDirectories();
		String toUrl = this.getToURL();
		List<String> listRD = new ArrayList<String>();

		for(int i = 0; i < dirList.size(); i++) {
			MSRouteBuilder route = new MSRouteBuilder(dirList.get(i), toUrl);
			try {
				camelContext.addRoutes(route);
			}
			catch (Exception e) {
				LOGGER.error(CLASS_NAME + "." + METHOD_NAME + " - " + e.getMessage(), e);
			}
			listRD.add(route.getRouteDefinition().getId());
		}

		LOGGER.info(CLASS_NAME + "." + METHOD_NAME + " - routes created: " + listRD.toArray(new String[0]));
		LOGGER.info(CLASS_NAME + "." + METHOD_NAME + " - " + "EXIT");

		return listRD;
	}
}
