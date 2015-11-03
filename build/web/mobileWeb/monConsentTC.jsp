<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" import="com.vzw.selfProvisioning.mobileWeb.*, com.vzw.selfProvisioning.utils.*, com.vzw.edr.selfProv.utils.*, org.apache.log4j.*" %>
<%!
	static Logger L = Logger.getLogger(SPProps.feLoggerBase + ".monConsentTC_jsp");		
%>
<%
	String imgBaseUrl = Util.getContextURL(request);			
%>

<document>
	<para>
		<logoImg><%= imgBaseUrl %>images/vzwLogo.jpg</logoImg>								
		<paraValue>CONSENT TO MONITORING AND DISCLOSURE. You agree that We may monitor e-mails, 
		 communications, postings, data or information periodically, in order to comply with any 
		 applicable laws, regulations or other governmental orders, and/or to operate the Services 
		 properly, or to protect Ourselves and You. We reserve the right to delete, reject or 
		 eliminate, in whole or in part, any information available or transmitted through the 
		 Services that We, in Our sole discretion, believe is unacceptable or in violation of 
		 these Terms and Conditions.</paraValue>
		<tcLink><%=response.encodeURL(SPProps.termsAndConditionsUrl)%></tcLink>
	</para>
</document>
