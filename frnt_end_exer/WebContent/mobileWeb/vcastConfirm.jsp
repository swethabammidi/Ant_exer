<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" import="com.vzw.selfProvisioning.mobileWeb.*, com.vzw.selfProvisioning.utils.*, com.vzw.edr.selfProv.utils.*, org.apache.log4j.*" %>
<%!
	static Logger L = Logger.getLogger(SPProps.feLoggerBase + ".index_jsp");		
%>
<%
	int service = Util.getService(request);
	L.debug ("Service Requested = " + service);
	String imgBaseUrl = Util.getContextURL(request);
%>
<document>
	<para>
		<logoImg><%= imgBaseUrl %>images/vzwLogo.jpg</logoImg>
		<paraValue1>Select </paraValue1>
		<paraValue2> to validate your are the account holder and authorized to signup for this service.</paraValue2>
    	<tcLink><%=response.encodeURL(SPProps.termsAndConditionsUrl)%></tcLink>
 		<cancelLink><%=response.encodeURL(SPProps.cancelUrl)%></cancelLink>
	</para>
</document>
