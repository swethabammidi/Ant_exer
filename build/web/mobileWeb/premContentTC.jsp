<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" import="com.vzw.selfProvisioning.mobileWeb.*, com.vzw.selfProvisioning.utils.*, com.vzw.edr.selfProv.utils.*, org.apache.log4j.*" %>
<%!
	static Logger L = Logger.getLogger(SPProps.feLoggerBase + ".premContentTC_jsp");		
%>
<%
	String imgBaseUrl = Util.getContextURL(request);				
%>

<document>
	<para>
		<logoImg><%= imgBaseUrl %>images/vzwLogo.jpg</logoImg>										
		<paraValue>PREMIUM CONTENT SERVICES. Should You decide to subscribe to a premium content 
		 service, You understand and agree that Your monthly or annual subscription fee shall 
		 be included in your Verizon Wireless bill.</paraValue>
		<tcLink><%=response.encodeURL(SPProps.termsAndConditionsUrl)%></tcLink>
	</para>
</document>
