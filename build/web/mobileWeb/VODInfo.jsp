<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" import="com.vzw.selfProvisioning.mobileWeb.*, com.vzw.selfProvisioning.utils.*, com.vzw.edr.selfProv.utils.*, org.apache.log4j.*" %>
<%!
	static Logger L = Logger.getLogger(SPProps.feLoggerBase + ".VODInfoDemo_jsp");
%>

<%
	String logTag = (String) request.getAttribute("logtag");
	if(logTag==null) 
		logTag="";
	L.debug(logTag + "Enter VOD More Info Page...");

	int service = Util.getService(request);
	L.debug ("Service Requested = " + service);
	String imgBaseUrl = Util.getContextURL(request);
	L.debug(logTag + "imgBaseUrl = "+imgBaseUrl);
%>

<document>
	<para>
		<logoImg><%= imgBaseUrl %>images/vzwLogo.jpg</logoImg>
		<paraValue>Get Access to V CAST Video for the latest News, Sports, Entertainment, and Weather clips anywhere 
		within the V CAST Coverage Area.  Sign up for the $<%= SPProps.vcastPrice %> monthly access plan which includes unlimited 
		access to V CAST, unlimited V CAST basic video clips, and unlimited airtime while using the Get It 
		Now service. Or you can sign up for $<%= SPProps.v24Price %> daily access, which includes unlimited access to V CAST, 
		unlimited V CAST basic video clips, and unlimited airtime for 24 hours from when you enter the Get 
		It Now service.  If you would like to sign up, select one of the Subscription methods below. By 
		selecting Subscribe you are confirming you are the account holder and authorized to sign up for 
		this service.</paraValue>
		
		<accLink_1><%=response.encodeURL(SPProps.vcastDailyTCUrl)%></accLink_1><daily> - $<%= SPProps.v24Price %> Daily Access</daily>
		<accLink_2><%=response.encodeURL(SPProps.vcastMonthlyTCUrl)%></accLink_2><monthly> - $<%= SPProps.vcastPrice %> Monthly Access</monthly>
		<CancelLink><%=response.encodeURL(SPProps.cancelUrl)%></CancelLink>
	</para>
</document>

