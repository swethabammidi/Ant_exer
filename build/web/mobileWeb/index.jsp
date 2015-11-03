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
		<% if (service == Util.CSP) { %>
		<paraValue>Get access to Mobile Web for the latest News, Sports and Weather.
		 You can also get info on Movies, Dining, Business, Shopping and more.
		 Even read E-mail, all for only $$<%=SPProps.defaultPrice%> a month! If you 
		 would like to sign up, select </paraValue>
		<% } else if(service == Util.WSP){ %>
		<paraValue>Get access to Mobile Web 2.0 for the latest News, Sports and Weather.
		 You can also get info on Movies, Dining, Business, Shopping and more. 
		 Even read E-mail, all for only $<%=SPProps.defaultPrice%> a month! If you 
		 would like to sign up, select </paraValue>
		<% } %>
		<% if(service != Util.VCAST){ %>	
		<paraValue1> below.</paraValue1>
		<tcLink><%=response.encodeURL(SPProps.termsAndConditionsUrl)%></tcLink>
		<moreInfoLink><%=response.encodeURL(SPProps.moreInfo1Url)%></moreInfoLink>
		<% } %>
	</para>
</document>
