<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" import="com.vzw.selfProvisioning.mobileWeb.*, com.vzw.selfProvisioning.utils.*, com.vzw.edr.selfProv.utils.*, org.apache.log4j.*" %>
<%!
	static Logger L = Logger.getLogger(SPProps.feLoggerBase + ".entireTC_jsp");		
%>
<%
	int service = Util.getService(request);
	L.debug ("Service Requested = " + service);
%>

<document>
	<para>
		<paraValue1>PIX PLACE REGISTRATION REQUIRED. To subscribe to the Service, you must have registered for an account on 
		 the Verizon Wireless Pix Place website located at www.vzwpix.com</paraValue1>
		<paraValue2>CUSTOMER AGREEMENT AND PICTURE/VIDEO MESSAGING WEBSITE USE AGREEMENT. THE SERVICE IS SUBJECT TO THE TERMS 
		 AND CONDITIONS OF YOUR VERIZON WIRELESS CUSTOMER AGREEMENT AND CALLING PLAN AND PICTURE/VIDEO MESSAGING WEBSITE USE 
		 AGREEMENT, WHICH ARE INCORPORATED HEREIN BY REFERENCE.</paraValue2>
		<paraValue3>CHARGES AND BILLING. Upon subscribing to the Service, You will be billed a subscription charge of 
		 $<%=SPProps.ppusPrice%> each month on your Verizon Wireless bill until you cancel Your subscription.</paraValue3>
		<paraValue4>ELECTRONIC TRANSACTION RULES. You agree that in any situation where Your signature may be required 
		 to process a transaction, compliance with a commercially reasonable attribution procedure agreed to or 
		 adopted by You and Verizon Wireless, or established by law for authenticating a record, authenticates the 
		 record as a matter of law.</paraValue4>
		<tcLink><%=response.encodeURL(SPProps.termsAndConditionsUrl)%></tcLink>
	</para>
</document>
