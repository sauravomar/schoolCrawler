package com.edlogy.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * User: Saurav Date: Jun 2, 2015 Time: 3:18:55 PM
 */


public class PropertyReader {

  private static Properties edlogyProperties = null;
  
  public static Properties getPropertiesMap() {
	    if(edlogyProperties == null) {
	      // populate properties map
	      edlogyProperties = new Properties();
	      final InputStream inputStream = new PropertyReader().getClass().getClassLoader().getResourceAsStream("edlogy.properties");

	      try {
	        edlogyProperties.load(inputStream);
	      } catch(final IOException expIO) {
	        edlogyProperties = null;
	        System.err.println("Properties File *edlogyProperties.properties* not found" + expIO);
	      }
	    }
	    return edlogyProperties;
	  }

  public static synchronized String getPropertyValue(final String propertyName) {
    getPropertiesMap();
    if(edlogyProperties == null) {
      return null;
    }
    return edlogyProperties.getProperty(propertyName);

  }
  
  public static synchronized Integer getIntegerPropertyValue(final String propertyName){
	  String val = getPropertyValue(propertyName);
	  return Integer.parseInt(val);
  }
  
  public static synchronized Long getLongPropertyValue(final String propertyName){
	  String val = getPropertyValue(propertyName);
	  return Long.parseLong(val);
  }

  public static synchronized Boolean getBooleanPropertyValue(final String propertyName) {
	  String val = getPropertyValue(propertyName);
	  return Boolean.parseBoolean(val);
  }
  
  
  public static String getPropertyValue(final String propertyName, final String defaultValue) {
    getPropertiesMap();
    if(edlogyProperties == null) {
      return null;
    }
    if(edlogyProperties.getProperty(propertyName) != null) {
      return edlogyProperties.getProperty(propertyName);
    } else {
      return defaultValue;
    }

  }
}
