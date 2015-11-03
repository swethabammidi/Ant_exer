<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" import="com.vzw.selfProvisioning.mobileWeb.*, com.vzw.selfProvisioning.utils.*, com.vzw.edr.selfProv.utils.*, org.apache.log4j.*" %>
<%!
	static Logger L = Logger.getLogger(SPProps.feLoggerBase + ".termsAndConditions_jsp");
%>

<%
%>

<document>
	<para>
		<paraValue>By clicking on the "I Accept" button below, You are verifying that you have read and agree to all of the 
		 Terms and Conditions sections listed below, that you are attaching your electronic signature to these Terms and 
		 Conditions, and that you acknowledge that the Customer Agreement and Picture/Video Messaging Website Use Agreement 
		 will apply to the Pix Place Unlimited Storage service ("Service"). </paraValue>
		<paraValue1> Verizon Wireless reserves the right to revert your storage capacity to the standard level or to enforce 
		 any other rights we may have under the Agreements without notice if we believe that you are using the Service in any 
		 manner prohibited by the Agreements. You understand that if you do not agree to these Terms and Conditions, you 
		 should click on the "Decline" button below to discontinue your order with Verizon Wireless.</paraValue1>
		<pixPlaceRegLink><%=response.encodeURL(SPProps.pixPlaceRegTCUrl)%></pixPlaceRegLink>
		<picVideoMsgLink><%=response.encodeURL(SPProps.picVideoMsgTCUrl)%></picVideoMsgLink>
		<billingLink><%=response.encodeURL(SPProps.billingTCUrl)%></billingLink>
		<elecTransactionsLink><%=response.encodeURL(SPProps.elecTransactionsTCUrl)%></elecTransactionsLink>
		<entireTCLink><%=response.encodeURL(SPProps.entireTCUrl)%></entireTCLink>
		<accLink><%=response.encodeURL(SPProps.selfActivateUrl)%></accLink>
		<decLink><%=response.encodeURL(SPProps.cancelUrl)%></decLink>
	</para>
</document>
