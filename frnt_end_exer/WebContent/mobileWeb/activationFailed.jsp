<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" import="com.vzw.selfProvisioning.mobileWeb.*, com.vzw.selfProvisioning.utils.*, com.vzw.edr.selfProv.utils.*, org.apache.log4j.*" %>
<%!
	static Logger L = Logger.getLogger(SPProps.feLoggerBase + ".activationFailed_jsp");		
%>
<%
	int service = Util.getService(request);
	String errorMsg = "";
	String imgBaseUrl = Util.getContextURL(request);
	ActivationFailed af = (ActivationFailed)request.getAttribute("statusBean");
	String errorCode = null;
	if (af != null)
		errorCode = af.getErrorCode();
	if (errorCode == null)
		errorCode = "";
	if (errorCode.trim().equals("201"))
	{
		errorMsg = "Unable to submit your order at this time, please try again later.";
	}
	else if (errorCode.trim().equals("202"))
	{
		errorMsg = "Unable to submit your order at this time, please try again later.";
	}
	else if (errorCode.trim().equals("203"))
	{
		if (service == Util.VCAST)
		{
			errorMsg = "The system was unable to process your order, please call Customer Service, *611 from your mobile phone, to activate. Error Code: 203";
		}
		else
		{
			errorMsg = "Unable to submit your order at this time, please try again later.";
		}
	}
	else if (errorCode.trim().equals("204"))
	{
		if (service == Util.VCAST)
		{
			errorMsg = "The system was unable to process your order, please call Customer Service, *611 from your mobile phone, to activate. Error Code: 204";
		}
		else
		{
			errorMsg = "Unable to submit your order at this time, please try again later.";
		}
	}
	else if (errorCode.trim().equals("205"))
	{
		if (service == Util.PPUS)
			errorMsg = "Ordering Pix Place Unlimited Storage feature is not available " +
					"from your service provider at this time. Please contact Customer Care you require further assistance.";
		else
			errorMsg = "MobileWeb is not available from your wireless service provider.";
	}
	else if (errorCode.trim().equals("206"))
	{
		errorMsg = "Unable to submit your order at this time, please try again later.";
	}
	else if (errorCode.trim().equals("207"))
	{
		errorMsg = "Our records indicate that you already have the requested feature, " +
				"please contact customer care if you require additional assistance.";
	}
	else
	{
		errorMsg = "The system was unable to process your order, please call Customer Service,";
		errorMsg += " *611 from your mobile phone, to activate. Error Code: " + errorCode;
	}
%>

<jsp:useBean id="statusBean" scope="request" class="com.vzw.selfProvisioning.mobileWeb.ActivationFailed"/>
<document>
	<para>
		<paraValue><%=errorMsg%></paraValue>
	</para>
	<logoImg><%= imgBaseUrl %>images/vzwLogo.jpg</logoImg>
	<% if (service == Util.PPUS){ %>
		<backLinkText>Return to Pix Place</backLinkText>
		<backLink><%=response.encodeURL(SPProps.pixPlaceFURL)%></backLink>
	<% } %>
</document>
					
