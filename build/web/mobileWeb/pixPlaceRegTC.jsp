<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" import="com.vzw.selfProvisioning.mobileWeb.*, com.vzw.selfProvisioning.utils.*, com.vzw.edr.selfProv.utils.*, org.apache.log4j.*" %>
<%!
	static Logger L = Logger.getLogger(SPProps.feLoggerBase + ".confidentialityTC_jsp");		
%>

<document>
	<para>
		<paraValue>PIX PLACE REGISTRATION REQUIRED. To subscribe to the Service, you must have registered for an account 
		 on the Verizon Wireless Pix Place website located at www.vzwpix.com</paraValue>
		<tcLink><%=response.encodeURL(SPProps.termsAndConditionsUrl)%></tcLink>
	</para>
</document>
