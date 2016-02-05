package com.example.camel.activemq;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
	
	public BasicDataSource getDb() {
		return db;
	}
	
	public void setDb(BasicDataSource db) {
		this.db = db;
	}

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

	public List<String> addDetectionRoutes() throws SQLException {
		
		final String METHOD_NAME = "addDetectionRoutes";
		LOGGER.info(CLASS_NAME + "." + METHOD_NAME + " - " + "ENTER");
		
		//Sample code for using the datasource
		Connection conn = db.getConnection();
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM dbtable");
		while (rs.next()) {
			LOGGER.info(rs.getString(1) + ":" + rs.getString(2));
			MSRouteBuilder route = new MSRouteBuilder(rs.getString(2), rs.getString(3));
			try {
				camelContext.addRoutes(route);
			} catch (Exception e) {
				LOGGER.error(CLASS_NAME + "." + METHOD_NAME + " - " + e.getMessage(), e);
			}
		}
		rs.close();
		st.close();
		
		List<String> dirList = this.getDirectories();
		String toUrl = this.getToURL();
		List<String> listRD = new ArrayList<String>();

/*		for(int i = 0; i < dirList.size(); i++) {
			MSRouteBuilder route = new MSRouteBuilder(dirList.get(i), toUrl, true);
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
*/
		return listRD;
	}
}
