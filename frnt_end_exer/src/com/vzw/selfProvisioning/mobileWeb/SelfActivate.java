package com.vzw.selfProvisioning.mobileWeb;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.vzw.edr.selfProv.utils.SPProps;
import com.vzw.selfProvisioning.ActionInterface;
import com.vzw.selfProvisioning.RequestResponse;
import com.vzw.selfProvisioning.utils.DOMUtility;
import com.vzw.selfProvisioning.utils.HttpTransporter;
import com.vzw.selfProvisioning.utils.Util;

/**
 * @author Roy Kachouh		Verizon Wireless
 * 
 * @created Sept 10, 2003
 *
·*	This class implements the doAction() method from the ActionInterface.  
 *  It is responsible for sniffing an http request which was passed as a parameter 
 *  to it’s doAction() method.  It will then utilize the relevant HTTP header data 
 *  and construct and store the min in the http session.
 * 
 */
public class SelfActivate extends RequestResponse implements ActionInterface {
	private static Logger L = Logger.getLogger(SPProps.getFELogName(SelfActivate.class));		
	private String _mdn = null;
	private String xmlData = null;
	private Document xmlMessage = null;
	private String logTag = null;
	
	/**
	 * @see com.vzw.selfProvisioning.ActionInterface#doAction()
	 * 
	 * This method is implemented from the action interface.  
	 * It takes an HttpServletRequest and a HttpServletResponse as its parameter.  
	 * It will return a string containing an address of a resource back to its caller.
	 * 
	 */
	public String doAction(HttpServletRequest req, HttpServletResponse resp)
		throws Exception
	{
		this._req = req;
		this._resp = resp;
		set_req(_req);
		showCookies();
		//get the min from the http session
		_mdn = (String) get_session().getAttribute("mdn");
		if (_mdn == null || _mdn.trim().equals(""))
		{
			L.error("doAction, mdn is null. Can't continue");
			ActivationFailed af = new ActivationFailed();
			af.setErrorCode("999");
			req.setAttribute("statusBean", af);
			return SPProps.getProperty("errorPage");
		}

		String eventId = (String) get_session().getAttribute("eventid");
		if (eventId == null || eventId.trim().equals(""))
		{
			Date now = new Date();
			eventId = "" + now.getTime();
			get_session().setAttribute("eventid", eventId);
		}

		logTag = (String) get_session().getAttribute("logtag");
		if (logTag == null || logTag.trim().equals(""))
		{
			logTag = " [" + _mdn + "-" + eventId + "] ";
			get_session().setAttribute("logtag", logTag);
		}

		L.info(	"doAction():" + logTag +
				"User has entered the activation page, with the following session: "+get_session().getId());
	  	SPProps.infoLogger.info("SelfActivate.doAction():" + logTag +
				"User has entered the activation page, with the following session: "+get_session().getId());
		
		//showCookies();
		String responseCode = null;

		//create an xml message
		contsructMessage(eventId);

		try {
			SPProps.infoLogger.info("SelfActivate.doAction():" + logTag +
				"Submitting Self Activate Request to the backend");
			//ship it to the backend and parse the response
			HttpTransporter transport =	new HttpTransporter(SPProps.backendServlet, xmlData);
			responseCode = transport.sendXMLMessage();
		} catch (Exception e) {
			responseCode = "101";
			L.error("doAction():" + logTag + "Unable to connect to the backend", e);
			SPProps.infoLogger.info("SelfActivate.doAction():" + logTag +
					"Self Activate Request Submit FAILED, Exception = " + e.getMessage());
			ActivationFailed af = new ActivationFailed();
			af.setErrorCode(responseCode);
			_req.setAttribute("statusBean", af);
			return SPProps.getProperty("errorPage");
		}

		if (responseCode.equals("200")) {
			L.info("doAction():" + logTag +
				"Self Activate Request Submit SUCCEDED");
			SPProps.infoLogger.info("SelfActivate.doAction():" + logTag +
				"Self Activate Request Submit SUCCEDED");
			return SPProps.getProperty("successPage");
		} else {
			L.error("doAction():" + logTag +
				"Recieved an error code from the backend, Response Code: " + responseCode);
			
			SPProps.infoLogger.info("SelfActivate.doAction():" + logTag +
					"Self Activate Request Submit FAILED, Response Code = " + responseCode);
			ActivationFailed af = new ActivationFailed();
			af.setErrorCode(responseCode);
			_req.setAttribute("statusBean", af);
			return SPProps.getProperty("errorPage");
		}
	}

	/**
	 * This method is called from the doAction() method.  It shall use the instance 
	 * variables set in the sniffHeader() method to construct an XML message.  Once 
	 * the message is properly constructed, it will be passed to the HttpTransporter 
	 * class 
	 */
	private void contsructMessage(String eventId) throws Exception
	{
		L.debug("constructMessage():" + logTag + "Begining to constuct xml message");
		xmlMessage = getRequestDocument(eventId);
		xmlData = DOMUtility.getStringFromDocument(xmlMessage);
		L.debug (xmlData);
		L.debug("constructMessage():" + logTag + "Finished constucting xml message, xml: " + xmlData);
	}
    
    
	private Document getRequestDocument(String eventId) throws Exception {
		Document doc = DOMUtility.getNewDocument();
		//<service_id>
		int service = Util.getService(this._req);
		
		//add SPVReqRec element as root element
		Element spvReqElement = doc.createElement("SPVReqRec");
		spvReqElement.setAttribute(
			"xmlns",
			"http://www.vzw.com/namespaces/spv");
		spvReqElement.setAttribute(
			"xmlns:xsi",
			"http://www.w3.org/2001/XMLSchema-instance");
		spvReqElement.setAttribute(
			"xsi:schemaLocation",
			"http://www.vzw.com/namespaces/spv SelfProvisioningVendor1.0.xsd");
		spvReqElement.setAttribute("ReqType", "x");
		doc.appendChild(spvReqElement);
		//<req_event_id>
		Element newElement = DOMUtility.createElement(doc, "req_event_id", eventId);
		spvReqElement.appendChild(newElement);

		//<event_date>
		String date = SPProps.dateFormatter.format(new Date());
		newElement = DOMUtility.createElement(doc, "event_date", date);
		spvReqElement.appendChild(newElement);

		//<mdn>
		//newElement = DOMUtility.createElement(doc,"mdn",this._mdn);
		//spvReqElement.appendChild(newElement);


		//<min> or <mdn>
		if(service == Util.VCAST)
			newElement = DOMUtility.createElement(doc, "mdn", this._mdn);
		else
		newElement = DOMUtility.createElement(doc, "min", this._mdn);

		spvReqElement.appendChild(newElement);

		//<vendor_id>
		newElement =
			DOMUtility.createElement(doc, "vendor_id", SPProps.defaultVendor);
		spvReqElement.appendChild(newElement);

		//<channel_id>
		newElement =
			DOMUtility.createElement(doc, "channel_id", SPProps.defaultChannel);
		spvReqElement.appendChild(newElement);

		
		if(service == Util.WSP)
		{
			newElement = DOMUtility.createElement(doc, "service_id", SPProps.defaultService);
			newElement.setAttribute("action", "add");
			spvReqElement.appendChild(newElement);
		}
		else if (service == Util.CSP)
		{
			newElement = DOMUtility.createElement(doc, "service_id", SPProps.wap1xService);
			newElement.setAttribute("action", "add");
			spvReqElement.appendChild(newElement);
			
		}
		else if (service == Util.VCAST)
		{
			String term = this._req.getParameter("term");
			if(term==null || term.trim().length()<1)
				L.error("Error in getting daily/monthly term for the VOD provisioning request from session");
			else {
				if(term.equalsIgnoreCase("monthly")) {
					newElement = DOMUtility.createElement(doc, "service_id", SPProps.vcastService);
					newElement.setAttribute("action", "add");
					spvReqElement.appendChild(newElement);
				}
				else if(term.equalsIgnoreCase("daily")) {
					newElement = DOMUtility.createElement(doc, "service_id", SPProps.v24Service);
					newElement.setAttribute("action", "add");
					spvReqElement.appendChild(newElement);
				}
				else
					L.error("Error in getting daily/monthly term for the VOD provisioning request from session");
			}
		}
		else if (service == Util.PPUS)
		{
			newElement = DOMUtility.createElement(doc, "service_id", SPProps.ppusService);
			newElement.setAttribute("action", "add");
			spvReqElement.appendChild(newElement);
			
		}
		
		else
		{
			L.debug ("getRequestDocument():" + logTag + "SelfActivate-->invalid browser type: ");
		}
	    	
		return doc;
	}
}
