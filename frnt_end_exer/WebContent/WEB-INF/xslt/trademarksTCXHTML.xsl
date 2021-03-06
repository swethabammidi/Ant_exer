<?xml version="1.0"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
	<xsl:template match="/">
		<html xmlns="http://www.w3.org/1999/xhtml">
			<head>
				<meta http-equiv="Expires" content="0" /> 
				<meta http-equiv="Cache-Control" content="no-cache" />
				<meta http-equiv="Pragma" content="no-cache" />
				<title>Trademarks T&amp;C</title>
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
					</p>
					<a>
						<xsl:attribute name="href">
							<xsl:value-of select="tcLink"/>
						</xsl:attribute>
						<xsl:text>Back</xsl:text>
					</a>
				</xsl:for-each>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>
