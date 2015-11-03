<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" import="com.vzw.selfProvisioning.mobileWeb.*, com.vzw.selfProvisioning.utils.*, com.vzw.edr.selfProv.utils.*, org.apache.log4j.*" %>
<%!
	static Logger L = Logger.getLogger(SPProps.feLoggerBase + ".chargesForServiceTC_jsp");		
%>
<%
	String imgBaseUrl = Util.getContextURL(request);	
%>		

<document>
	<para>
		<logoImg><%= imgBaseUrl %>images/vzwLogo.jpg</logoImg>	
		<paraValue>CHARGES FOR THE SERVICE. The Services are charged as subscription services to 
		 access the Internet via Your handset. Whenever You launch Your browser, You are charged
		 airtime usage fees.  You can call Customer Service to determine the specific rate 
		 You will pay according to Your particular calling and/or data plan.</paraValue>
		<tcLink><%=response.encodeURL(SPProps.termsAndConditionsUrl)%></tcLink>
	</para>
</document>
