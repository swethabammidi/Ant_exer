import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vzw.edr.selfProv.utils.SPProps;

import java.util.*;

/**
 * @version 	1.0
 * @author
 */
public class PropLoader extends HttpServlet implements Servlet {

	static SPProps props = null;
	//static String propPath = null;
	//static String propFile = null;
	/**
	* @see javax.servlet.http.HttpServlet#void (javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	*/
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException
	{
		defaultAction(req, resp);
	}

	/**
	* @see javax.servlet.http.HttpServlet#void (javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	*/
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException
	{
			defaultAction(req, resp);
	}

	/**
	* @see javax.servlet.http.HttpServlet#void (javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	*/
	public void defaultAction(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
		String act = req.getParameter("action");
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html");
		if (act == null || act.trim().equals(""))
		{
			out.print("<B><font color=\"red\">action parameter is null or empty. Usage: action=PRINT/RELOAD</font></B>");
			return;
		}
		if (act.trim().equalsIgnoreCase("print"))
		{
			printProps(out);
		}
		else if (act.trim().equalsIgnoreCase("reload"))
		{
			try
			{
				props = new SPProps();
			}
			catch (Exception e)
			{
				throw new ServletException ("Unable to load properties from database ... ");
			}
			out.print("<b>Reloaded Properties Successfully</b><br>");
			printProps(out);
		}
		else
		{
			out.print("<B><font color=\"red\">Invalid action parameter " + act + ". Usage: action=PRINT/RELOAD</font></B>");
			return;
		}
	}
	
	public void printProps(PrintWriter out)
	{
		Properties props = SPProps.getPropObject();
		out.println("<B>Properties:</B><BR>");
		Set keySet = props.keySet();
		TreeSet sortedKeys = new TreeSet(keySet);
		Iterator it = sortedKeys.iterator();
		while (it.hasNext())
		{
			String name = (String)it.next();
			String val = props.getProperty(name);
			out.println(name + " = <B>" + val + "</B><BR>");
		}
		Properties uiDBProps = SPProps.getUIDBPropObject();
		out.println("<B>UI DB Properties:</B><BR>");
		keySet = uiDBProps.keySet();
		sortedKeys = new TreeSet(keySet);
		it = sortedKeys.iterator();
		while (it.hasNext()) {
			String name = (String) it.next();
			String val = uiDBProps.getProperty(name);
			out.println(name + " = <B>" + val + "</B><BR>");
		}
		
	}

	/**
	* @see javax.servlet.GenericServlet#void ()
	*/
	public void init() throws ServletException {
		super.init();
		//propPath = System.getProperty("PROPPATH");
		//propFile = propPath + File.separator + "selfProvisioning.properties";
		//System.out.println("Property File is : " + propFile);
		try
		{
			props = new SPProps();
		}
		catch (Exception e)
		{
			throw new ServletException ("Unable to load properties from database ... ");
		}
	}

}
