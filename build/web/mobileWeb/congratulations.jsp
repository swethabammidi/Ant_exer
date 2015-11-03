<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" import="com.vzw.selfProvisioning.mobileWeb.*, com.vzw.selfProvisioning.utils.*, com.vzw.edr.selfProv.utils.*, org.apache.log4j.*" %>
<%!
	static Logger L = Logger.getLogger(SPProps.feLoggerBase + ".congratulations_jsp");		
%>
<%
	int service = Util.getService(request);
	L.debug ("Service Requested = " + service);
	String imgBaseUrl = Util.getContextURL(request);	
%>


<document>
	<para>
		<paraValue1>Thank you for successfully subscribing!  </paraValue1>
		<% if (service == Util.CSP) { %>
		<paraValue2>Please turn your phone off and back on, and then wait a few minutes before 
		 you use the service. If you see the "Mobile Web Sign Up" page, your order is 
		 still being processed. Please wait a few more minutes and repeat the steps above.</paraValue2>
		<% } else if(service == Util.WSP) { %>
		<paraValue2>Please turn your phone off and back on, and then wait a few minutes before 
		 you use the service. If you see the "Mobile Web 2.0 Sign Up" page, your order is 
		 still being processed. Please wait a few more minutes and repeat the steps above.</paraValue2>
		<% } else if(service == Util.VCAST) { %>
		<logoImg><%= imgBaseUrl %>images/vzwLogo.jpg</logoImg>					
		<paraValue2>Please turn your phone off and back on, and then access V CAST.
		If you see the "V CAST Sign Up" page, your order is still being processed. 
		Please wait a few more minutes and repeat the steps above.</paraValue2>
		<% } else if (service == Util.PPUS){ %>
		<paraValue2>The service may take up to 30 minutes to be 
		 activated. If you login into Pix Place, you can verify that You have the Unlimited Storage by checking 
		 under the My Profile link on the page. If Unlimited Storage does not appear within 30 minutes, wait a 
		 few more minutes and repeat the step above.</paraValue2>
		<backLinkText>Return to Pix Place</backLinkText>
		<backLink><%=response.encodeURL(SPProps.pixPlaceSURL)%></backLink>
		<% } %>
	</para>
</document>




