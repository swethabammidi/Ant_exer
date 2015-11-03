<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" import="com.vzw.selfProvisioning.mobileWeb.*, com.vzw.selfProvisioning.utils.*, com.vzw.edr.selfProv.utils.*, org.apache.log4j.*" %>
<%!
	static Logger L = Logger.getLogger(SPProps.feLoggerBase + ".elecTransactionsTC_jsp");		
%>
<%
	String imgBaseUrl = Util.getContextURL(request);		
%>

<document>
	<para>
		<logoImg><%= imgBaseUrl %>images/vzwLogo.jpg</logoImg>					
		<paraValue>ELECTRONIC TRANSACTION RULES. You agree that in any situation where Your 
		 signature may be required to process a transaction, compliance with a commercially 
		 reasonable attribution procedure agreed to or adopted by You and Verizon Wireless, or established by 
		 law for authenticating a record, authenticates the record as a matter of law.</paraValue>
		<tcLink><%=response.encodeURL(SPProps.termsAndConditionsUrl)%></tcLink>
	</para>
</document>
