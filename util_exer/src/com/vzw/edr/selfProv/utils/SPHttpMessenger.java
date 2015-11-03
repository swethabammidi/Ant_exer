package com.vzw.edr.selfProv.utils;

import java.io.*;
import java.net.*;
import java.util.*;

import org.apache.commons.httpclient.DefaultMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.log4j.*;

import org.w3c.dom.Document;

import org.apache.xml.serialize.XMLSerializer;
import org.apache.xml.serialize.OutputFormat;

/**
 * @author kasarsri, ravi vellanki, metelitsa
 * 
 * This class will be used to write and read data to and from a URL path.
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class SPHttpMessenger {
	private static Logger L =
		Logger.getLogger(SPProps.getBELogName(SPHttpMessenger.class));

	private String _urlPath = null;
	private String _dataToBeSent = null;
	private HashMap _nameValuePairs = null;
	private boolean _nameValue = false;

	/**
	 * SPHttpMessenger Constructor.
	 * @param urlPath
	 * @param xmlFile The data to send.
	 */
	public SPHttpMessenger(String urlPath, String xmlFile) {
		set_urlPath(urlPath);
		set_dataToBeSent(xmlFile);
	}

	/**
	 * SPHttpMessenger Constructor.
	 * @param urlPath
	 * @param nvHashMap The data to send.
	 */
	public SPHttpMessenger(String urlPath, HashMap nvHashMap) {
		L.debug("Entered SPHttpMessenger");
		L.debug("SPHttpMessenger: urlPath = " + urlPath);
		L.debug("SPHttpMessenger: nvHashMap = " + nvHashMap);

		Set e = nvHashMap.entrySet();
		Iterator i = e.iterator();

		StringBuffer nameValPairs = null;

		if (i.hasNext()) {
			nameValPairs = new StringBuffer();
			nameValPairs.append(i.next());
		}
		while (i.hasNext()) {
			nameValPairs.append("&").append(i.next());
		}
		//	"Inside Iterator :" + nameValPairs.toString().trim());
		set_dataToBeSent(nameValPairs.toString().trim());

		L.debug(
			"SPHttpMessenger: dataToBeSent = "
				+ nameValPairs.toString().trim());
		set_urlPath(urlPath);

		set_nameValue(true);
	}

	/**
	 * Method sendHttpMessage. Used to send the given data.
	 * @returns String This should have the response message populated.
	 * @throws Exception
	 */
	public String sendHttpMessage() throws Exception {
		String responseMsg = null;
		if (is_nameValue()) {
			L.debug("sendHttpMessage(): Invoking sendNameValueMessage()");
			responseMsg = sendNameValueMessage();
		} else {
			L.debug("sendHttpMessage(): Invoking sendXMLMessage()");
			responseMsg = sendXMLMessage();
		}

		return responseMsg;
	}

	public String sendXmlRequest() {
		
		////////////////   Fixing ztango connection issue - introduce socket timeout  /////////////////
		//HttpURLConnection conn = null;
		//String inputLine = null;
		//String xmlResponse = null;
		//Document doc = null;
		//try {
		//	URL url = new URL(_urlPath);
		//	L.debug("Openning Connection to URL: " + _urlPath);
		//	conn = (HttpURLConnection) url.openConnection();
		//	// added for ztango stupid server
		//	conn.setRequestProperty("accept", "*/*");
		//	conn.setRequestProperty("accept-language", "en-us");
		//	conn.setRequestProperty("content-type", "text/xml");
		//	conn.setRequestProperty("user-agent", "Java1.3.0");
		//	// in or out makes no difference for me.      
		//	conn.setRequestProperty("content-length", "" + _dataToBeSent.length());
		//	conn.setRequestProperty("connection", "Keep-Alive");
		//	conn.setRequestProperty("cache-control", "no-cache");
		//	conn.setRequestMethod("POST");
		//	conn.setDoInput(true);
		//	conn.setDoOutput(true);
		//	OutputStream out = conn.getOutputStream();
		//	XMLSerializer ser =
		//		new XMLSerializer(out, new OutputFormat("xml", "UTF-8", false));
		//	L.debug("Writing the document to connection:" + _dataToBeSent);
		//	doc = SPDOMUtility.getDocumentFromString(_dataToBeSent);
		//	ser.serialize(doc);
		//	L.debug("Closing Output Stream");
		//	out.flush();
		//	// this added because it's not buffered stream
		//	out.close();
		//	BufferedReader in =
		//		new BufferedReader(
		//			new InputStreamReader(conn.getInputStream()));
		//	StringBuffer sb = new StringBuffer();
		//	while ((inputLine = in.readLine()) != null) {
		//		L.debug(
		//			"SPHttpMessenger sendXMLMessage() inputLine = "
		//				+ inputLine);
		//		sb.append(inputLine);
		//	}
		//
		//	in.close();
		//	xmlResponse = sb.toString();
		//} catch (MalformedURLException e) {
		//	L.error("sendXmlOverHttp:", e);
		//} catch (IOException e) {
		//	L.error("sendXmlOverHttp:", e);
		//} catch (Exception e) {
		//	L.error("sendXmlOverHttp:", e);
		//}
		//return xmlResponse;
		//
		//
		//
		//
		//
		////////////////   Fixing ztango connection issue - introduce socket timeout  /////////////////
		String xmlResponse = null;
		
		try {
			HttpClient client = new HttpClient();
			
			L.debug("Openning Connection to URL: " + _urlPath);
			PostMethod pm = new PostMethod(_urlPath);
	
			// default - timeout 6 sec
			int timeout = 6000;
			String toStr = SPProps.getProperty("CONNECTION_TIMEOUT");
			if(toStr!=null && toStr.trim().length()>0)
				timeout = Integer.parseInt(toStr);
				
			client.setTimeout(timeout);
			L.debug("HttpClient timeout set to " + timeout);
			
			// default - no retry
			String retry = SPProps.getProperty("CONNECTION_RETRY");
			if(retry!=null && retry.trim().length()>0) {
				DefaultMethodRetryHandler retryhandler = new DefaultMethodRetryHandler();
				retryhandler.setRequestSentRetryEnabled(true);
				retryhandler.setRetryCount(Integer.parseInt(retry));
				pm.setMethodRetryHandler(retryhandler);
				L.debug("HttpClient retry set to " + retry);
			}
			else
				L.debug("HttpClient set NO retry ");

			pm.setRequestBody(_dataToBeSent);
			L.debug("Writing the document to connection:" + _dataToBeSent);
			pm.setRequestHeader("accept", "*/*");
			pm.setRequestHeader("accept-language", "en-us");
			pm.setRequestHeader("content-type", "text/xml");
			pm.setRequestHeader("user-agent", "Java1.3.0");
			pm.setRequestHeader("content-length", "" + _dataToBeSent.length());
			pm.setRequestHeader("connection", "Keep-Alive");
			pm.setRequestHeader("cache-control", "no-cache");
	
			int status = client.executeMethod(pm);
			L.debug("sendXmlRequest(): response code - " + status);
			xmlResponse = pm.getResponseBodyAsString();
	
			pm.releaseConnection( );
	
		} catch (MalformedURLException e) {
			L.error("sendXmlRequest(): MalformedURLException - ", e);
		} catch (IOException e) {
			L.error("sendXmlRequest(): IOException - ", e);
		} catch (Exception e) {
			L.error("sendXmlRequest(): Exception - ", e);
		} 
		
		return xmlResponse;
		
	}

	/**
	 * Method sendNameValueMessage.
	 * @return String
	 * @throws Exception
	 */
	private String sendNameValueMessage() throws Exception {
		L.debug("Entered SPHttpMessenger.sendNameValueMessage()");
		String inputLine = null;
		try {
			URL url = new URL(_urlPath);
			HttpURLConnection.setFollowRedirects(false);
			HttpURLConnection connection =
				(HttpURLConnection) url.openConnection();

			connection.setDoOutput(true);
			PrintWriter out = new PrintWriter(connection.getOutputStream());

			L.debug(
				"SPHttpMessenger sendNameValueMessage(): _dataToBeSent = "
					+ _dataToBeSent);

			out.print(_dataToBeSent);
			out.close();

			BufferedReader in =
				new BufferedReader(
					new InputStreamReader(connection.getInputStream()));

			int i = 0;
			while ((inputLine = in.readLine()) != null) {
				L.debug(
					"SPHttpMessenger sendNameValueMessage(): inputLine = "
						+ inputLine);

				if (i == 0) {
					if (!inputLine.equalsIgnoreCase("<HTML>")) {
						break;
					}
				}

				if (i == 2) {
					inputLine = "EDR " + inputLine;
					break;
				}
				++i;
			}
			in.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
			String errorCode = "S4010";
			L.debug(
				errorCode
					+ " SPHttpMessenger sendNameValueMessage(): MalformedURLException");
			throw new SPSystemException(errorCode, e.getLocalizedMessage());
		} catch (IOException e) {
			e.printStackTrace();
			String errorCode = "S4011";
			L.debug(
				errorCode
					+ " SPHttpMessenger sendNameValueMessage(): MalformedURLException");
			throw new SPSystemException(errorCode, e.getLocalizedMessage());
		}

		L.debug(
			"Exiting SPHttpMessenger.sendNameValueMessage(). inputLine = "
				+ inputLine);
		return inputLine;
	}

	/**
	 * Method sendXMLMessage.
	 * @return String
	 * @throws Exception
	 */
	private String sendXMLMessage() throws Exception {
		L.debug("Entered SPHttpMessenger.sendXMLMessage()");
		String inputLine = null;
		String xmlResponse = null;
		final String XML_REQ_DOC = "xmlreqdoc=";

		try {
			java.net.URL url = new java.net.URL(_urlPath);

			//java.net.URL URL_Object = new java.net.URL ("http://NJWARHQD0IT3A13:8080/servlet/com.vzw.edr.VisionEDRInterfaceServlet?");			
			//java.net.HttpURLConnection uConnection;

			//int cntLen = _dataToBeSent.length();
			//uConnection = (java.net.HttpURLConnection) url.openConnection();
			//uConnection.setRequestProperty("accept", "*/*");
			//uConnection.setRequestProperty("accept-language", "en-us");
			//uConnection.setRequestProperty(
			//	"content-type",
			//	"text/xml");
			//uConnection.setRequestProperty("user-agent", "Java1.3.0");
			// in or out makes no difference for me.      
			//uConnection.setRequestProperty("content-length", "" + cntLen);
			//uConnection.setRequestProperty("connection", "Keep-Alive");
			//uConnection.setRequestProperty("cache-control", "no-cache");
			//uConnection.setRequestMethod("POST");

			//uConnection.setDoInput(true);
			//uConnection.setDoOutput(true);

			//boolean isUsingProxy = uConnection.usingProxy();
			//uConnection.connect();
			//OutputStream oStream = uConnection.getOutputStream();
			//java.io.BufferedOutputStream out =
			//	new java.io.BufferedOutputStream(oStream);

			//String encodedName = URLEncoder.encode("xmlreqdoc");
			//String encodedValue = URLEncoder.encode(_dataToBeSent);	
			//String postMessage = encodedName + "=" + encodedValue;	

			//L.debug("sendXMLMessage(): postMessage = " + postMessage);										
			//out.write(postMessage.getBytes());
			//out.flush();
			// added this but it shouldn't make any difference with BufferedOutputStream.       
			//out.close();

			java.net.HttpURLConnection uConnection =
				(java.net.HttpURLConnection) (url.openConnection());
			uConnection.setRequestMethod("POST");
			uConnection.setDoOutput(true);
			java.io.PrintWriter out =
				new java.io.PrintWriter(uConnection.getOutputStream());

			L.debug(
				"SPHttpMessenger sendXMLMessage(): sending to VBS: "
					+ XML_REQ_DOC
					+ _dataToBeSent);
			String postMessage =
				XML_REQ_DOC
					+ java.net.URLEncoder.encode(_dataToBeSent, "UTF-8");
			//L.debug("sendXMLMessage(): postMessage = " + postMessage);		
			out.println(postMessage);
			out.close();

			if (uConnection.getResponseCode() != HttpURLConnection.HTTP_OK) {
				L.error("SPHttpMessenger sendXMLMessage() Connection Error");
				throw new SPSystemException(
					Integer.toString(uConnection.getResponseCode()),
					uConnection.getResponseMessage());
			}

			BufferedReader in =
				new BufferedReader(
					new InputStreamReader(uConnection.getInputStream()));

			StringBuffer sb = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				L.debug(
					"SPHttpMessenger sendXMLMessage() inputLine = "
						+ inputLine);
				sb.append(inputLine);
			}

			in.close();
			xmlResponse = sb.toString();
		} catch (MalformedURLException e) {
			e.printStackTrace();
			String errorCode = "S4030";
			L.debug(
				errorCode
					+ " SPHttpMessenger sendXMLMessage(): MalformedURLException");
			throw new SPSystemException(errorCode, e.getLocalizedMessage());
		} catch (ProtocolException e) {
			e.printStackTrace();
			String errorCode = "S4031";
			L.debug(
				errorCode
					+ " SPHttpMessenger sendXMLMessage(): ProtocolException");
			throw new SPSystemException(errorCode, e.getLocalizedMessage());
		} catch (IOException e) {
			e.printStackTrace();
			String errorCode = "S4032";
			L.debug(
				errorCode + " SPHttpMessenger sendXMLMessage(): IOException");
			throw new SPSystemException(errorCode, e.getLocalizedMessage());
		} catch (SPSystemException e) {
			e.printStackTrace();
			String errorCode = "S4033";
			L.debug(
				errorCode
					+ " SPHttpMessenger sendXMLMessage(): SPSystemException");
			throw new SPSystemException(e.getErrorCode(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			String errorCode = "S4034";
			L.debug(errorCode + " SPHttpMessenger sendXMLMessage(): Exception");
			throw new SPSystemException(errorCode, e.getLocalizedMessage());
		}

		L.debug(
			"Exiting SPHttpMessenger.sendXMLMessage(), xmlResponse = "
				+ xmlResponse);
		return xmlResponse;
	}

	/**
	 * Method readInput. This private method is used to read Object data from a stream.
	 * @param inputStream
	 * @return String
	 */
	private String readInput(InputStream inputStream)
		throws SPSystemException {
		L.debug("Entered SPHttpMessenger.readInput()");

		String responseMsg = null;
		try {
			//Read the Object
			ObjectInputStream objectIStream =
				new ObjectInputStream(inputStream);
			Object readObject = objectIStream.readObject();
			objectIStream.close();

			if (readObject instanceof String) {
				responseMsg = (String) readObject;
			} else {
				throw new SPSystemException(
					"S4060",
					"Error Occurred in readInput of a HTTP request");
			}
		} catch (SPSystemException e) {
			e.printStackTrace();
			String errorCode = "S4070";
			L.debug(
				errorCode
					+ " SPHttpMessenger sendXMLMessage(): SPSystemException");
			throw new SPSystemException(errorCode, e.getLocalizedMessage());
		} catch (Exception e) {
			e.printStackTrace();
			L.debug("S4071", e);
			throw new SPSystemException("S4070", e.getLocalizedMessage());
		}

		L.debug("Exiting SPHttpMessenger.readInput()");
		return responseMsg;
	} //readInput()

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
