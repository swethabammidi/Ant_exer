<?xml version="1.0"?>
<xsl:stylesheet  version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		<xsl:text disable-output-escaping="yes">
		&lt;!DOCTYPE wml PUBLIC "-//WAPFORUM//DTD WML 1.1//EN" "http://www.wapforum.org/DTD/wml_1.1.xml"&gt;
		</xsl:text>
		<wml>
			<head>
 				<meta http-equiv="Cache-Control" content="max-age=0" forua="true"/>
				<meta http-equiv="Cache-Control" content="must-revalidate" forua="true" />
				<meta http-equiv="Cache-Control" content="no-cache" forua="true" />
			</head>
			<xsl:variable name="nextVal" select="document/nextVal"/>
  			<card id="Signup" title="Terms and Conditions">
  				<p align="center">
					<img  src="images/vzwLogo.wbmp" alt="openwave" align="middle"/>
				</p>
				<xsl:if test="$nextVal = '0'">
					<xsl:for-each select="document/para">
						<p><b><a>
							<xsl:attribute name="href">
								<xsl:value-of select="nextLink1"/>
							</xsl:attribute>
							<xsl:text>NEXT</xsl:text>
						</a></b></p>
						<p><b><xsl:value-of select="paraValue"/></b></p>
						<p><b>1 <a>
							<xsl:attribute name="href">
								<xsl:value-of select="custAgreementLink"/>
							</xsl:attribute>
							<xsl:text>CUSTOMER AGREEMENT</xsl:text>
						</a>
						<br/>2 <a>
							<xsl:attribute name="href">
								<xsl:value-of select="privacyLink"/>
							</xsl:attribute>
							<xsl:text>PRIVACY</xsl:text>
						</a>
						<br/>3 <a>
							<xsl:attribute name="href">
								<xsl:value-of select="chargesForServiceLink"/>
							</xsl:attribute>
							<xsl:text>CHARGES FOR THE SERVICE</xsl:text>
						</a>
						<br/>4<a>
							<xsl:attribute name="href">
								<xsl:value-of select="billingLink"/>
							</xsl:attribute>
							<xsl:text>BILLING</xsl:text>
						</a></b></p>
						<p><b><a>
							<xsl:attribute name="href">
								<xsl:value-of select="nextLink1"/>
							</xsl:attribute>
							<xsl:text>NEXT</xsl:text>
						</a></b></p>
					</xsl:for-each>
				</xsl:if>
				<xsl:if test="$nextVal = '1'">
					<xsl:for-each select="document/para">
						<p><b><a>
							<xsl:attribute name="href">
								<xsl:value-of select="nextLink2"/>
							</xsl:attribute>
							<xsl:text>NEXT</xsl:text>
						</a></b></p>
						<p><b>5<a>
							<xsl:attribute name="href">
								<xsl:value-of select="monConsentLink"/>
							</xsl:attribute>
							<xsl:text>CONSENT TO MONITORING AND DISCLOSURE</xsl:text>
						</a>
						<br/>6 <a>
							<xsl:attribute name="href">
								<xsl:value-of select="useOfServicesLink"/>
							</xsl:attribute>
							<xsl:text>YOUR USE OF THE SERVICES</xsl:text>
						</a>
						<br/>7 <a>
							<xsl:attribute name="href">
								<xsl:value-of select="discWarrantiesLink"/>
							</xsl:attribute>
							<xsl:text>DISCLAIMER OF WARRANTIES</xsl:text>
						</a>
						<br/>8 <a>
							<xsl:attribute name="href">
								<xsl:value-of select="limLiabilitiesLink"/>
							</xsl:attribute>
							<xsl:text>LIMITATION OF LIABILITIES</xsl:text>
						</a>
						<br/>9<a>
							<xsl:attribute name="href">
								<xsl:value-of select="copyrightLink"/>
							</xsl:attribute>
							<xsl:text>COPYRIGHT</xsl:text>
						</a>
						<br/>10 <a>
							<xsl:attribute name="href">
								<xsl:value-of select="trademarksLink"/>
							</xsl:attribute>
							<xsl:text>TRADEMARKS</xsl:text>
						</a>
						<br/>11 <a>
							<xsl:attribute name="href">
								<xsl:value-of select="elecTransactionsLink"/>
							</xsl:attribute>
							<xsl:text>ELECTRONIC TRANSACTIONS RULES</xsl:text>
						</a></b></p>
						<p><b><a>
							<xsl:attribute name="href">
								<xsl:value-of select="nextLink2"/>
							</xsl:attribute>
							<xsl:text>NEXT</xsl:text>
						</a></b></p>
					</xsl:for-each>
				</xsl:if>
				<xsl:if test="$nextVal = '2'">
					<xsl:for-each select="document/para">
						<p><b><a>
							<xsl:attribute name="href">
								<xsl:value-of select="accLink"/>
							</xsl:attribute>
							<xsl:text>Accept</xsl:text>
						</a>
						<br />
						<a>
							<xsl:attribute name="href">
								<xsl:value-of select="decLink"/>
							</xsl:attribute>
							<xsl:text>Decline</xsl:text>
						</a></b></p>
						<p><b>12 <a>
							<xsl:attribute name="href">
								<xsl:value-of select="confidentialityLink"/>
							</xsl:attribute>
							<xsl:text>CONFIDENTIALITY</xsl:text>
						</a>
						<br/>13 <a>
							<xsl:attribute name="href">
								<xsl:value-of select="entireTCLink"/>
							</xsl:attribute>
							<xsl:text>ENTIRE TERMS AND CONDITIONS</xsl:text>
						</a></b></p>
						<p><b><xsl:value-of select="paraValue1"/></b></p>
						<p><b><a>
							<xsl:attribute name="href">
								<xsl:value-of select="accLink"/>
							</xsl:attribute>
							<xsl:text>Accept</xsl:text>
						</a>
						<br />
						<a>
							<xsl:attribute name="href">
								<xsl:value-of select="decLink"/>
							</xsl:attribute>
							<xsl:text>Decline</xsl:text>
						</a></b></p>
					</xsl:for-each>
				</xsl:if>
			</card>
		</wml>
	</xsl:template>
</xsl:stylesheet>

