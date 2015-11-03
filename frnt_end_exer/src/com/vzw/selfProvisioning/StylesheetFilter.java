package com.vzw.selfProvisioning;


import com.vzw.edr.selfProv.utils.SPProps;
import com.vzw.selfProvisioning.utils.*;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;

import org.apache.log4j.Logger;

import java.util.*;



/**
 * A utility class that uses the Servlet 2.3 Filtering API to apply
 * an XSLT stylesheet to a servlet response.
 *
 * @author Manohar R Nallapu
 */
public class StylesheetFilter  implements Filter {
	private static Logger L = Logger.getLogger(SPProps.getFELogName(StylesheetFilter.class));
    private FilterConfig filterConfig;
    private String xsltFileName;

    /**
     * This method is called once when the filter is first loaded.
     */
    public void init(FilterConfig filterConfig)
    	throws ServletException
    {
        this.filterConfig = filterConfig;
    }

    public void doFilter (ServletRequest req, ServletResponse res, FilterChain chain)
    	throws IOException, ServletException
    {
        if (!(res instanceof HttpServletResponse))
        {
        	L.error ("This filer only supports HTTP");
            throw new ServletException("This filter only supports HTTP");
        }
		String contentType = null;
		String styleSheet = null;
		
		BufferedHttpResponseWrapper responseWrapper =
                new BufferedHttpResponseWrapper((HttpServletResponse) res);

        chain.doFilter(req, responseWrapper);
		HttpSession session = ((HttpServletRequest)req).getSession();
		
		if (session == null)
		{
			L.error ("Unable to get the session in StylesheetFilter");
			((HttpServletResponse)res).sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"The system was unable to process your order, please call Customer Service, *611 from your mobile phone, to activate");
			return;
		}
		
		String mdn = (String)session.getAttribute("mdn");
		String logTag = null;
		if (mdn == null || mdn.trim().equals(""))
		{
			L.error ("mdn not found in session");
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
		int service = Util.getService((HttpServletRequest)req);
		if(service == Util.unKnowClient)
		{
			service = Util.getBrowserType((HttpServletRequest)req);
		}
		
		String page = (String)session.getAttribute("page");
		if (page == null || page.trim().equals(""))
		{
			L.error ("doFilter():" + logTag + "page attribute not found in session.");
			((HttpServletResponse)res).sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"The system was unable to process your order, please call Customer Service, *611 from your mobile phone, to activate");
			return;
		}
		
		L.debug ("doFilter():" + logTag + "In StylesheetFilter, page = " + page);
		L.debug ("doFilter():" + logTag + "In StylesheetFilter, service = " + service);
		String styleTemplate = null;
		String stylePath = filterConfig.getInitParameter("xsltPath");
		L.debug ("doFilter():" + logTag + "StyleSheet Filter, init parameter, xsltPath = " + stylePath);
		if (stylePath == null || stylePath.trim().equals(""))
		{
			L.debug("doFilter():" + logTag + "Init Paramter xsltPath not set. Assigning to /WEB-INF/xslt/");
			stylePath = "/WEB-INF/xslt/"; 
		}

		if(service == Util.WSP || service == Util.PPUS)
		{
			contentType = "text/html";
			styleTemplate = getXSLTTemplate(page);
			
			if (styleTemplate == null || styleTemplate.trim().equals(""))
			{
				L.error ("doFilter():" + logTag + "Can't get the style sheet template for page: " + page);
				((HttpServletResponse)res).sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"The system was unable to process your order, please call Customer Service, *611 from your mobile phone, to activate");
				return;
			}
//			String pageType = SPProps.getProperty("WSP" + "_" + "PAGE_TYPE");
			String pageType = "XHTML";
//			if((pageType == null ) || pageType.equals(""))
//				pageType = "XHTML"; 
			styleTemplate = styleTemplate.trim() + pageType;
			//styleSheet = SPProps.getProperty(styleTemplate);
			styleSheet = styleTemplate.trim() + ".xsl";
		}
		else if(service == Util.CSP)
		{
			contentType = "text/vnd.wap.wml";
			styleTemplate = getXSLTTemplate(page);
			if (styleTemplate == null || styleTemplate.trim().equals(""))
			{
				L.error ("doFilter():" + logTag + "Can't get the style sheet template for page: " + page);
				((HttpServletResponse)res).sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"The system was unable to process your order, please call Customer Service, *611 from your mobile phone, to activate");
				return;
			}
			String pageType = "WML";
//			String pageType = SPProps.getProperty("CSP" + "_" + "PAGE_TYPE");
//			if((pageType == null ) || pageType.equals(""))
//				pageType = "WML"; 
			styleTemplate = styleTemplate.trim() + pageType;
			//styleSheet = SPProps.getProperty(styleTemplate);
			styleSheet = styleTemplate.trim() + ".xsl";
		}
		else if(service == Util.VCAST)
		{
			contentType = "text/html";
			styleTemplate = getXSLTTemplate(page);
			
			if (styleTemplate == null || styleTemplate.trim().equals(""))
			{
				L.error ("doFilter():" + logTag + "Can't get the style sheet template for page: " + page);
				((HttpServletResponse)res).sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"The system was unable to process your order, please call Customer Service, *611 from your mobile phone, to activate");
				return;
			}
			String pageType = "HTML";
//			String pageType = SPProps.getProperty("VCAST" + "_" + "PAGE_TYPE");
//			if((pageType == null ) || pageType.equals(""))
//				pageType = "HTML"; 
			styleTemplate = styleTemplate.trim() + pageType;
			//styleSheet = SPProps.getProperty(styleTemplate);
			styleSheet = styleTemplate.trim() + ".xsl";
		}
		else
		{
			// Unknown service
			L.error ("doFilter():" + logTag + "Stylesheet Filter found Unknow browsertype.");
			((HttpServletResponse)res).sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"The system was unable to process your order, please call Customer Service, *611 from your mobile phone, to activate");
			return;
		}

		if (styleSheet == null || styleSheet.trim().equals(""))
		{
			L.error ("doFilter():" + logTag + "Style Sheet not found for page : " + page);
			((HttpServletResponse)res).sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"The system was unable to process your order, please call Customer Service, *611 from your mobile phone, to activate");
			return;
		}
		
		stylePath = stylePath.trim() + styleSheet.trim();
		stylePath = filterConfig.getServletContext().getRealPath(stylePath);
		L.debug ("doFilter():" + logTag + "Style Path = " + stylePath);
		new BufferedHttpResponseWrapper((HttpServletResponse) res);
		byte[] origXML = responseWrapper.getBuffer();
		if (origXML == null || origXML.length == 0) {
			chain.doFilter(req, res);
			return;
		}
        try {
            // do the XSLT transformation
            Transformer trans = StylesheetCache.newTransformer(stylePath);
            ByteArrayInputStream origXMLIn = new ByteArrayInputStream(origXML);
            Source styleSource = new StreamSource(origXMLIn);
            ByteArrayOutputStream resultBuf = new ByteArrayOutputStream();
            trans.transform(styleSource, new StreamResult(resultBuf));
            String multipart = (String)req.getAttribute("multipart");
            if((multipart == null) || multipart.equalsIgnoreCase("false"))
       		   res.setContentLength(resultBuf.size());
            res.setContentType(contentType);
            res.getOutputStream().write(resultBuf.toByteArray());
            res.flushBuffer();
	        L.debug ("doFilter():" + logTag + " After Flushing the response");
        } catch (TransformerException te) {
			L.error("doFilter():" + logTag + "Exception during Transformation.", te);
            throw new ServletException(te);
        }
    }

    /**
     * The counterpart to the init() method.
     */
    public void destroy() {
        this.filterConfig = null;
    }

	private String getXSLTTemplate(String page )
	{
		String browser = null;
 		StringTokenizer st = new StringTokenizer(page,"/");
        int count = 0; 
       	while(st.hasMoreTokens()) {
       		if(count == 1)
     	 	{  
     	   		browser = st.nextToken();
     	   		StringTokenizer st1 = new StringTokenizer(browser,".");
     	   		if(st1.hasMoreTokens())
     				browser = st1.nextToken();
	     	  	return browser;
     		}
  			count ++;
         	st.nextToken();
		}
	 	return browser;
	}
}


