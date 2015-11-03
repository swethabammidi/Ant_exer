<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" import="com.vzw.selfProvisioning.mobileWeb.*, com.vzw.selfProvisioning.utils.*, com.vzw.edr.selfProv.utils.*, org.apache.log4j.*" %>
<%!
	static Logger L = Logger.getLogger(SPProps.feLoggerBase + ".billingTC_jsp");		
%>
<%
	int service = Util.getService(request);
	L.debug ("Service Requested = " + service);
	String imgBaseUrl = Util.getContextURL(request);
%>
<document>
	<para>
		<% if (service == Util.CSP) { %>
		<paraValue>BILLING. Upon subscribing to the Services You will be billed a subscription 
		 charge each month until you cancel Your subscription. You understand and agree that 
		 Your monthly subscription fee shall be included in your Verizon Wireless bill.</paraValue>
		<% } else if(service == Util.WSP) { %>
		<paraValue>BILLING. Upon subscribing to the Services You will be billed a subscription 
		 charge each month until you cancel Your subscription.  Should You decide to subscribe 
		 to a premium content service, You understand and agree that Your monthly or annual 
		 subscription fee shall be included in your Verizon Wireless bill.</paraValue>
		<% } else if (service == Util.VCAST){ %>
		<logoImg><%= imgBaseUrl %>images/vzwLogo.jpg</logoImg>
		<paraValue>BILLING. Upon subscribing to the Services You will be billed a subscription 
		 charge each month until you cancel Your subscription.  Should You decide to subscribe 
		 to a premium content service, You understand and agree that Your monthly or annual 
		 subscription fee shall be included in your Verizon Wireless bill.</paraValue>
		<% } else if(service == Util.PPUS) { %>
		<paraValue>CHARGES AND BILLING. Upon subscribing to the Service, You will be billed a subscription 
		 charge of $<%=SPProps.ppusPrice%> each month on your Verizon Wireless bill until you cancel 
		 Your subscription.</paraValue>
		<% } %>
		<tcLink><%=response.encodeURL(SPProps.termsAndConditionsUrl)%></tcLink>
	</para>
</document>
