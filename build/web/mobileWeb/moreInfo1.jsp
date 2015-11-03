<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" import="com.vzw.selfProvisioning.mobileWeb.*, com.vzw.selfProvisioning.utils.*, com.vzw.edr.selfProv.utils.*, org.apache.log4j.*" %>
<%!
	static Logger L = Logger.getLogger(SPProps.feLoggerBase + ".moreInfo1_jsp");		
%>
<%
	int service = Util.getService(request);
	L.debug ("Service Requested = " + service);
	String imgBaseUrl = Util.getContextURL(request);				
%>

<document>
	<para>
		<% if (service == Util.CSP) { %>
		<paraValue>Verizon Wireless offers Mobile Web, a portal designed specifically to be used 
		 from your phone. You will have access to a categorized list of wireless sites optimized 
		 for use on your phone; plus you can search for and bookmark other sites of your choice, 
		 all while on the go!  Subscribers pay a monthly access charge of 
		 $$<%=SPProps.defaultPrice%>. If you would like to start accessing Mobile Web, 
		 select </paraValue>
		<% } else if(service == Util.WSP){ %>
		<paraValue>Verizon Wireless offers Mobile Web 2.0, a portal designed specifically to be 
		 used from your phone. You will have access to a categorized list of wireless sites 
		 optimized for use on your phone; plus you can search for and bookmark other sites of 
		 your choice, all while on the go!  Subscribers pay a monthly access charge of 
		 $<%=SPProps.defaultPrice%>. If you would like to start accessing Mobile Web 2.0, 
		 select </paraValue>
		<% } else if(service == Util.VCAST){ %>
		<logoImg><%= imgBaseUrl %>images/vzwLogo.jpg</logoImg>									
		<paraValue>The V CAST experience is mobile entertainment with the richness of broadband only from Verizon Wireless. 
		 The hottest entertainment clips, latest sports highlights, breaking news, weather, and more. 
		 Subscribers pay a monthly access charge of $<%=SPProps.vcastPrice%>.
		 If you would like to start accessing V CAST, select </paraValue>
		<paraValue1>. By selecting Subscribe you are confirming you are the account 
		 holder and authorized to sign up for this service.</paraValue1>
		<tcLink><%=response.encodeURL(SPProps.termsAndConditionsUrl)%></tcLink>
		<backLink><%=response.encodeURL(SPProps.indexUrl)%></backLink>
		<canLink><%=response.encodeURL(SPProps.cancelUrl)%></canLink>
		<% } %>
		<% if(service != Util.VCAST) { %>
   		<paraValue1> below. Select </paraValue1>  
		<paraValue2> below if you want to see a sample of sites available on our wireless portal.</paraValue2>
		<tcLink><%=response.encodeURL(SPProps.termsAndConditionsUrl)%></tcLink>
		<moreInfo2Link><%=response.encodeURL(SPProps.moreInfo2Url)%></moreInfo2Link>
		<canLink><%=response.encodeURL(SPProps.cancelUrl)%></canLink>
		<% } %>
	</para>
</document>
