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
			<card id="Signup" title="Sign up for Mobile Web">
				<p align="center">
					<img  src="images/vzwLogo.wbmp" alt="openwave" align="middle"/>
				</p>

				<xsl:for-each select="document/para">
					<p><b>
						<xsl:value-of select="paraValue"/>
						<a>
							<xsl:attribute name="href">
								<xsl:value-of select="tcLink"/>
							</xsl:attribute>
							<xsl:text>Subscribe</xsl:text>
						</a>
						<xsl:value-of select="paraValue1"/>
					</b></p>
					<p><b>
						<br/>
						<a>
							<xsl:attribute name="href">
								<xsl:value-of select="tcLink"/>
							</xsl:attribute>
							<xsl:text>Subscribe</xsl:text>
						</a>
					</b></p>
					<p><b>
						<a>
							<xsl:attribute name="href">
								<xsl:value-of select="moreInfoLink"/>
							</xsl:attribute>
							<xsl:text>More Info</xsl:text>
						</a>
					</b></p>
				</xsl:for-each>
			</card>
		</wml>
	</xsl:template>
</xsl:stylesheet>
