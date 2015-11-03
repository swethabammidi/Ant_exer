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
				<xsl:for-each select="document/para">
					<xsl:if test="$nextVal = '0'">
						<p><b>
								<xsl:value-of select="paraValue1"/>
						</b></p>
						<p><b>
							<a>
								<xsl:attribute name="href">
									<xsl:value-of select="nextLink1"/>
								</xsl:attribute>
								<xsl:text>NEXT</xsl:text>
							</a>
							<br/><a>
								<xsl:attribute name="href">
									<xsl:value-of select="tcLink"/>
								</xsl:attribute>
								<xsl:text>HOME</xsl:text>
							</a>
						</b></p>
					</xsl:if>
					<xsl:if test="$nextVal = '1'">
						<p><b>
								<xsl:value-of select="paraValue2"/>
						</b></p>
						<p><b>
							<a>
								<xsl:attribute name="href">
									<xsl:value-of select="nextLink2"/>
								</xsl:attribute>
								<xsl:text>NEXT</xsl:text>
							</a>
							<br/><a>
								<xsl:attribute name="href">
									<xsl:value-of select="tcLink"/>
								</xsl:attribute>
								<xsl:text>HOME</xsl:text>
							</a>
						</b></p>
					</xsl:if>
					<xsl:if test="$nextVal = '2'">
						<p><b>
								<xsl:value-of select="paraValue3"/>
						</b></p>
						<p><b>
							<a>
								<xsl:attribute name="href">
									<xsl:value-of select="nextLink3"/>
								</xsl:attribute>
								<xsl:text>NEXT</xsl:text>
							</a>
							<br/><a>
								<xsl:attribute name="href">
									<xsl:value-of select="tcLink"/>
								</xsl:attribute>
								<xsl:text>HOME</xsl:text>
							</a>
						</b></p>
					</xsl:if>
					<xsl:if test="$nextVal = '3'">
						<p><b>
								<xsl:value-of select="paraValue4"/>
						</b></p>
						<p><b>
							<a>
								<xsl:attribute name="href">
									<xsl:value-of select="nextLink4"/>
								</xsl:attribute>
								<xsl:text>NEXT</xsl:text>
							</a>
							<br/><a>
								<xsl:attribute name="href">
									<xsl:value-of select="tcLink"/>
								</xsl:attribute>
								<xsl:text>HOME</xsl:text>
							</a>
						</b></p>
					</xsl:if>
					<xsl:if test="$nextVal = '4'">
						<p><b>
								<xsl:value-of select="paraValue5"/>
						</b></p>
						<p><b>
							<a>
								<xsl:attribute name="href">
									<xsl:value-of select="nextLink5"/>
								</xsl:attribute>
								<xsl:text>NEXT</xsl:text>
							</a>
							<br/><a>
								<xsl:attribute name="href">
									<xsl:value-of select="tcLink"/>
								</xsl:attribute>
								<xsl:text>HOME</xsl:text>
							</a>
						</b></p>
					</xsl:if>
					<xsl:if test="$nextVal = '5'">
						<p><b>
								<xsl:value-of select="paraValue6"/>
						</b></p>
						<p><b>
							<a>
								<xsl:attribute name="href">
									<xsl:value-of select="nextLink6"/>
								</xsl:attribute>
								<xsl:text>NEXT</xsl:text>
							</a>
							<br/><a>
								<xsl:attribute name="href">
									<xsl:value-of select="tcLink"/>
								</xsl:attribute>
								<xsl:text>HOME</xsl:text>
							</a>
						</b></p>
					</xsl:if>
					<xsl:if test="$nextVal = '6'">
						<p><b>
								<xsl:value-of select="paraValue7"/>
						</b></p>
						<p><b>
							<a>
								<xsl:attribute name="href">
									<xsl:value-of select="nextLink7"/>
								</xsl:attribute>
								<xsl:text>NEXT</xsl:text>
							</a>
							<br/><a>
								<xsl:attribute name="href">
									<xsl:value-of select="tcLink"/>
								</xsl:attribute>
								<xsl:text>HOME</xsl:text>
							</a>
						</b></p>
					</xsl:if>
					<xsl:if test="$nextVal = '7'">
						<p><b>
								<xsl:value-of select="paraValue8"/>
						</b></p>
						<p><b>
							<a>
								<xsl:attribute name="href">
									<xsl:value-of select="nextLink8"/>
								</xsl:attribute>
								<xsl:text>NEXT</xsl:text>
							</a>
							<br/><a>
								<xsl:attribute name="href">
									<xsl:value-of select="tcLink"/>
								</xsl:attribute>
								<xsl:text>HOME</xsl:text>
							</a>
						</b></p>
					</xsl:if>
					<xsl:if test="$nextVal = '8'">
						<p><b>
								<xsl:value-of select="paraValue9"/>
						</b></p>
						<p><b>
							<a>
								<xsl:attribute name="href">
									<xsl:value-of select="nextLink9"/>
								</xsl:attribute>
								<xsl:text>NEXT</xsl:text>
							</a>
							<br/><a>
								<xsl:attribute name="href">
									<xsl:value-of select="tcLink"/>
								</xsl:attribute>
								<xsl:text>HOME</xsl:text>
							</a>
						</b></p>
					</xsl:if>
					<xsl:if test="$nextVal = '9'">
						<p><b>
								<xsl:value-of select="paraValue10"/>
						</b></p>
						<p><b>
							<a>
								<xsl:attribute name="href">
									<xsl:value-of select="nextLink10"/>
								</xsl:attribute>
								<xsl:text>NEXT</xsl:text>
							</a>
							<br/><a>
								<xsl:attribute name="href">
									<xsl:value-of select="tcLink"/>
								</xsl:attribute>
								<xsl:text>HOME</xsl:text>
							</a>
						</b></p>
					</xsl:if>
					<xsl:if test="$nextVal = '10'">
						<p><b>
								<xsl:value-of select="paraValue11"/>
						</b></p>
						<p><b>
							<a>
								<xsl:attribute name="href">
									<xsl:value-of select="nextLink11"/>
								</xsl:attribute>
								<xsl:text>NEXT</xsl:text>
							</a>
							<br/><a>
								<xsl:attribute name="href">
									<xsl:value-of select="tcLink"/>
								</xsl:attribute>
								<xsl:text>HOME</xsl:text>
							</a>
						</b></p>
					</xsl:if>
					<xsl:if test="$nextVal = '11'">
						<p><b>
								<xsl:value-of select="paraValue12"/>
						</b></p>
						<p><b>
							<a>
								<xsl:attribute name="href">
									<xsl:value-of select="tcLink"/>
								</xsl:attribute>
								<xsl:text>HOME</xsl:text>
							</a>
						</b></p>
					</xsl:if>
				</xsl:for-each>
			</card>
		</wml>
	</xsl:template>
</xsl:stylesheet>

