package com.vzw.selfProvisioning;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Roy Kachouh
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public interface ActionInterface {
	
	
	
	
	public String doAction(HttpServletRequest req, HttpServletResponse resp) throws Exception;

}
