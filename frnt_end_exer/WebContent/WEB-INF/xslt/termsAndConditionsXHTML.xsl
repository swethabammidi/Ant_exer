<?xml version="1.0"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
	<xsl:template match="/">
		<html xmlns="http://www.w3.org/1999/xhtml">
			<head>
				<meta http-equiv="Expires" content="0" /> 
				<meta http-equiv="Cache-Control" content="no-cache" />
				<meta http-equiv="Pragma" content="no-cache" />
				<title>Terms and Conditions</title>
				<link href="theme/Master.css" rel="stylesheet" type="text/css" />
				<link href="theme/themes.css" rel="stylesheet" type="text/css" />
			</head>
			<body>
				<table bgcolor="white" width="100%">
					<tr><td align="center">
						<img src="images/vzwLogo.gif" />
					</td></tr>
				</table>

				<xsl:for-each select="document/para">
					<p class="blackVerdana8">
						<xsl:value-of select="paraValue"/>
						<a>
							<xsl:attribute name="href">
								<xsl:value-of select="custAgreementLink"/>
							</xsl:attribute>
							<xsl:text>CUSTOMER AGREEMENT</xsl:text>
						</a>,
						<a>
							<xsl:attribute name="href">
								<xsl:value-of select="privacyLink"/>
							</xsl:attribute>
							<xsl:text>PRIVACY</xsl:text>
						</a>,
						<a>
							<xsl:attribute name="href">
								<xsl:value-of select="chargesForServiceLink"/>
							</xsl:attribute>
							<xsl:text>CHARGES FOR THE SERVICE</xsl:text>
						</a>,
						<a>
							<xsl:attribute name="href">
								<xsl:value-of select="billingLink"/>
							</xsl:attribute>
							<xsl:text>BILLING</xsl:text>
						</a>,
						<a>
							<xsl:attribute name="href">
								<xsl:value-of select="monConsentLink"/>
							</xsl:attribute>
							<xsl:text>CONSENT TO MONITORING AND DISCLOSURE</xsl:text>
						</a>,
						<a>
							<xsl:attribute name="href">
								<xsl:value-of select="useOfServicesLink"/>
							</xsl:attribute>
							<xsl:text>YOUR USE OF THE SERVICES</xsl:text>
						</a>,
						<a>
							<xsl:attribute name="href">
								<xsl:value-of select="premContentLink"/>
							</xsl:attribute>
							<xsl:text>PREMIUM CONTENT SERVICES</xsl:text>
						</a>,
						<a>
							<xsl:attribute name="href">
								<xsl:value-of select="discWarrantiesLink"/>
							</xsl:attribute>
							<xsl:text>DISCLAIMER OF WARRANTIES</xsl:text>
						</a>,
						<a>
							<xsl:attribute name="href">
								<xsl:value-of select="limLiabilitiesLink"/>
							</xsl:attribute>
							<xsl:text>LIMITATION OF LIABILITIES</xsl:text>
						</a>,
						<a>
							<xsl:attribute name="href">
								<xsl:value-of select="copyrightLink"/>
							</xsl:attribute>
							<xsl:text>COPYRIGHT</xsl:text>
						</a>,
						<a>
							<xsl:attribute name="href">
								<xsl:value-of select="trademarksLink"/>
							</xsl:attribute>
							<xsl:text>TRADEMARKS</xsl:text>
						</a>,
						<a>
							<xsl:attribute name="href">
								<xsl:value-of select="elecTransactionsLink"/>
							</xsl:attribute>
							<xsl:text>ELECTRONIC TRANSACTIONS RULES</xsl:text>
						</a>,
						<a>
							<xsl:attribute name="href">
								<xsl:value-of select="confidentialityLink"/>
							</xsl:attribute>
							<xsl:text>CONFIDENTIALITY</xsl:text>
						</a>,
						<a>
							<xsl:attribute name="href">
								<xsl:value-of select="entireTCLink"/>
							</xsl:attribute>
							<xsl:text>ENTIRE TERMS AND CONDITIONS</xsl:text>
						</a>.
						<xsl:value-of select="paraValue1"/>
					</p>
					<a>
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
					</a>
				</xsl:for-each>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>
