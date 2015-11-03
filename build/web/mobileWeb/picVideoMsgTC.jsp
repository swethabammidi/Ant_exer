<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" import="com.vzw.selfProvisioning.mobileWeb.*, com.vzw.selfProvisioning.utils.*, com.vzw.edr.selfProv.utils.*, org.apache.log4j.*" %>
<%!
	static Logger L = Logger.getLogger(SPProps.feLoggerBase + ".confidentialityTC_jsp");		
%>
<document>
	<para>
		<paraValue>CUSTOMER AGREEMENT AND PICTURE/VIDEO MESSAGING WEBSITE USE AGREEMENT. THE SERVICE IS SUBJECT TO THE TERMS AND 
		 CONDITIONS OF YOUR VERIZON WIRELESS CUSTOMER AGREEMENT AND CALLING PLAN AND PICTURE/VIDEO MESSAGING WEBSITE USE AGREEMENT, 
		 WHICH ARE INCORPORATED HEREIN BY REFERENCE.</paraValue>
		<tcLink><%=response.encodeURL(SPProps.termsAndConditionsUrl)%></tcLink>
	</para>
</document>
