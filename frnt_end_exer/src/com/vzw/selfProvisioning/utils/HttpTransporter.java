package com.vzw.selfProvisioning.utils;

import java.io.*;
import java.util.*;


import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.vzw.edr.selfProv.utils.SPProps;

/**
 * @author kasarsri
 * extended by kachoro
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class HttpTransporter {
	private static Logger L = Logger.getLogger(SPProps.getFELogName(HttpTransporter.class));
	private String respCode = null;
	private String _urlPath = null;
	private String _dataToBeSent = null;
	private HashMap _nameValuePairs = null;
	private boolean _nameValue = false;
	public static final String HEAD = "SPVRespRec";
	public static final String RESPONSE_CODE = "resp_code";
	public static final String REQ_EVENT_ID = "req_event_id";
	public static final String RESP_EVENT_ID = "resp_event_id";
	public static final String RESP_DESC = "resp_description";
	private HashMap _hmReqData;

	private java.net.HttpURLConnection uConnection;
	private String inputLine;

	public HttpTransporter(String urlPath, String xmlFile) {

		set_urlPath(urlPath);
		set_dataToBeSent(xmlFile);

	}
	public HttpTransporter(String urlPath, HashMap nvHashMap) {

		set_urlPath(urlPath);
		set_nameValue(true);

		Set s = nvHashMap.entrySet();

		StringBuffer nameValuePairs = null;

		//Iterate through the HashMap and form a String

		Iterator iter = s.iterator();
		if (iter.hasNext()) {
			nameValuePairs = new StringBuffer();
			Map.Entry mapentry = (Map.Entry) iter.next();
			String key = ((String) mapentry.getKey());
			String value = (String) mapentry.getValue();
			nameValuePairs.append(key).append("=").append(value);

		}
		while (iter.hasNext()) {
			Map.Entry mapentry = (Map.Entry) iter.next();
			String key = ((String) mapentry.getKey());
			String value = (String) mapentry.getValue();
			nameValuePairs.append("&").append(key).append("=").append(value);

		};

		set_dataToBeSent(nameValuePairs.toString());

	}

	public String sendXMLMessage() throws Exception {
		L.debug ("SelfProvisioningServlet.sendXMLMessage() : Attempting to send the xml message to "+_urlPath);
		java.net.URL url = new java.net.URL(_urlPath);

		int cntLen = _dataToBeSent.length();
		uConnection = (java.net.HttpURLConnection) url.openConnection();
		uConnection.setRequestProperty("accept", "*/*");
		uConnection.setRequestProperty("accept-language", "en-us");
		uConnection.setRequestProperty("content-type", "text/xml");
		uConnection.setRequestProperty("user-agent", "Java1.3.0");
		// in or out makes no difference for me.      
		uConnection.setRequestProperty("content-length", "" + cntLen);
		uConnection.setRequestProperty("connection", "Keep-Alive");
		uConnection.setRequestProperty("cache-control", "no-cache");
		uConnection.setRequestMethod("POST");

		uConnection.setDoInput(true);
		uConnection.setDoOutput(true);
		java.io.BufferedOutputStream out =
			new java.io.BufferedOutputStream(uConnection.getOutputStream());
		out.write(_dataToBeSent.getBytes());
		out.flush();
		// added this but it shouldn't make any difference with BufferedOutputStream.       
		//out.close();

		parseResponse();

		return respCode;

	}

	public void parseResponse() throws Exception {
		try {

			BufferedReader in =
				new BufferedReader(
					new InputStreamReader(uConnection.getInputStream()));
			StringBuffer xmlString = new StringBuffer();

			int i = 0;
			while ((inputLine = in.readLine()) != null) {
				xmlString.append(inputLine);
			}

			L.info ("parseResponse() : Parsing response, response message: "+xmlString.toString());

			Document document =
				DOMUtility.getDocumentFromString(xmlString.toString());
			Element root = document.getDocumentElement();
			//get the response code
			//and return it
			NodeList respNL = root.getElementsByTagName("resp_code");
			if (respNL != null && respNL.getLength() != 0) {
				Element respElement = (Element) respNL.item(0);
				 respCode = respElement.getFirstChild().getNodeValue();
				L.info("HttpTransporter.parseResponse() : Response Code: "+respCode);
			}


		} catch (Exception e) {
			throw e;
		}

	}

	/**
	 * Returns the _dataToBeSent.
	 * @return String
	 */
	public String get_dataToBeSent() {
		return _dataToBeSent;
	}

	/**
	 * Returns the _nameValuePairs.
	 * @return HashMap
	 */
	public HashMap get_nameValuePairs() {
		return _nameValuePairs;
	}

	/**
	 * Returns the _urlPath.
	 * @return String
	 */
	public String get_urlPath() {
		return _urlPath;
	}

	/**
	 * Sets the _dataToBeSent.
	 * @param _dataToBeSent The _dataToBeSent to set
	 */
	public void set_dataToBeSent(String _dataToBeSent) {
		this._dataToBeSent = _dataToBeSent;
	}

	/**
	 * Sets the _nameValuePairs.
	 * @param _nameValuePairs The _nameValuePairs to set
	 */
	public void set_nameValuePairs(HashMap _nameValuePairs) {
		this._nameValuePairs = _nameValuePairs;
	}

	/**
	 * Sets the _urlPath.
	 * @param _urlPath The _urlPath to set
	 */
	public void set_urlPath(String _urlPath) {
		this._urlPath = _urlPath;
	}

	/**
	 * Returns the _nameValue.
	 * @return boolean
	 */
	public boolean is_nameValue() {
		return _nameValue;
	}

	/**
	 * Sets the _nameValue.
	 * @param _nameValue The _nameValue to set
	 */
	public void set_nameValue(boolean _nameValue) {
		this._nameValue = _nameValue;
	}


	

}
