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
								<xsl:value-of select="pixPlaceRegLink"/>
							</xsl:attribute>
							<xsl:text>PIX PLACE REGISTRATION REQUIRED</xsl:text>
						</a>,
						<a>
							<xsl:attribute name="href">
								<xsl:value-of select="picVideoMsgLink"/>
							</xsl:attribute>
							<xsl:text>CUSTOMER AGREEMENT AND PICTURE/VIDEO MESSAGING WEBSITE USE AGREEMENT</xsl:text>
						</a>,
						<a>
							<xsl:attribute name="href">
								<xsl:value-of select="billingLink"/>
							</xsl:attribute>
							<xsl:text>CHARGES AND BILLING</xsl:text>
						</a>,
						<a>
							<xsl:attribute name="href">
								<xsl:value-of select="elecTransactionsLink"/>
							</xsl:attribute>
							<xsl:text>ELECTRONIC TRANSACTIONS RULES</xsl:text>
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
