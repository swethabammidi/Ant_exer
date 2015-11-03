<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" import="com.vzw.selfProvisioning.mobileWeb.*, com.vzw.selfProvisioning.utils.*, com.vzw.edr.selfProv.utils.*, org.apache.log4j.*" %>
<%!
	static Logger L = Logger.getLogger(SPProps.feLoggerBase + ".dailyTCDemo_jsp");
%>

<%
	String logTag = (String) request.getAttribute("logtag");
	if(logTag==null) 
		logTag="";
	L.debug(logTag + "Enter VOD Daily T&C Page...");

	int service = Util.getService(request);
	L.debug ("Service Requested = " + service);
	String imgBaseUrl = Util.getContextURL(request);
	
	L.debug(logTag + "imgBaseUrl = "+imgBaseUrl);
%>

<document>
	<para>
		<logoImg><%= imgBaseUrl %>images/vzwLogo.jpg</logoImg>
		<paraValue>By clicking 'Accept' you agree to Customer Agreement, Calling Plan, and Get It Now® Agreements. 
		 Customer must be within the V CAST Coverage Area in order to download or stream video clips. 
		 The V CAST Coverage Area is not available everywhere. Customer can download 3-D Games anywhere within the V CAST or 
		 Get It Now Coverage Areas. Not all video clips are available for download. Download fees for all other Get It Now 
		 applications still apply. Premium video clips are available for an extra charge. 
		 V CAST Alerts are charged in accordance with your TXT Messaging subscription.<b>V CAST cannot be used: (1) for access to the Internet, intranets or other data networks except as 
		 permitted via Get It Now and getWeb; (2) for any applications that tether your phone to laptops, personal computers 
		 or other devices for any purpose; (3) for uploading, downloading or streaming of movies, music or games unless offered 
		 through Get It Now; (4) for sustained, high bandwidth applications, including, without limitation, Web camera posts or 
		 broadcasts, automatic data feeds, Voice over IP (VoIP), or peer-to-peer (P2P) file sharing; and/or (5) as a substitute 
		 or backup for private lines or dedicated data connections. We reserve the right to limit throughput or amount of data 
		 transferred, deny or terminate data services, without notice, for anyone we believe is using the V CAST service in any 
		 manner prohibited above, whose usage adversely impacts our network or service levels or whose usage exceeds reasonable 
		 levels. We also reserve the right to terminate service upon expiration of Customer Agreement term.</b>V CAST is for individual use only and is not for resale. Verizon Wireless reserves the right to change 
		 these Terms and Conditions at any time.</paraValue>
		
		<accLink_1><%=response.encodeURL(SPProps.selfActivateUrl)%></accLink_1>
		<accLink_2><%=response.encodeURL(SPProps.cancelUrl)%></accLink_2>
	</para>
</document>
