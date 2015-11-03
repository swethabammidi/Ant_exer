<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" import="com.vzw.selfProvisioning.mobileWeb.*, com.vzw.selfProvisioning.utils.*, com.vzw.edr.selfProv.utils.*, org.apache.log4j.*" %>
<%!
	static Logger L = Logger.getLogger(SPProps.feLoggerBase + ".confidentialityTC_jsp");		
%>
<%
	String imgBaseUrl = Util.getContextURL(request);	
%>

<document>
	<para>
		<logoImg><%= imgBaseUrl %>images/vzwLogo.jpg</logoImg>		
		<paraValue>CONFIDENTIALITY. We cannot guarantee Your confidential use of the Services. 
		 We shall not be responsible for any harm that You or any person may suffer as a result 
		 of a breach of confidentiality in respect to Your use of the Services.</paraValue>
		<tcLink><%=response.encodeURL(SPProps.termsAndConditionsUrl)%></tcLink>
	</para>
</document>
