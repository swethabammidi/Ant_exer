<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" import="com.vzw.selfProvisioning.mobileWeb.*, com.vzw.selfProvisioning.utils.*, com.vzw.edr.selfProv.utils.*, org.apache.log4j.*" %>
<%!
	static Logger L = Logger.getLogger(SPProps.feLoggerBase + ".confirm_jsp");		
%>
<%
		int service = Util.getService(request);
		L.debug ("Service Requested = " + service);
		String imgBaseUrl = Util.getContextURL(request);	

		
%>

<document>
	<para>
		<paraValue1>Select </paraValue1>
		<% if (service == Util.CSP) { %>
		<paraValue2> to validate your request to subscribe. By selecting the "Confirm" option 
		 below, I agree to having $$<%=SPProps.defaultPrice%> billed to my VZW account each month 
		 and understand that when I use Mobile Web, airtime charges may apply, according to my 
		 price plan.</paraValue2>
		<% } else if (service == Util.WSP)  { %>
		<paraValue2> to validate your request to subscribe.  By selecting the "Confirm" option 
		 below, I agree to having $<%=SPProps.defaultPrice%> billed to my VZW account each month 
		 and understand that when I use Mobile Web 2.0, airtime charges may apply, according to my 
		 price plan.</paraValue2>  
		<% } else if (service == Util.VCAST)  { %>
		<logoImg><%= imgBaseUrl %>images/vzwLogo.jpg</logoImg>			
		<paraValue2> to validate your request to subscribe.  By selecting the "Confirm" option 
		 below, I agree to having $<%=SPProps.vcastPrice%> billed to my VZW account each month 
		 and understand that when I use Mobile Web 2.0, airtime charges may apply, according to my 
		 price plan.</paraValue2>  
		<% } %>
        <paraValue3>To process your order, select </paraValue3>
		<paraValue4> below.</paraValue4>
		<accLink><%=response.encodeURL(SPProps.selfActivateUrl)%></accLink>
		<canLink><%=response.encodeURL(SPProps.cancelUrl)%></canLink>
	</para>
</document>
