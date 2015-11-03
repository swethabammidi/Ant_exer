<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" import="java.io.*,java.util.*,com.vzw.edr.selfProv.utils.*"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<META http-equiv="Content-Style-Type" content="text/css">
<TITLE>Self Provisioning Properties</TITLE>
</HEAD>
<BODY>
<B>-----------------------------------------------------------------------------------------</B><br>
<B>Properties</B><BR>
<B>-----------------------------------------------------------------------------------------</B><br>
<%
	String propPath = System.getProperty("PROPPATH");
	String propFile = propPath + File.separator + "selfProvisioning.properties";
	new SPProps();
	Properties props = SPProps.getPropObject();
	Set keySet = props.keySet();
	TreeSet sortedKeys = new TreeSet(keySet);
	Iterator it = sortedKeys.iterator();
	while (it.hasNext())
	{
		String name = (String)it.next();
		String val = props.getProperty(name);
%>
<%=name%> = <b><%=val%></b><br>
<%
	}
%>
<B>-----------------------------------------------------------------------------------------</B><br>
<B>Bill Prov Props</B><BR>
<B>-----------------------------------------------------------------------------------------</B><br>
<%
	Properties bpProps = SPProps.getBillProvPropObject();
	keySet = bpProps.keySet();
	sortedKeys = new TreeSet(keySet);
	it = sortedKeys.iterator();
	while (it.hasNext())
	{
		String name = (String) it.next();
		String val = bpProps.getProperty(name);
%>
<%=name%> = <b><%=val%></b><br>
<%
	}
%>
<B>-----------------------------------------------------------------------------------------</B><br>
<B>Action Handler Props</B><BR>
<B>-----------------------------------------------------------------------------------------</B><br>
<%
	Properties ahProps = SPProps.getActionHandlerPropObject();
	keySet = ahProps.keySet();
	sortedKeys = new TreeSet(keySet);
	it = sortedKeys.iterator();
	while (it.hasNext())
	{
		String name = (String) it.next();
		String val = ahProps.getProperty(name);
%>
<%=name%> = <b><%=val%></b><br>
<%
	}
%>
<B>-----------------------------------------------------------------------------------------</B><br>
<B>Vendor URL Props</B><BR>
<B>-----------------------------------------------------------------------------------------</B><br>
<%
	Properties vuProps = SPProps.getVendUrlPropObject();
	keySet = vuProps.keySet();
	sortedKeys = new TreeSet(keySet);
	it = sortedKeys.iterator();
	while (it.hasNext())
	{
		String name = (String) it.next();
		String val = vuProps.getProperty(name);
%>
<%=name%> = <b><%=val%></b><br>
<%
	}
%>
<B>-----------------------------------------------------------------------------------------</B><br>
<B>VBS URL Props</B><BR>
<B>-----------------------------------------------------------------------------------------</B><br>
<%
	Properties vbsProps = SPProps.getVBSUrlPropObject();
	keySet = vbsProps.keySet();
	sortedKeys = new TreeSet(keySet);
	it = sortedKeys.iterator();
	while (it.hasNext())
	{
		String name = (String) it.next();
		String val = vbsProps.getProperty(name);
%>
<%=name%> = <b><%=val%></b><br>
<%
	}
%>
<B>-----------------------------------------------------------------------------------------</B><br>
<B>UI DB Props</B><BR>
<B>-----------------------------------------------------------------------------------------</B><br>
<%
	Properties uidbProps = SPProps.getUIDBPropObject();
	keySet = vbsProps.keySet();
	sortedKeys = new TreeSet(keySet);
	it = sortedKeys.iterator();
	while (it.hasNext())
	{
		String name = (String) it.next();
		String val = uidbProps.getProperty(name);
%>
<%=name%> = <b><%=val%></b><br>
<%
	}
%>
</BODY>
</HTML>
