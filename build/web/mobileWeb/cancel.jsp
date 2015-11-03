<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" import="com.vzw.selfProvisioning.mobileWeb.*, com.vzw.selfProvisioning.utils.*, com.vzw.edr.selfProv.utils.*, org.apache.log4j.*" %>
<%!
	static Logger L = Logger.getLogger(SPProps.feLoggerBase + ".cancel_jsp");		
%>
<%
	int service = Util.getService(request);
	L.debug ("Service Requested = " + service);
	String imgBaseUrl = Util.getContextURL(request);	

%>


<document>
	<% if (service == Util.CSP) { %>
	<para>
		<paraValue>You can re-launch your browser anytime in the future to subscribe to the 
		 Mobile Web.</paraValue>
	</para>
	<% } else if (service == Util.WSP){ %>
	<para>
		<paraValue>You can re-launch your browser anytime in the future to subscribe to the 
		 Mobile Web 2.0.</paraValue>
	</para>
	<% } else if (service == Util.VCAST){ %>
	<para>
		<paraValue>You can re-launch V CAST Video anytime in the future to subscribe to the service.</paraValue>
	</para>
	<logoImg><%= imgBaseUrl %>images/vzwLogo.jpg</logoImg>		
	<% } else if (service == Util.PPUS){ %>
	<para>
		<paraValue></paraValue>
	</para>
	<backLinkText>Return to Pix Place</backLinkText>
	<backLink><%=response.encodeURL(SPProps.pixPlaceDURL)%></backLink>
	<% } %>
</document>
