<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" import="com.vzw.selfProvisioning.mobileWeb.*, com.vzw.selfProvisioning.utils.*, com.vzw.edr.selfProv.utils.*, org.apache.log4j.*" %>
<%!
	static Logger L = Logger.getLogger(SPProps.feLoggerBase + ".moreInfo2_jsp");		
%>
<%
	int service = Util.getService(request);
	L.debug ("Service Requested = " + service);
	String imgBaseUrl = Util.getContextURL(request);				

%>


<document>
	<para>
		<% if (service == Util.CSP) { %>
		<paraValue>Mobile Web provides quick access to over 30 sites including: Yahoo!, Hotmail, 
		 AOL Mail, The Weather Channel, CNN, ESPN, ABC News, MSNBC, MSN Money, E*Trade, 
		 Amazon, NY Times, Edmunds, Schwab, Fidelity, Movies.com, 
		 TD Waterhouse, Superpages, Airdate Chat, Horoscopes, Hollywood.com, Moviefone. You can 
		 also search for and bookmark any other wireless sites from your phone. Visit </paraValue>
		<visitLinkText>vzw.msn.com</visitLinkText>
		<paraValue1> from your desktop computer for additional information on Mobile Web access. 
		 If you would like to start your subscription, select </paraValue1>
		<% } else if(service == Util.WSP) { %>
		<paraValue>Mobile Web 2.0 provides quick access to over 50 sites including: Yahoo!, 
		 Hotmail, AOL Mail, The Weather Channel, CNN, ESPN, Fox Sports, ABC News, MSNBC, 
		 Amazon, Weatherbug, AccuWeather, CBS Sportsline, 10 Best, Edmunds, 
		 Schwab, Fidelity, CBS Marketwatch, Superpages, E! Online,Airdate Chat, Horoscopes, 
		 Vindigo Movies, Blender, and Restaurant Row. You can also search for and bookmark any 
		 other wireless sites from your phone. Visit </paraValue>
		<visitLinkText>getitnow.vzwshop.com/getweb.main.do</visitLinkText>
		<paraValue1> from your desktop computer for additional information on Mobile Web 2.0 access. 
		 If you would like to start your subscription, select </paraValue1>
		<% } else if(service == Util.VCAST) { %>
		<logoImg><%= imgBaseUrl %>images/vzwLogo.jpg</logoImg>											
		<paraValue>Mobile Web 2.0 provides quick access to over 50 sites including: Yahoo!, 
		 Hotmail, AOL Mail, The Weather Channel, CNN, ESPN, Fox Sports, ABC News, MSNBC, 
		 Amazon, Weatherbug, AccuWeather, CBS Sportsline, 10 Best, Edmunds, 
		 Schwab, Fidelity, CBS Marketwatch, Superpages, E! Online,Airdate Chat, Horoscopes, 
		 Vindigo Movies, Blender, and Restaurant Row. You can also search for and bookmark any 
		 other wireless sites from your phone. Visit </paraValue>
		<visitLinkText>getitnow.vzwshop.com/getweb.main.do</visitLinkText>
		<paraValue1> from your desktop computer for additional information on Mobile Web 2.0 access. 
		 If you would like to start your subscription, select </paraValue1>
		 
		<% } %>
		<tcLink><%=response.encodeURL(SPProps.termsAndConditionsUrl)%></tcLink>
		<canLink><%=response.encodeURL(SPProps.cancelUrl)%></canLink>
		<backLink><%=response.encodeURL(SPProps.moreInfo1Url)%></backLink>
	</para>
</document>
