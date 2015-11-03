<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" import="com.vzw.selfProvisioning.mobileWeb.*, com.vzw.selfProvisioning.utils.*, com.vzw.edr.selfProv.utils.*, org.apache.log4j.*" %>
<%!
	static Logger L = Logger.getLogger(SPProps.feLoggerBase + ".useOfServicesTC_jsp");		
%>
<%
	String imgBaseUrl = Util.getContextURL(request);				
%>
<document>
	<para>
		<logoImg><%= imgBaseUrl %>images/vzwLogo.jpg</logoImg>														
		<paraValue>YOUR USE OF THE SERVICES. You agree that while using the Services, You will 
		 not violate any applicable law, regulation, code or rule, or post or transmit any 
		 commercial, advertising or promotional materials, including, without limitation, "Spam"
		 or mass distributions.  You agree that We are not responsible or liable for deactivation 
		 or deletion of accounts or for loss of emails, communications, postings, data or 
		 information which may occur as a result of or arising out of the administration of the 
		 Services.</paraValue>
		<tcLink><%=response.encodeURL(SPProps.termsAndConditionsUrl)%></tcLink>
	</para>
</document>
