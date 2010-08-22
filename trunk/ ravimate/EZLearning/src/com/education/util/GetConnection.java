/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.education.util;

/**
 *
 * @author Administrator
 */
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

import javax.naming.*;
import javax.sql.*;

import org.apache.commons.beanutils.PropertyUtils;
import org.expframework.AppConfig;
import org.expframework.exceptions.BaseAppException;

import com.education.exceptions.DBDataSourceException;
import com.education.exceptions.DBInvalidDBDriverException;

public final class GetConnection {

  /** Uses JNDI and Datasource (preferred style).   */
  static Connection getJNDIConnection(){
    String DATASOURCE_CONTEXT = "java:comp/env/jdbc/blah";
    
    Connection result = null;
    try {
      Context initialContext = new InitialContext();
      if ( initialContext == null){
        log("JNDI problem. Cannot get InitialContext.");
      }
      DataSource datasource = (DataSource)initialContext.lookup(DATASOURCE_CONTEXT);
      if (datasource != null) {
        result = datasource.getConnection();
      }
      else {
        log("Failed to lookup datasource.");
      }
    }
    catch ( NamingException ex ) {
      log("Cannot get connection: " + ex);
    }
    catch(SQLException ex){
      log("Cannot get connection: " + ex);
    }
    return result;
  }

  /** Uses DriverManager.  */
  public static Connection getSimpleConnection() throws BaseAppException{
	  
//	 InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("app.properties");
	 String DB_CONN_STRING = null; 
	 String DRIVER_CLASS_NAME = null;
	 String USER_NAME = null;
	 String PASSWORD = null;
	 try
	 {
		 
	AppConfig config = AppConfig.getInstance();	 
//	 Properties prop = new Properties();
//	 prop.load(is);
	  
    //See your driver documentation for the proper format of this string :
    DB_CONN_STRING = config.get("connstring");
    //Provided by your driver documentation. In this case, a MySql driver is used : 
    DRIVER_CLASS_NAME = config.get("driverClassName");
     USER_NAME = config.get("userName");
     PASSWORD = config.get("password");
	 }
	 catch (Exception e) {
		// TODO: handle exception
		 throw new BaseAppException("Cannot Load Drivers");
	}
    
    Connection result = null;
    try {
       Class.forName(DRIVER_CLASS_NAME).newInstance();
    }catch (InstantiationException e){
    	throw new DBInvalidDBDriverException(e.getMessage());
	} catch (IllegalAccessException e) {
		throw new DBInvalidDBDriverException(e.getMessage());
	} catch (ClassNotFoundException e) {
		throw new DBInvalidDBDriverException(e.getMessage());
	}

    try {
      result = DriverManager.getConnection(DB_CONN_STRING, USER_NAME, PASSWORD);
    }catch(SQLException e){
    	throw new DBDataSourceException(e.getMessage());
    }
    return result;
  }

  private static void log(Object aObject){
    System.out.println(aObject);
  }
}
