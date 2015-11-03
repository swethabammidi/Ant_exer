<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" import="com.vzw.selfProvisioning.mobileWeb.*, com.vzw.selfProvisioning.utils.*, com.vzw.edr.selfProv.utils.*, org.apache.log4j.*" %>
<%!
	static Logger L = Logger.getLogger(SPProps.feLoggerBase + ".entireTC_jsp");		
%>
<%
	int service = Util.getService(request);
	L.debug ("Service Requested = " + service);
	String nextV = request.getParameter("next");
	if (nextV == null || nextV.trim().equals(""))
	{
		nextV = "0";
	}
	String next1 = response.encodeURL(SPProps.entireTCUrl + "&next=1");
	String next2 = response.encodeURL(SPProps.entireTCUrl + "&next=2");
	String next3 = response.encodeURL(SPProps.entireTCUrl + "&next=3");
	String next4 = response.encodeURL(SPProps.entireTCUrl + "&next=4");
	String next5 = response.encodeURL(SPProps.entireTCUrl + "&next=5");
	String next6 = response.encodeURL(SPProps.entireTCUrl + "&next=6");
	String next7 = response.encodeURL(SPProps.entireTCUrl + "&next=7");
	String next8 = response.encodeURL(SPProps.entireTCUrl + "&next=8");
	String next9 = response.encodeURL(SPProps.entireTCUrl + "&next=9");
	String next10 = response.encodeURL(SPProps.entireTCUrl + "&next=10");
	String next11 = response.encodeURL(SPProps.entireTCUrl + "&next=11");
	String imgBaseUrl = Util.getContextURL(request);			
%>

<document>
	<nextVal><%=nextV%></nextVal>
	<para>
		<logoImg><%= imgBaseUrl %>images/vzwLogo.jpg</logoImg>					
		<paraValue1>CUSTOMER AGREEMENT. THE SERVICES ARE SUBJECT TO THE TERMS AND CONDITIONS OF 
		 YOUR VERIZON WIRELESS CUSTOMER AGREEMENT AND CALLING PLAN WHICH ARE INCORPORATED 
		 HEREIN BY REFERENCE.</paraValue1>
		<paraValue2>PRIVACY. It is Our policy to respect Your privacy. We have established a Privacy 
		 Statement, which is available at: http://www.verizonwireless.com/jsp/privacy.jsp.</paraValue2>
		<paraValue3>CHARGES FOR THE SERVICE. The Services are charged as subscription services to 
		 access the Internet via Your handset. Whenever You launch Your browser, You are charged
		 airtime usage fees.  You can call Customer Service to determine the specific rate 
		 You will pay according to Your particular calling and/or data plan.</paraValue3>
		<% if (service == Util.CSP) { %>
		<paraValue4>BILLING. Upon subscribing to the Services You will be billed a subscription 
		 charge each month until you cancel Your subscription. You understand and agree that 
		 Your monthly subscription fee shall be included in your Verizon Wireless bill.</paraValue4>
		<% } else if (service == Util.WSP){ %>
		<paraValue4>BILLING. Upon subscribing to the Services You will be billed a subscription 
		 charge each month until you cancel Your subscription.  Should You decide to subscribe 
		 to a premium content service (only available if you are using the Mobile Web 2.0 Service), 
		 You understand and agree that Your monthly or annual subscription fee shall be included 
		 in your Verizon Wireless bill.</paraValue4>
		<% } else if (service == Util.VCAST){ %>
		<paraValue4>BILLING. Upon subscribing to the Services You will be billed a subscription 
		 charge each month until you cancel Your subscription.  Should You decide to subscribe 
		 to a premium content service (only available if you are using the Mobile Web 2.0 Service), 
		 You understand and agree that Your monthly or annual subscription fee shall be included 
		 in your Verizon Wireless bill.</paraValue4>
		<% } %>
		<paraValue5>CONSENT TO MONITORING AND DISCLOSURE. You agree that We may monitor e-mails, 
		 communications, postings, data or information periodically, in order to comply with any 
		 applicable laws, regulations or other governmental orders, and/or to operate the Services 
		 properly, or to protect Ourselves and You. We reserve the right to delete, reject or 
		 eliminate, in whole or in part, any information available or transmitted through the 
		 Services that We, in Our sole discretion, believe is unacceptable or in violation of 
		 these Terms and Conditions.</paraValue5>
		<paraValue6>YOUR USE OF THE SERVICES. You agree that while using the Services, You will 
		 not violate any applicable law, regulation, code or rule, or post or transmit any 
		 commercial, advertising or promotional materials, including, without limitation, "Spam" 
		 or mass distributions.  You agree that We are not responsible or liable for deactivation 
		 or deletion of accounts or for loss of emails, communications, postings, data or 
		 information which may occur as a result of or arising out of the administration of the 
		 Services.</paraValue6>
		<% if (service == Util.WSP) { %>
		<paraValue_PC>PREMIUM CONTENT SERVICES. Should You decide to subscribe to a premium content 
		 service (only available if you are using the Mobile Web 2.0 Service), You understand and 
		 agree that Your monthly or annual subscription fee shall be included in your Verizon 
		 Wireless bill.</paraValue_PC>
		<% } %>
		<% if (service == Util.VCAST) { %>
		<paraValue_PC>PREMIUM CONTENT SERVICES. Should You decide to subscribe to a premium content 
		 service (only available if you are using the Mobile Web 2.0 Service), You understand and 
		 agree that Your monthly or annual subscription fee shall be included in your Verizon 
		 Wireless bill.</paraValue_PC>
		<% } %>
		<paraValue7>DISCLAIMER OF WARRANTIES. YOU ASSUME ALL RESPONSIBILITY AND RISK FOR THE USE OF 
		 THE SERVICES AND THE INTERNET GENERALLY. THE USE OF THE INFORMATION, PRODUCTS, AND/OR 
		 SERVICES PROVIDED ON OR AVAILABLE THROUGH THE SERVICES ARE PROVIDED ON AN "AS IS" AND 
		 "AS AVAILABLE" BASIS, WITHOUT WARRANTIES OF ANY KIND. NEITHER VERIZON WIRELESS NOR ITS 
		 AFFILIATES WARRANT THAT THE INFORMATION, PRODUCTS, PROCESSES, AND/OR SERVICES AVAILABLE 
		 THROUGH THE SERVICES WILL BE UN-INTERRUPTIBLE, ACCURATE, COMPLETE, USEFUL, FUNCTIONAL OR 
		 ERROR FREE, OR THAT ANY INFORMATION, SOFTWARE OR OTHER MATERIAL ACCESSIBLE THROUGH THE 
		 SERVICES IS FREE OF VIRUSES OR OTHER HARMFUL COMPONENTS. WE HAVE NO CONTROL OVER OR 
		 RESPONSIBILITY FOR CONTENT ON THIRD PARTY WEBSITES. WE CANNOT BE HELD LIABLE FOR ANY 
		 DAMAGES OR INJURY ARISING FROM CONTENT ON SUCH THIRD PARTY WEBSITES.</paraValue7>
		<% if (service == Util.CSP) { %>
		<paraValue8>LIMITATION OF LIABILITY. WE EACH AGREE TO LIMIT CLAIMS FOR DAMAGES OR OTHER 
		 MONETARY RELIEF AGAINST EACH OTHER TO DIRECT DAMAGES. IN NO EVENT SHALL OUR, OR OUR 
		 AFFILIATES AGGREGATE, TOTAL LIABILITY FOR DIRECT DAMAGES ARISING FROM OR RELATING TO USE 
		 OF THE SERVICE EXCEED THE GREATER OF: (1) THE AMOUNT OF MONTHLY ACCESS FEE, IF ANY, PAID BY 
		 YOU TO VERIZON WIRELESS IN RESPECT OF THE USE OF THE SERVICE OR (2) $$100.00.</paraValue8>
		<% } else if(service == Util.WSP){ %>
		<paraValue8>LIMITATION OF LIABILITY. WE EACH AGREE TO LIMIT CLAIMS FOR DAMAGES OR OTHER 
		 MONETARY RELIEF AGAINST EACH OTHER TO DIRECT DAMAGES. IN NO EVENT SHALL OUR, OR OUR 
		 AFFILIATES AGGREGATE, TOTAL LIABILITY FOR DIRECT DAMAGES ARISING FROM OR RELATING TO USE 
		 OF THE SERVICE EXCEED THE GREATER OF: (1) THE AMOUNT OF MONTHLY ACCESS FEE, IF ANY, PAID BY 
		 YOU TO VERIZON WIRELESS IN RESPECT OF THE USE OF THE SERVICE OR (2) $100.00.</paraValue8>
		<% } else if(service == Util.VCAST){ %>
		<paraValue8>LIMITATION OF LIABILITY. WE EACH AGREE TO LIMIT CLAIMS FOR DAMAGES OR OTHER 
		 MONETARY RELIEF AGAINST EACH OTHER TO DIRECT DAMAGES. IN NO EVENT SHALL OUR, OR OUR 
		 AFFILIATES AGGREGATE, TOTAL LIABILITY FOR DIRECT DAMAGES ARISING FROM OR RELATING TO USE 
		 OF THE SERVICE EXCEED THE GREATER OF: (1) THE AMOUNT OF MONTHLY ACCESS FEE, IF ANY, PAID BY 
		 YOU TO VERIZON WIRELESS IN RESPECT OF THE USE OF THE SERVICE OR (2) $100.00.</paraValue8>
		<% } %>
		<paraValue9>COPYRIGHT. The materials available through the Services are Our copyrighted 
		 property or the copyrighted property of Our licensors.</paraValue9>
		<paraValue10>TRADEMARKS. Our name, tradenames, trademarks and logo, and all related product 
		 and service names, design marks and slogans are Our trademarks, service marks or 
		 registered trademarks, or are the trademarks, service marks or registered trademarks of 
		 Our parent, Verizon Communications.</paraValue10>
		<paraValue11>ELECTRONIC TRANSACTION RULES. You agree that in any situation where Your 
		 signature may be required to process a transaction, compliance with a commercially 
		 reasonable attribution procedure agreed to or adopted by You and Us, or established by 
		 law for authenticating a record authenticates the record as a matter of law.</paraValue11>
		<paraValue12>CONFIDENTIALITY. We cannot guarantee Your confidential use of the Services. 
		 We shall not be responsible for any harm that You or any person may suffer as a result 
		 of a breach of confidentiality in respect to Your use of the Services.</paraValue12>
		<tcLink><%=response.encodeURL(SPProps.termsAndConditionsUrl)%></tcLink>
		<nextLink1><![CDATA[<%=next1%>]]></nextLink1>
		<nextLink2><![CDATA[<%=next2%>]]></nextLink2>
		<nextLink3><![CDATA[<%=next3%>]]></nextLink3>
		<nextLink4><![CDATA[<%=next4%>]]></nextLink4>
		<nextLink5><![CDATA[<%=next5%>]]></nextLink5>
		<nextLink6><![CDATA[<%=next6%>]]></nextLink6>
		<nextLink7><![CDATA[<%=next7%>]]></nextLink7>
		<nextLink8><![CDATA[<%=next8%>]]></nextLink8>
		<nextLink9><![CDATA[<%=next9%>]]></nextLink9>
		<nextLink10><![CDATA[<%=next10%>]]></nextLink10>
		<nextLink11><![CDATA[<%=next11%>]]></nextLink11>
	</para>
</document>
