<%@ page language="java" import="java.util.*" %>
<%
	java.util.HashMap vMap = new java.util.HashMap();
	vMap.put("200011","ZTANGO");
	vMap.put("200012","MSN");
	vMap.put("200013","QUALCOMM");
	vMap.put("200014","TELENOR");
	vMap.put("200015","MOBLISS");
	vMap.put("200016","MQUBE");
	vMap.put("200017","HANDANGO");
	vMap.put("200018","PINPOINT");
	vMap.put("200019","GOLDPOCKET");
	vMap.put("200020","NETPACE");
	vMap.put("200021","ZTANGOP");
	vMap.put("200023","InfoSpace");
	vMap.put("200024","thePlatform");
	vMap.put("200025","Inphomatch");
	vMap.put("200067","LightSurf");
	vMap.put("200028","Upoc");
	vMap.put("200031","M7");
	vMap.put("200032","RIM");
	vMap.put("200999","WIG");
	vMap.put("800001","Lavalife");
	vMap.put("999900","SCM");
	vMap.put("999901","SCMVEN1");
	vMap.put("999902","SCMVEN2");
	vMap.put("999903","SCMVEN3");
	vMap.put("999904","SCMVEN4");
	vMap.put("999905","SCMVEN5");
	vMap.put("999906","SCMVEN6");
	vMap.put("999907","SCMVEN7");
	vMap.put("999908","SCMVEN8");
	vMap.put("999909","SCMVEN9");
	vMap.put("999910","SCMVEN10");
	vMap.put("200080","MEDIAFLO11");	
	vMap.put("999920","Fox");
	vMap.put("999999","VZW");	
	Object [] keys = vMap.keySet().toArray();
%>
<HTML>
	<HEAD>
		<TITLE>TestSP.jsp</TITLE>
	</HEAD>
	<BODY>
	<FORM METHOD=POST ACTION="TestDriverPerf">
		<TABLE BORDER="1">
			<TR>
				<TH COLSPAN="2">Enter the Relevant Information and Click Submit</TH>
			</TR>
			<TR>
				<TH>req_type: 's' or 'x'</TH>
				<TD><INPUT NAME="ReqType" TYPE="TEXT" LENGTH="1" /></TD>
			</TR>
			<TR>
				<TH>MDN</TH>
				<TD><INPUT NAME="mdn" TYPE="TEXT" LENGTH="10" /></TD>
			</TR>
			<TR>
				<TH>MIN</TH>
				<TD><INPUT NAME="min" TYPE="TEXT" LENGTH="10" /></TD>
			</TR>
			<TR>
				<TH>VENDOR_ID</TH>
				<TD><SELECT NAME="vendor_id">
					<% for (int i = 0; i < keys.length; i++) { %>
					<OPTION VALUE="<%=keys[i]%>"><%=(String)vMap.get(keys[i])%></OPTION>
					<% } %>
					</SELECT>
				</TD>
			</TR>
			<TR>
				<TH>CHANNEL_ID</TH>
				<TD><INPUT NAME="channel_id" TYPE="TEXT" LENGTH="10" MAXLENGTH="2" /></TD>
			</TR>
			<TR>
				<TH>SERVICE_ID</TH>
				<TD><SELECT NAME="service_id">
					<OPTION VALUE="" SELECTED></OPTION>
					<OPTION VALUE="MPX">MPX</OPTION>
					<OPTION VALUE="RBT">RBT</OPTION>
					<OPTION VALUE="WSP">WSP</OPTION>
					<OPTION VALUE="CSP">CSP</OPTION>
					<OPTION VALUE="FFL">FFL</OPTION>
					<OPTION VALUE="FLO">FLO</OPTION>
					<OPTION VALUE="V24">V24</OPTION>
					</SELECT><BR>
	
					<INPUT TYPE="RADIO" NAME="action_type" value="add" SELECTED>Add
					<INPUT TYPE="RADIO" NAME="action_type" value="delete">Delete
				</TD>
			</TR>
			<TR>
				<TH>CONTENT 1</TH>
				<TD><INPUT NAME="content1" TYPE="TEXT" LENGTH="15" MAXLENGTH="15" /></TD>
			</TR>
			<TR>
				<TH>CONTENT 2</TH>
				<TD><INPUT NAME="content2" TYPE="TEXT" LENGTH="15" MAXLENGTH="15" /></TD>
			</TR>
			<TR>
				<TH>CONTENT 3</TH>
				<TD><INPUT NAME="content3" TYPE="TEXT" LENGTH="15" MAXLENGTH="15" /></TD>
			</TR>
			<TR>
				<TH>CONTENT 4</TH>
				<TD><INPUT NAME="content4" TYPE="TEXT" LENGTH="15" MAXLENGTH="15" /></TD>
			</TR>
			<TR>
				<TH>CONTENT 5</TH>
				<TD><INPUT NAME="content5" TYPE="TEXT" LENGTH="15" MAXLENGTH="15" /></TD>
			</TR>
			<TR>
				<TH>PREPAY_IND</TH>
				<TD><SELECT NAME="prepay_ind">
					<OPTION VALUE="" SELECTED></OPTION>
					<OPTION VALUE="Y">Y</OPTION>
					<OPTION VALUE="N">N</OPTION>
					</SELECT>
				</TD>
			</TR>
			<TR>
				<TD COLSPAN=2 ALIGN="CENTER">
					<INPUT TYPE="SUBMIT" NAME="sub" VALUE="Submit"/>
					<INPUT TYPE="RESET" NAME="res" VALUE="Reset"/>
				</TD>
			</TR>
		</TABLE>
	</FORM>
	</BODY>
</HTML>
