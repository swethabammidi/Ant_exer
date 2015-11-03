import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringReader;
import java.net.URL;
import java.util.Date;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.xerces.parsers.DOMParser;
import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.XMLSerializer;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import sun.net.www.protocol.http.HttpURLConnection;

import com.vzw.edr.selfProv.utils.SPProps;
import com.vzw.selfProvisioning.utils.DOMUtility;

/**
 * @version 	1.0
 * @author
 */
public class TestDriver extends HttpServlet implements Servlet {

	private static Logger L = Logger.getLogger(SPProps.getFELogName(TestDriver.class));
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
		Object xPath = null;
		String mdn = req.getParameter("mdn");
		String min = req.getParameter("min");
		String vendor_id = req.getParameter("vendor_id");
		String channel_id = req.getParameter("channel_id");
		String service_id = req.getParameter("service_id");
		String action_type = req.getParameter("action_type");
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
		if (vendor_id == null || vendor_id.trim().equals(""))
			vendor_id = "999999";
		if (channel_id == null || channel_id.trim().equals(""))
			channel_id = "1";
		if (service_id == null || service_id.trim().equals(""))
			service_id = "RBT";
		if (action_type == null || action_type.trim().equals(""))
			action_type = "add";
		L.debug ("mdn = " + mdn);		
		L.debug ("min = " + min);		
		L.debug ("vendor_id = " + vendor_id);		
		L.debug ("channel_id = " + channel_id);		
		L.debug ("service_id = " + service_id);
		L.debug ("action_type = " + action_type);				

		StringBuffer sb = new StringBuffer();
		sb.append("<SPVReqRec xmlns=\"http://www.vzw.com/namespaces/spv\"");
		sb.append(" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"");
		sb.append(" xsi:schemaLocation=\"http://www.vzw.com/namespaces/spv SelfProvisioningVendor1.0.xsd\"");
		sb.append(" ReqType=\"x\">");
		Date now = new Date();
		String eventId = "" + now.getTime();
		sb.append("<req_event_id>" + eventId + "</req_event_id>");
		String date = SPProps.dateFormatter.format(new Date());
		sb.append("<event_date>" + date + "</event_date>");
		sb.append("<mdn>" + mdn + "</mdn>");
		sb.append("<min>" + min + "</min>");
		sb.append("<vendor_id>" + vendor_id + "</vendor_id>");
		sb.append("<channel_id>" + channel_id + "</channel_id>");
		sb.append("<service_id action=\"" + action_type + "\">" + service_id + "</service_id>");
		sb.append("</SPVReqRec>");
		
		DocumentBuilderFactory df = DocumentBuilderFactory.newInstance();
		DOMParser parser = new DOMParser();
		Document doc = null;
		try
		{
			df.setNamespaceAware(false);
			df.setValidating(false);
			df.setExpandEntityReferences(false);
			df.setCoalescing(false);
			DocumentBuilder builder = df.newDocumentBuilder();
			builder.setEntityResolver(null);
			builder.setErrorHandler(null);
			doc = builder.parse(new InputSource(new StringReader(sb.toString())));

			L.debug ("SelfProvisioningServlet.sendXMLMessage() : Attempting to send the xml message to "+SPProps.backendServlet);
			URL url = new URL(SPProps.backendServlet);
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			conn.setDoInput(true);
			conn.setDoOutput(true);
			OutputStream out = conn.getOutputStream();
			XMLSerializer ser = new XMLSerializer (
								 out, new OutputFormat ("xml", "UTF-8", false));
			L.debug ("Writing the document to connection");
			ser.serialize(doc);
			L.debug ("Closing Output Stream");
			out.flush();
			DocumentBuilderFactory dF1 = DocumentBuilderFactory.newInstance(); 
			dF1.setValidating(false);
			dF1.setNamespaceAware(false);
			
			BufferedReader in =
				new BufferedReader(
					new InputStreamReader(conn.getInputStream()));
			StringBuffer xmlString = new StringBuffer();

			String inputLine = "";
			int i = 0;
			while ((inputLine = in.readLine()) != null) {
				xmlString.append(inputLine);
			}

			L.info ("parseResponse() : Parsing response, response message: "+xmlString.toString());

			Document retDoc =
				DOMUtility.getDocumentFromString(xmlString.toString());

	
			Element docEle = retDoc.getDocumentElement();
			NodeList rCodeL = retDoc.getElementsByTagName("resp_code");
			if (rCodeL == null || rCodeL.getLength() < 1)
			{
				respcode = "0";
			}
			else
			{
				Node aNode = rCodeL.item(0);
				respcode = nodeToString(aNode);
			}
			NodeList rDescL = retDoc.getElementsByTagName("resp_description");
			if (rDescL == null || rDescL.getLength() < 1)
			{
				respdesc = "";
			}
			else
			{
				Node aNode = rDescL.item(0);
				respdesc = nodeToString(aNode);
			}
		}
		catch (Exception e)
		{
			L.debug ("Exceptiopn e = " , e);
		}

		L.debug ("respcode = " + respcode);
		res.setContentType("text/html");
		PrintWriter pout = res.getWriter();
		pout.println("<html><head><title>Response</title></head>");
		pout.println("<body><b>Response Code:" + respcode + "</b><br>");
		pout.println("<b>Response Desc:" + respdesc + "</b></body></html>");
	}

	public static String nodeToString (Node aNode)
	{
		return nodeToString(aNode, false);
	}
	public static String nodeToString (Node aNode, boolean nSep)
	{
		StringBuffer sb = new StringBuffer();
		if (aNode.getNodeType() == Node.ELEMENT_NODE)
		{
			Element aEle = (Element)aNode;
			NodeList aNList = aEle.getChildNodes();
			for (int i = 0; i < aNList.getLength(); i++)
			{
				Node aNode1 = aNList.item(i);
				if (aNode1.getNodeType() == Node.ELEMENT_NODE)
				{
					if (nSep)
					{
						sb.append(nodeToString(aNode1, nSep) + " ");
					}
					else
					{
						sb.append(nodeToString(aNode1, nSep));
					}
				}
				else
				{
					if (nSep)
					{
						sb.append(aNode1.getNodeValue() + " ");
					}
					else
					{
						sb.append(aNode1.getNodeValue());
					}
				}
			}
		}
		else
		{
			if (nSep)
			{
				sb.append(aNode.getNodeValue() + " ");
			}
			else
			{
				sb.append(aNode.getNodeValue());
			}
		}
		return sb.toString();
	}

	/**
	* @see javax.servlet.GenericServlet#void ()
	*/
	public void init() throws ServletException {
		super.init();
		L.setLevel(Level.DEBUG);
	}

}
