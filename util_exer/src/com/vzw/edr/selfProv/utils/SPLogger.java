package com.vzw.edr.selfProv.utils;

import java.util.Enumeration;
import java.util.Properties;

import org.apache.log4j.*;

/**
 * @author c0metej
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class SPLogger extends Logger {
	/** default logger name */
	private static String LOGGER_NAME = "com.vzw.edr.selfProv";

	private static Logger instance = null;
	private boolean inited = false;

	private SPLogger() {
		super(LOGGER_NAME);
		if (!inited) {
			Logger tL = Logger.getRootLogger();
			Enumeration en = tL.getAllAppenders();
			if (!en.hasMoreElements());
			{
				Properties logProperties = new Properties();
				logProperties.put("log4j.rootLogger", "DEBUG, A1");
				logProperties.put(
					"log4j.appender.A1",
					"org.apache.log4j.ConsoleAppender");
				logProperties.put(
					"log4j.appender.A1.layout",
					"org.apache.log4j.PatternLayout");
				logProperties.put(
					"log4j.appender.A1.layout.ConversionPattern",
					"%-4r [%t] %-5p %c %x - %m%n");
				PropertyConfigurator.configure(logProperties);
			}
		}
	}

	private static synchronized void configure(String propertiesFilePath) {
		if (instance == null) {
			//Refresh the properties file every 10 mins (36000)
			PropertyConfigurator.configureAndWatch(propertiesFilePath, 36000);

			//This will return a Logger with default logger name
			instance = Logger.getLogger(LOGGER_NAME);
			//instance = new SPLogger();

		}

	}
}
