<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" import="com.vzw.selfProvisioning.mobileWeb.*, com.vzw.selfProvisioning.utils.*, com.vzw.edr.selfProv.utils.*, org.apache.log4j.*" %>
<%!
	static Logger L = Logger.getLogger(SPProps.feLoggerBase + ".trademarksTC_jsp");		
%>
<%
	String imgBaseUrl = Util.getContextURL(request);				
%>

<document>
	<para>
		<logoImg><%= imgBaseUrl %>images/vzwLogo.jpg</logoImg>														
		<paraValue>TRADEMARKS. Our name, tradenames, trademarks and logo, and all related product 
		 and service names, design marks and slogans are Our trademarks, service marks or 
		 registered trademarks, or are the trademarks, service marks or registered trademarks of 
		 Our parent, Verizon Communications.</paraValue>
		<tcLink><%=response.encodeURL(SPProps.termsAndConditionsUrl)%></tcLink>
	</para>
</document>
