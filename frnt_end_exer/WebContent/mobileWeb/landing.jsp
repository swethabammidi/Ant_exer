<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" import="com.vzw.selfProvisioning.mobileWeb.*, com.vzw.selfProvisioning.utils.*, com.vzw.edr.selfProv.utils.*, org.apache.log4j.*,java.util.HashMap" %>
<%!
	static Logger L = Logger.getLogger(SPProps.feLoggerBase + ".index_jsp");		

%>
<%
	int browserType = Util.getBrowserType(request);
	L.debug ("Browser Type = " + browserType);
	
	String ssUrl = null;
	String logTag = SPProps.getLogTag(session);
	HashMap dispMap = Util.getDisplayMap(request.getHeader("user-agent"),logTag);
	if (SPProps.selfServeURL == null || SPProps.selfServeURL.trim().equals(""))
	{
		// Eg. http://quickiemart.ddc.vzwcorp.com:13103/selfProvisioning_SIT/Self
		String reqUrl = request.getRequestURL().toString();
		int idx1 = reqUrl.indexOf("://");
		String tUrl = null;
		if (idx1 != -1)
		{
			tUrl = reqUrl.substring(idx1+3);
		}
		if (tUrl == null || tUrl.trim().equals(""))
		{
			tUrl = "http://wapregister.verizonwireless.com/";
		}
		idx1 = tUrl.indexOf("/");
		if (idx1 != -1)
		{
			tUrl = tUrl.substring(0, idx1);
		}
		ssUrl = "http://" + tUrl + "/selfServe/forms/myacc.jsp";
	}
	else
		ssUrl = SPProps.selfServeURL.trim();
	L.debug("SelfServe URL = " + ssUrl);
	boolean showSelfServe = false;
	if (SPProps.showSelfServe != null && SPProps.showSelfServe.trim().equalsIgnoreCase("true"))
	{
		showSelfServe = true;
	}
%>
<document>
		<mimg>images/<%=(String)dispMap.get("mainImg")%></mimg>
		<title>MyAccount</title>
		<% if(showSelfServe) { %>
			<link accesskey="1"><%=response.encodeURL(ssUrl)%></link>
			<limage>images/<%=(String)dispMap.get("myAcctImg")%></limage>
			<dimage>images/<%=(String)dispMap.get("myAcctDescImg")%></dimage>		
		<% } %>		
		<link accesskey="2"><%=response.encodeURL(SPProps.indexUrl)%></link>
		<limage>images/<%=(String)dispMap.get("mwImg")%></limage>
		<dimage>images/<%=(String)dispMap.get("mwDescImg")%></dimage>
</document>
