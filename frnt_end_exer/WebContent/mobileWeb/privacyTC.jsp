<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" import="com.vzw.selfProvisioning.mobileWeb.*, com.vzw.selfProvisioning.utils.*, com.vzw.edr.selfProv.utils.*, org.apache.log4j.*" %>
<%!
	static Logger L = Logger.getLogger(SPProps.feLoggerBase + ".privacyTC_jsp");		
%>
<%
	String imgBaseUrl = Util.getContextURL(request);				
%>

<document>
	<para>
		<logoImg><%= imgBaseUrl %>images/vzwLogo.jpg</logoImg>											
		<paraValue>PRIVACY. It is Our policy to respect Your privacy. We have established a Privacy 
		 Statement, which is available at: http://www.verizonwireless.com/jsp/privacy.jsp.</paraValue>
		<tcLink><%=response.encodeURL(SPProps.termsAndConditionsUrl)%></tcLink>
	</para>
</document>
