package com.vzw.selfProvisioning;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.vzw.edr.selfProv.utils.SPProps;
import javax.servlet.Servlet;

import org.apache.log4j.Logger;


/**
 * @version 	1.0
 * @author		Roy Kachouh			Verizon Wireless
 * @created Sept 10, 2003
 * 
 * This class will be responsible for taking an input parameter named 'actionClass' 
 * from the WAP presentation page and constructing a Helper class based on the 
 * parameter provided.
 * It will then construct a RequestDispatcher object.  The request dispatcher object 
 * will forward control to a resource specified by the return value of the action 
 * class
 * 
 */
public class SelfProvisioningServlet extends HttpServlet implements Servlet {
	private static Logger L = Logger.getLogger(SPProps.getFELogName(SelfProvisioningServlet.class));
	String contentPackage="com.vzw.selfProvisioning.mobileWeb.";
	//private ServletContext context;

	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException
	{
		defaultAction(req, resp);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException
	{
		defaultAction(req, resp);
	}

	public void defaultAction(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
		Date startDate = null;	
		Date endDate = null;
		startDate = new Date();
		String actionClass = null;
		String logTag = null;
		
		try {
			actionClass = req.getParameter("actionClass");
			if (actionClass == null || actionClass.trim().equals(""))
			{
				L.debug("actionClass is null, assigning SignUp");
				actionClass = "SignUp";
			}
			HttpSession session = req.getSession();
			String mdn = (String)session.getAttribute("mdn");
			if (mdn == null || mdn.trim().equals("") || actionClass.equals("SignUp"))
			{
				mdn = "9999999999";
				logTag = " [" + mdn + "-9999999999] ";
			}
			else
			{
				logTag = (String) session.getAttribute("logtag");
				if (logTag == null || logTag.trim().equals(""))
				{
					String eventId = (String) session.getAttribute("eventid");
					if (eventId == null || eventId.trim().equals(""))
					{
						Date now = new Date();
						eventId = "" + now.getTime();
						session.setAttribute("eventid", eventId);
					}
					logTag = " [" + mdn + "-" + eventId + "] ";
					session.setAttribute("logtag", logTag);
				}
			}

			L.info("defaultAction():" + logTag + "Entering defaultAction(), actionClass = " + actionClass);
			SPProps.infoLogger.info("SelfProvisioningServlet.defaultAction():" + logTag + "Entered with actionClass = " + actionClass);
			String instance = contentPackage+actionClass;
			Class helperClass = Class.forName(instance);
			L.debug ("defaultAction():" + logTag + "Instantiating "+instance);
			ActionInterface ai = (ActionInterface) helperClass.newInstance();
			String returnPage = ai.doAction(req, resp);
			gotoPage(returnPage, req, resp);
		}
		catch (Exception e)
		{
			L.error ("defaultAction():" + logTag + "Exception", e);
		} finally {
			endDate = new Date();
			long dateOffset = endDate.getTime() - startDate.getTime();
			SPProps.infoLogger.info("SelfProvisioningServlet.defaultAction():" + logTag +
				"Finished a request. Elapsed run time:: "+dateOffset/1000+"."+dateOffset%1000);
		}
	}

	private void gotoPage(
		String address,
		HttpServletRequest req,
		HttpServletResponse resp)
		throws ServletException, IOException
	{
		HttpSession session = req.getSession();
		session.setAttribute("page",address);
		getServletContext().getRequestDispatcher(address).forward(req, resp);
	}
}
