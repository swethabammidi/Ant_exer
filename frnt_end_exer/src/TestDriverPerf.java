import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.vzw.edr.selfProv.utils.SPProps;

/**
 * @version 	1.0
 * @author
 */
public class TestDriverPerf extends HttpServlet implements Servlet {

	private static Logger L = Logger.getLogger(SPProps.getFELogName(TestDriverPerf.class));
	/**
	* @see javax.servlet.http.HttpServlet#void (javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	*/
	public void doGet(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException
	{
		defaultAction (req, res);
	}

	/**
	* @see javax.servlet.http.HttpServlet#void (javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	*/
	public void doPost(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException
	{
		defaultAction (req, res);
	}

	public void defaultAction(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException
	{
		String reqType = req.getParameter("ReqType");
		String mdn = req.getParameter("mdn");
		String min = req.getParameter("min");
		String prepay_ind = req.getParameter("prepay_ind");
		String vendor_id = req.getParameter("vendor_id");
		String channel_id = req.getParameter("channel_id");
		String service_id = req.getParameter("service_id");
		String action_type = req.getParameter("action_type");
		String [] content = new String[5];
		for(int i=0; i<5; i++) {
			content[i] = req.getParameter("content"+i);
		}
		String respcode = "200";
		String respdesc = "";
		
		long mdn_l = 0;
		long min_l = 0;
		try
		{
			mdn_l = new Long(mdn).longValue();
		}
		catch (Exception e) {}
		
		try
		{
			min_l = new Long(min).longValue();
		}
		catch (Exception e) {}

		if (mdn_l == 0L && min_l == 0L)
		{
			// Error			
		}
		if (mdn == null)
			mdn = "";
		if (min == null)
			min = "";
		if (prepay_ind == null)
			prepay_ind = "";
		if (vendor_id == null || vendor_id.trim().equals(""))
			vendor_id = "999999";
		if (channel_id == null || channel_id.trim().equals(""))
			channel_id = "1";
		//if (service_id == null || service_id.trim().equals(""))
		//	service_id = "RBT";
		if (action_type == null || action_type.trim().equals(""))
			action_type = "add";
			
		L.debug ("reqType = " + reqType);	
		L.debug ("mdn = " + mdn);		
		L.debug ("min = " + min);		
		L.debug ("prepay_ind = " + prepay_ind);	
		L.debug ("vendor_id = " + vendor_id);		
		L.debug ("channel_id = " + channel_id);		
		L.debug ("service_id = " + service_id);
		L.debug ("action_type = " + action_type);
		for(int i=0; i<5; i++)
			L.debug ("content = " + content[i]);

		Date now = new Date();
		String eventId = "" + now.getTime();
		String date = SPProps.dateFormatter.format(new Date());
		/*
		Document doc = DOMUtility.getNewDocument();
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
		newElement = DOMUtility.createElement(doc, "event_date", date);
		spvReqElement.appendChild(newElement);

		//<mdn>
		newElement = DOMUtility.createElement(doc,"mdn",mdn);
		spvReqElement.appendChild(newElement);

		//<min>
		newElement = DOMUtility.createElement(doc, "min", min);
		spvReqElement.appendChild(newElement);

		//<vendor_id>
		newElement =
			DOMUtility.createElement(doc, "vendor_id", vendor_id);
		spvReqElement.appendChild(newElement);

		//<channel_id>
		newElement =
			DOMUtility.createElement(doc, "channel_id", channel_id);
		spvReqElement.appendChild(newElement);

		//<service_id>
		newElement =
			DOMUtility.createElement(doc, "service_id", service_id);
		newElement.setAttribute("action", action_type);
		spvReqElement.appendChild(newElement);
		
		StringWriter wri = new StringWriter();
		OutputFormat oFormat = new OutputFormat("xml", "UTF-8", true);
		XMLSerializer ser = new XMLSerializer (wri, oFormat);
		ser.serialize(doc);
		L.debug("Input Doc:\n" + wri.toString());

		String xmlData = DOMUtility.getStringFromDocument(doc);
		L.debug("xmlData = " + xmlData);
		*/
		StringBuffer xmlBuf = new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		xmlBuf.append("<SPVReqRec xmlns=\"http://www.vzw.com/namespaces/spv\" ");
		xmlBuf.append("xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" ");
		
		//xmlBuf.append("ReqType=\"s\" ");
		//reqType = "s";
		xmlBuf.append("ReqType=\"" + reqType + "\" ");
		xmlBuf.append("xsi:schemaLocation=\"http://www.vzw.com/namespaces/spv SelfProvisioningVendor1.0.xsd\">");
		xmlBuf.append("<req_event_id>" + eventId + "</req_event_id>");
		xmlBuf.append("<event_date>" + date + "</event_date>");
		xmlBuf.append("<mdn>" + mdn + "</mdn>");
		xmlBuf.append("<min>" + min + "</min>");
		if(prepay_ind!=null && prepay_ind.trim().length()>0)
			xmlBuf.append("<prepay_ind>" + prepay_ind + "</prepay_ind>");
		
		xmlBuf.append("<vendor_id>" + vendor_id + "</vendor_id>");
		xmlBuf.append("<channel_id>" + channel_id + "</channel_id>");
		
		if(service_id!=null && !service_id.trim().equals(""))
			xmlBuf.append("<service_id action=\"" + action_type + "\">" + service_id + "</service_id>");	
		
		for(int i=0; i<5; i++) {
			if(content[i]!=null && !content[i].trim().equals(""))
				xmlBuf.append("<content_id action=\"add\">" + content[i] + "</content_id>");
		}
				
		xmlBuf.append("</SPVReqRec>");
		L.debug("xmlBuf = " + xmlBuf.toString());

		try {
			//ship it to the backend and parse the response
			//HttpTransporter transport =	new HttpTransporter(SPProps.backendServlet, xmlData);
			respcode = sendXMLMessage(SPProps.backendServlet, xmlBuf.toString());
		} catch (Exception e) {
			respcode = "101";
			respdesc = "Unbale to connect to backend";
		}

		L.debug ("resp = " + respcode);
		res.setContentType("text/xml");
		PrintWriter out = res.getWriter();
		out.print(respcode);
		//out.println("<html><head><title>Response</title></head>");
		//out.println("<body><b>Response Code:" + respcode + "</b><br>");
		//out.println("<b>Response Desc:" + respdesc + "</b></body></html>");
	}

	/**
	* @see javax.servlet.GenericServlet#void ()
	*/
	public void init() throws ServletException {
		super.init();
		L.setLevel(Level.DEBUG);
	}

	public String sendXMLMessage(String urlStr, String data) throws Exception {
		L.debug ("sendXMLMessage() : Attempting to send the xml message to "+urlStr);
		// http://localhost:9080/SelfProvBackEnd/SelfProvInterface
		//<servlet-name>SelfProvIntfServlet</servlet-name>
		java.net.URL url = new java.net.URL(urlStr);
		java.net.HttpURLConnection uConnection;

		int cntLen = data.length();
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
		out.write(data.getBytes());
		out.flush();
		// added this but it shouldn't make any difference with BufferedOutputStream.       
		//out.close();
		BufferedReader in =
			new BufferedReader(
				new InputStreamReader(uConnection.getInputStream()));
		StringBuffer xmlString = new StringBuffer();
	
		int i = 0;
		String inputLine = "";
		while ((inputLine = in.readLine()) != null) {
			xmlString.append(inputLine);
		}
		return xmlString.toString();
	}
}
