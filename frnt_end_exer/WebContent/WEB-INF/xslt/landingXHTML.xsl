<?xml version="1.0"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
	<xsl:template match="/">
		<html xmlns="http://www.w3.org/1999/xhtml">
			<head>
				<meta http-equiv="Expires" content="0" /> 
				<meta http-equiv="Cache-Control" content="no-cache" />
				<meta http-equiv="Pragma" content="no-cache" />
				<link href="theme/Master.css" rel="stylesheet" type="text/css" />
				<link href="theme/themes.css" rel="stylesheet" type="text/css" />
				<title><xsl:value-of select="/document/title"	></xsl:value-of></title>	
			</head>
			<body>
				<xsl:variable name="mainImg" select="/document/mimg" />
				<xsl:if test="$mainImg != ''">
								<img>
									<xsl:attribute name="src">
										<xsl:value-of select="/document/mimg"/>
									</xsl:attribute>
								</img>
					<BR />								
				</xsl:if>
				<xsl:variable name="limage" select="/document/limage" />
				<xsl:variable name="dimage" select="/document/dimage" />
				<xsl:variable name="link" select="/document/link" />
				<xsl:variable name="akey" select="/document/link/@accesskey" />
				<xsl:if test="$link[1] != ''">
						<a>
						<xsl:attribute name="href">
							<xsl:value-of select="/document/link[1]"/>
						</xsl:attribute>
						<xsl:if test="$akey[1] != ''">
							<xsl:attribute name="accesskey">
								<xsl:value-of select="/document/link[1]/@accesskey" />
							</xsl:attribute>
						</xsl:if>
						<xsl:if test="$limage[1] != ''">
							<img>
								<xsl:attribute name="src">
									<xsl:value-of select="/document/limage[1]"></xsl:value-of>
								</xsl:attribute>
								<xsl:attribute name="border">0</xsl:attribute>
								<xsl:attribute name="bgcolor">white</xsl:attribute>
							</img>
						<BR />															
						</xsl:if>
						</a>
				</xsl:if>
				<xsl:if test="$dimage[1] != ''">
						<img>
							<xsl:attribute name="src">
								<xsl:value-of select="/document/dimage[1]"></xsl:value-of>
							</xsl:attribute>
							<xsl:attribute name="border">0</xsl:attribute>
							<xsl:attribute name="bgcolor">white</xsl:attribute>
						</img>
					<BR />														
				</xsl:if>
				<xsl:if test="$link[2] != ''">
					<a>
						<xsl:attribute name="href">
							<xsl:value-of select="/document/link[2]"/>
						</xsl:attribute>
						<xsl:if test="$akey[2] != ''">
							<xsl:attribute name="accesskey">
								<xsl:value-of select="/document/link[2]/@accesskey" />
							</xsl:attribute>
						</xsl:if>
						<xsl:if test="$limage[2] != ''">
							<img>
								<xsl:attribute name="src">
									<xsl:value-of select="/document/limage[2]"></xsl:value-of>
								</xsl:attribute>
								<xsl:attribute name="border">0</xsl:attribute>
								<xsl:attribute name="bgcolor">white</xsl:attribute>
							</img>
						<BR />															
						</xsl:if>
					</a>
				</xsl:if>
				<xsl:if test="$dimage[2] != ''">
					<img>
						<xsl:attribute name="src">
							<xsl:value-of select="/document/dimage[2]"></xsl:value-of>
						</xsl:attribute>
						<xsl:attribute name="border">0</xsl:attribute>
						<xsl:attribute name="bgcolor">white</xsl:attribute>
					</img>
					<BR />													
				</xsl:if>
			</body>	
		</html>
	</xsl:template>
</xsl:stylesheet>
