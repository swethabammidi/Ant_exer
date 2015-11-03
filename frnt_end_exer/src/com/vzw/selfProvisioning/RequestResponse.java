package com.vzw.selfProvisioning;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.vzw.edr.selfProv.utils.SPProps;
import com.vzw.selfProvisioning.utils.Util;

import org.apache.log4j.Logger;


/**
 * @author kachoro
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class RequestResponse {
	private static Logger L = Logger.getLogger(SPProps.getFELogName(RequestResponse.class));		
	protected HttpServletRequest _req = null;
	protected HttpServletResponse _resp = null;
	private String _sourcePage = null;
	private String _destPage = null;
	private String mdn = null;
	private String userAgent = null;
	private String service = null;
	private HttpSession _session = null;
	
	

	/**
	 * Returns the _destPage.
	 * @return String
	 */
	public String get_destPage() {
		return _destPage;
	}

	/**
	 * Returns the _req.
	 * @return HttpServletRequest
	 */
	public HttpServletRequest get_req() {
		return _req;
	}

	/**
	 * Returns the _resp.
	 * @return HttpServletResponse
	 */
	public HttpServletResponse get_resp() {
		return _resp;
	}

	/**
	 * Returns the _sourcePage.
	 * @return String
	 */
	public String get_sourcePage() {
		return _sourcePage;
	}

	/**
	 * Sets the _destPage.
	 * @param _destPage The _destPage to set
	 */
	public void set_destPage(String _destPage) {
		this._destPage = _destPage;
	}

	/**
	 * Sets the _req.
	 * @param _req The _req to set
	 */
	public void set_req(HttpServletRequest _req) {
		this._req = _req;
	}

	/**
	 * Sets the _resp.
	 * @param _resp The _resp to set
	 */
	public void set_resp(HttpServletResponse _resp) {
		this._resp = _resp;
	}

	/**
	 * Sets the _sourcePage.
	 * @param _sourcePage The _sourcePage to set
	 */
	public void set_sourcePage(String _sourcePage) {
		this._sourcePage = _sourcePage;
	}
	
		/**
	 * This method is responsible for extracting the relevant elements from the http 
	 * header.  It will then set the element values as instance variables and return 
	 * control to the doAction() method.
	 */
	public void sniffHeader() {
		Enumeration headerNames = _req.getHeaderNames();

		while (headerNames.hasMoreElements()) {
			String headerName = (String) headerNames.nextElement();
			L.debug(headerName + " : " + _req.getHeader(headerName));
		}
		userAgent = _req.getHeader("user-agent");
		service = _req.getParameter(SPProps.servHeader);
		L.debug("service passed as parameter: " + service);
		L.debug("getCharacterEncoding: " + _req.getCharacterEncoding());
		L.debug("getContentLength: " + _req.getContentLength());
		L.debug("getContentType: " + _req.getContentType());
		L.debug("getProtocol: " + _req.getProtocol());
		L.debug("getRemoteAddr: " + _req.getRemoteAddr());
		L.debug("getRemoteHost: " + _req.getRemoteHost());
		L.debug("getScheme: " + _req.getScheme());
		L.debug("getServerName: " + _req.getServerName());
		L.debug("getServerPort: " + _req.getServerPort());
		L.debug("getAuthType: " + _req.getAuthType());
		L.debug("getMethod: " + _req.getMethod());
		L.debug("getPathInfo: " + _req.getPathInfo());
		L.debug("getPathTranslated: " + _req.getPathTranslated());
		L.debug("getQueryString: " + _req.getQueryString());
		L.debug("getRemoteUser: " + _req.getRemoteUser());
		L.debug("getRequestURI: " + _req.getRequestURI());
		L.debug("getServletPath: " + _req.getServletPath());
		
		L.debug ("SelfProvisioningServlet.sniffHeader() : Testing mode= :" + SPProps.testingMode + ":");
		int browserType = Util.getBrowserType(_req);
		L.debug ("SelfProvisioningServlet.sniffHeader() : Browser Type = " + browserType);
		if (SPProps.testingMode.trim().equalsIgnoreCase("true")) {
			mdn = _req.getParameter(SPProps.minHead2x);
			service = _req.getParameter(SPProps.servHeader);
			L.debug("Service in header: " + service);
			if((service == null) || service.equals(""))
			{
				if(browserType == Util.WAP1X)
					service = Util.CSP_SERVICE;
				else if (browserType == Util.WAP2X)
					service = Util.WSP_SERVICE;
				else if (browserType == Util.VCAST)
					service = SPProps.vzwServiceVCAST;	
			}
			L.info ("sniffHeader, testing mode is true. Extracting mdn using parameter SPProps.minHead2x("+ SPProps.minHead2x + "), mdn = " + mdn);
			L.info ("sniffHeader, testing mode is true. Extracting SERVICE   using parameter SPProps.servHeader("+ SPProps.servHeader + "), service = " + service);
		}
		else
		{
			if ((service == null) || service.equals(""))
			{
				service = _req.getHeader(SPProps.servHeader);
				if((service == null) || service.equals(""))
				{
				    // Check parameter for service.
					service = _req.getParameter(SPProps.servHeader);
				}
				if((service == null) || service.equals(""))
				{
					if(browserType == Util.WAP1X)
						service = Util.CSP_SERVICE;
					else if (browserType == Util.WAP2X)
						service = Util.WSP_SERVICE;
					else if (browserType == Util.VCAST)
						service = SPProps.vzwServiceVCAST;	
				}
			}	
			
			if(browserType == Util.WAP1X)
			{
				mdn = _req.getHeader(SPProps.minHead1x);
				L.info ("sniffHeader, testing mode is false. Extracting mdn using request header SPProps.minHead1x("+ SPProps.minHead1x + "), mdn = " + mdn);
			}
			else if(browserType == Util.WAP2X || browserType == Util.VCAST)
			{	
				mdn = _req.getHeader(SPProps.minHead2x);
				L.info ("sniffHeader, testing mode is false. Extracting mdn using request header SPProps.minHead2x("+ SPProps.minHead2x + "), mdn = " + mdn);
			}
			else				
			{
				mdn = null;
				L.warn ("sniffHeader, testing mode is false and browserType (" + service  + ") unknown. mdn = " + mdn);
			}
		}
		L.debug("SelfProvisioningServlet.sniffHeader() : Succesfully sniffed headers, MDN: " + mdn);
		SPProps.infoLogger.info(
			"SelfProvisioningServlet.sniffHeader() : " +
			"Succesfully sniffed headers, MDN: " + mdn);
	}

	/**
	 * Returns the mdn.
	 * @return String
	 */
	public String getMdn() {
		return mdn;
	}

	/**
	 * Returns the mdn.
	 * @return String
	 */
	public String getService() {
		return service;
	}

	/**
	 * Sets the mdn.
	 * @param mdn The mdn to set
	 */
	public void setMdn(String mdn) {
		this.mdn = mdn;
	}

	/**
	 * Returns the _session.
	 * @return HttpSession
	 */
	public HttpSession get_session() {
		HttpSession session = _req.getSession(true);
		if(session.isNew()) {
			L.debug("Found a new session in queryString: "+_req.getQueryString());
			L.debug("Just created a new session with the id: "+session.getId());
		}
	
		return session;
	}

	/**
	 * Sets the _session.
	 * @param _session The _session to set
	 */
	public void set_session(HttpSession _session) {
		this._session = _session;
	}
	
	/*public void showCookies() {
		Cookie[] cookies = _req.getCookies();
		Cookie cookie;
		L.debug("Printing all cookies...");
		for(int i=0; i<cookies.length;i++){
			cookie = cookies[i];
			L.debug("Cookie name: "+cookie.getName());
			L.debug("Cookie value: "+cookie.getValue());
			L.debug("Cookie Domain: "+cookie.getDomain());
			L.debug("Cookie path: "+cookie.getPath());
		}
	}*/
	public void showCookies() {}
	
	/**
	 * @return
	 */
	public String getUserAgent() {
		return userAgent;
	}

}
