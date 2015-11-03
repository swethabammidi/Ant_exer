<?xml version="1.0"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
	<xsl:template match="/">
		<html xmlns="http://www.w3.org/1999/xhtml">
			<head>
				<meta http-equiv="Expires" content="0" /> 
				<meta http-equiv="Cache-Control" content="no-cache" />
				<meta http-equiv="Pragma" content="no-cache" />
				<title>Cancelled</title>
			</head>
			<body>
				<table bgcolor="white" width="100%">
					<tr>
						<td align="center">
							<xsl:variable name="logoImg" select="/document/logoImg" />
							<xsl:if test="$logoImg != ''">
								<img>
									<xsl:attribute name="src">
									<xsl:value-of select="/document/logoImg"/>
									</xsl:attribute>
								</img>
							</xsl:if>								
						</td>
					</tr>
				</table>
				<xsl:for-each select="document/para">
					<p>
						<xsl:value-of select="paraValue"/>
					</p>
				</xsl:for-each>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>



