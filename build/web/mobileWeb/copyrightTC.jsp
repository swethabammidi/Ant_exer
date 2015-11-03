<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" import="com.vzw.selfProvisioning.mobileWeb.*, com.vzw.selfProvisioning.utils.*, com.vzw.edr.selfProv.utils.*, org.apache.log4j.*" %>
<%!
	static Logger L = Logger.getLogger(SPProps.feLoggerBase + ".copyrightTC_jsp");		
%>
<%
	String imgBaseUrl = Util.getContextURL(request);	
%>

<document>
	<para>
		<logoImg><%= imgBaseUrl %>images/vzwLogo.jpg</logoImg>				
		<paraValue>COPYRIGHT. The materials available through the Services are Our copyrighted 
		 property or the copyrighted property of Our licensors.</paraValue>
		<tcLink><%=response.encodeURL(SPProps.termsAndConditionsUrl)%></tcLink>
	</para>
</document>
