<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" import="com.vzw.selfProvisioning.mobileWeb.*, com.vzw.selfProvisioning.utils.*, com.vzw.edr.selfProv.utils.*, org.apache.log4j.*" %>
<%!
	static Logger L = Logger.getLogger(SPProps.feLoggerBase + ".termsAndConditions_jsp");
%>

<%
	String nextV = request.getParameter("next");
	if (nextV == null || nextV.trim().equals(""))
	{
		nextV = "0";
	}
	String next1 = response.encodeURL(SPProps.termsAndConditionsUrl + "&next=1");
	String next2 = response.encodeURL(SPProps.termsAndConditionsUrl + "&next=2");
	int service = Util.getService(request);
	L.debug ("Service Requested = " + service);
	String imgBaseUrl = Util.getContextURL(request);					

%>

<document>
	<nextVal><%=nextV%></nextVal>
	<para>
		<paraValue>By clicking on the "Accept" button below, you are verifying that you
		 have read and agree to all of the Terms and Conditions sections listed below,
		 that you are attaching your electronic signature to these Terms and Conditions,
		 and that you acknowledge that the Customer Agreement will apply to the
		 Mobile Web service. </paraValue>
		<% if (service == Util.CSP) { %>
		<paraValue1> YOU AGREE THAT USING OR ACCESSING THE SERVICES, INCLUDING, BUT NOT LIMITED 
		 TO EMAIL, CHAT ROOMS, MESSAGE BOARDS AND OTHER COMMUNITY SERVICES IS THE EQUIVALENT OF
		 YOUR "WRITTEN SIGNATURE" IN ACCORDANCE WITH THE ELECTRONIC TRANSACTION RULES STATED 
		 ABOVE. You understand that if you do not agree to these Verizon Wireless
		 Terms and Conditions and the Privacy Statement, you should click on the "Decline"
		 button below to discontinue your order with Verizon Wireless.</paraValue1>
		<% } else if (service == Util.WSP) { %>
		<paraValue1> YOU AGREE THAT USING OR ACCESSING THE SERVICES, INCLUDING, BUT NOT LIMITED 
		 TO EMAIL, CHAT ROOMS, MESSAGE BOARDS AND OTHER COMMUNITY SERVICES IS THE EQUIVALENT OF
		 YOUR "WRITTEN SIGNATURE" IN ACCORDANCE WITH THE ELECTRONIC TRANSACTION RULES STATED 
		 ABOVE. You understand that if you do not agree to these Verizon Wireless
		 Terms and Conditions and the Privacy Statement, you should click on the "Decline"
		 button below to discontinue this order for Mobile Web 2.0.</paraValue1>
		<% } else if (service == Util.VCAST) { %>
		<logoImg><%= imgBaseUrl %>images/vzwLogo.jpg</logoImg>													
		<paraValue1><![CDATA[By clicking 'Accept' you agree to Customer Agreement, Calling Plan, and Get It Now&reg; Agreements. 
		 Customer must be within the V CAST Coverage Area in order to download or stream video clips. 
		 The V CAST Coverage Area is not available everywhere. Customer can download 3-D Games anywhere within the V CAST or 
		 Get It Now Coverage Areas. Not all video clips are available for download. Download fees for all other Get It Now 
		 applications still apply. Premium video clips are available for an extra charge. 
		 V CAST Alerts are charged in accordance with your TXT Messaging subscription.]]></paraValue1>
		<paraValue2>V CAST cannot be used: (1) for access to the Internet, intranets or other data networks except as 
		 permitted via Get It Now and getWeb; (2) for any applications that tether your phone to laptops, personal computers 
		 or other devices for any purpose; (3) for uploading, downloading or streaming of movies, music or games unless offered 
		 through Get It Now; (4) for sustained, high bandwidth applications, including, without limitation, Web camera posts or 
		 broadcasts, automatic data feeds, Voice over IP (VoIP), or peer-to-peer (P2P) file sharing; and/or (5) as a substitute 
		 or backup for private lines or dedicated data connections. We reserve the right to limit throughput or amount of data 
		 transferred, deny or terminate data services, without notice, for anyone we believe is using the V CAST service in any 
		 manner prohibited above, whose usage adversely impacts our network or service levels or whose usage exceeds reasonable 
		 levels. We also reserve the right to terminate service upon expiration of Customer Agreement term.</paraValue2>
		<paraValue3>V CAST is for individual use only and is not for resale. Verizon Wireless reserves the right to change 
		 these Terms and Conditions at any time.</paraValue3>
 		<% } %>
		<custAgreementLink><%=response.encodeURL(SPProps.custAgreementTCUrl)%></custAgreementLink>
		<privacyLink><%=response.encodeURL(SPProps.privacyTCUrl)%></privacyLink>
		<chargesForServiceLink><%=response.encodeURL(SPProps.chargesForServiceTCUrl)%></chargesForServiceLink>
		<billingLink><%=response.encodeURL(SPProps.billingTCUrl)%></billingLink>
		<monConsentLink><%=response.encodeURL(SPProps.monConsentTCUrl)%></monConsentLink>
		<useOfServicesLink><%=response.encodeURL(SPProps.useOfServicesTCUrl)%></useOfServicesLink>
		<discWarrantiesLink><%=response.encodeURL(SPProps.discWarrantiesTCUrl)%></discWarrantiesLink>
		<limLiabilitiesLink><%=response.encodeURL(SPProps.limLiabilitiesTCUrl)%></limLiabilitiesLink>
		<copyrightLink><%=response.encodeURL(SPProps.copyrightTCUrl)%></copyrightLink>
		<trademarksLink><%=response.encodeURL(SPProps.trademarksTCUrl)%></trademarksLink>
		<elecTransactionsLink><%=response.encodeURL(SPProps.elecTransactionsTCUrl)%></elecTransactionsLink>
		<confidentialityLink><%=response.encodeURL(SPProps.confidentialityTCUrl)%></confidentialityLink>
		<entireTCLink><%=response.encodeURL(SPProps.entireTCUrl)%></entireTCLink>
		<premContentLink><%=response.encodeURL(SPProps.premContentTCUrl)%></premContentLink>
		<accLink><%=response.encodeURL(SPProps.selfActivateUrl)%></accLink>
		<decLink><%=response.encodeURL(SPProps.cancelUrl)%></decLink>
		<nextLink1><![CDATA[<%=next1%>]]></nextLink1>
		<nextLink2><![CDATA[<%=next2%>]]></nextLink2>
	</para>
</document>
