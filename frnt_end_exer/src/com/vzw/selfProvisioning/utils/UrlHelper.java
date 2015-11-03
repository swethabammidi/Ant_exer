package com.vzw.selfProvisioning.utils;

/**
 * @author kachoro
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class UrlHelper {
	static private UrlHelper _instance = null;
	static public UrlHelper instance() {
		if (_instance == null) {
			_instance = new UrlHelper();
			return _instance;
		} else
			return _instance;
	}
	
	public static void setURLS() {
	
	}

}
