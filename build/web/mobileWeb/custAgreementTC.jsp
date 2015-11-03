<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" import="com.vzw.selfProvisioning.mobileWeb.*, com.vzw.selfProvisioning.utils.*, com.vzw.edr.selfProv.utils.*, org.apache.log4j.*" %>
<%!
	static Logger L = Logger.getLogger(SPProps.feLoggerBase + ".custAgreementTC_jsp");		
%>
<%
	String imgBaseUrl = Util.getContextURL(request);	
%>


<document>
	<para>
		<logoImg><%= imgBaseUrl %>images/vzwLogo.jpg</logoImg>				
		<paraValue>CUSTOMER AGREEMENT. THE SERVICES ARE SUBJECT TO THE TERMS AND CONDITIONS OF 
		 YOUR VERIZON WIRELESS CUSTOMER AGREEMENT AND CALLING PLAN WHICH ARE INCORPORATED 
		 HEREIN BY REFERENCE.</paraValue>
		<tcLink><%=response.encodeURL(SPProps.termsAndConditionsUrl)%></tcLink>
	</para>
</document>
