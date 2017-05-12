<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format" xmlns:java="http://xml.apache.org/xslt/java" exclude-result-prefixes="java" xmlns="http://www.w3.org/1999/xhtml">
	
	<xsl:template match="/">
		<fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format">

		<fo:layout-master-set>
		  <fo:simple-page-master master-name="A4" margin="1cm">
			<fo:region-body  margin-top="0.5cm" margin-bottom="0.5cm" margin-left="0.3cm" margin-right="0.3cm"/>
			<fo:region-before extent="3cm"/>
			<fo:region-after extent="0.5cm"/>
		  </fo:simple-page-master>
		</fo:layout-master-set>

		<fo:page-sequence master-reference="A4">
			
			<fo:static-content flow-name="xsl-region-after">
				<fo:block font-size="10pt" font-family="Arial">Wygenerowano: <xsl:call-template name="dataCzas"/></fo:block>
			</fo:static-content>		
			
			<fo:flow flow-name="xsl-region-body">
			<fo:block font-size="10pt" font-family="Arial">
					<fo:table table-layout="fixed" width="100%">
					<fo:table-column column-width="55mm"/>
					<fo:table-column column-width="60mm"/>
					<fo:table-column column-width="65mm"/>
						<fo:table-body>
							<fo:table-row>
								<fo:table-cell border-top="solid black 1px" border-left="solid black 1px">
									<fo:block font-size="9pt" font-family="Arial" padding-before="0.1cm" padding-after="0.2cm" text-align="center" font-weight="bold">
									BADANIE PODMIOTOWE OGÓLNE</fo:block>
								</fo:table-cell>
								<fo:table-cell border-top="solid black 1px">
									<fo:block font-size="10pt" font-family="Arial" padding-before="0.1cm" padding-after="0.2cm" text-align="center">
									Oddział: <xsl:value-of select="badanie/@oddzial"/></fo:block>
								</fo:table-cell>
								<fo:table-cell border-top="solid black 1px" border-left="solid black 1px" border-right="solid black 1px">
									<fo:block font-size="7pt" font-family="Arial" padding-before="0.1cm" padding-after="0.3cm" text-align="center">
									DATA URODZENIA NUMER EWIDENCYJNY PESEL</fo:block>	
								</fo:table-cell>
							</fo:table-row>
						</fo:table-body>
					</fo:table>
				</fo:block>
				
				<fo:block font-size="10pt" font-family="Arial">
					<fo:table table-layout="fixed" width="100%">
						<fo:table-column column-width="45mm"/>
						<fo:table-column column-width="70mm"/>
						<fo:table-column column-width="65mm"/>
							<fo:table-body>
								<fo:table-row>
									<fo:table-cell border-bottom="solid black 1px" border-left="solid black 1px">
										<fo:block font-size="10pt" font-family="Arial" padding-before="0.1cm" padding-after="0.2cm" text-align="center">
										Nr Ks. Gł: <xsl:value-of select="badanie/@ksiega_gl"/></fo:block>
									</fo:table-cell>
									<fo:table-cell border-bottom="solid black 1px" border-right="solid black 1px">
										<fo:block font-size="10pt" font-family="Arial" padding-before="0.1cm" padding-after="0.2cm" text-align="center">
										Nr Ks. Oddz.: <xsl:value-of select="badanie/@ksiega_oddzialu"/></fo:block>
									</fo:table-cell>
									<fo:table-cell border-bottom="solid black 1px" border-left="solid black 1px" border-right="solid black 1px">
										<fo:block font-family="Arial" padding-before="0.1cm" text-align="center"><xsl:value-of select="badanie/pacjent/@pesel"/></fo:block>
									</fo:table-cell>
								</fo:table-row>
							</fo:table-body>
					</fo:table>
				</fo:block>
				
				<fo:block font-size="10pt" font-family="Arial">
					<fo:table table-layout="fixed" width="100%">
						<fo:table-column column-width="82.5mm"/>
						<fo:table-column column-width="82.5mm"/>
						<fo:table-column column-width="15mm"/>
							<fo:table-body>
								<fo:table-row>
									<fo:table-cell border-bottom="solid black 1px" border-left="solid black 1px">
										<fo:block font-size="10pt" font-family="Arial" padding-before="0.1cm" padding-after="0.2cm" text-align="left">NAZWISKO:</fo:block>
										<fo:block><xsl:value-of select="badanie/pacjent/nazwisko"/></fo:block>
									</fo:table-cell>
									<fo:table-cell border-bottom="solid black 1px" border-right="solid black 1px" border-left="solid black 1px">
										<fo:block font-size="10pt" font-family="Arial" padding-before="0.1cm" padding-after="0.2cm" text-align="left">IMIONA:</fo:block>
										<fo:block><xsl:apply-templates select="badanie/pacjent/imie"/></fo:block>
									</fo:table-cell>
									<fo:table-cell border-bottom="solid black 1px" border-left="solid black 1px" border-right="solid black 1px">
										<fo:block font-size="10pt" font-family="Arial" padding-before="0.1cm" padding-after="0.2cm" text-align="center">PŁEĆ (K,M)</fo:block>
										<fo:block text-align="center"><xsl:value-of select="badanie/pacjent/plec"/></fo:block>
									</fo:table-cell>
								</fo:table-row>
							</fo:table-body>
					</fo:table>
				</fo:block>
				
				<fo:block font-size="10pt" font-family="Arial" padding-after="1cm" padding-before="1.3cm">GŁÓWNE SKARGI: 
					<xsl:apply-templates select="badanie/wywiad_wstepny/gl_skargi"/>
				</fo:block>
				<fo:block font-size="10pt" font-family="Arial" padding-after="1cm">DOTYCHCZASOWY PRZEBIEG CHOROBY: 
					<xsl:apply-templates select="badanie/wywiad_wstepny/dotychczasowy_przebieg"/>
				</fo:block>
				<fo:block font-size="10pt" font-family="Arial" padding-after="1.5cm">PRZEBYTE CHOROBY: 
					<xsl:apply-templates select="badanie/wywiad_wstepny/przebyte_choroby"/>
				</fo:block>

				
				
				<fo:block font-size="8pt" font-family="Arial" padding-after="0.2cm" text-align="center"> 
					Wykaz ważniejszych leków przyjmowanych przed hospitalizacją
				</fo:block>
				
				<fo:block font-size="10pt" font-family="Arial" padding-after="1cm">
					<fo:table table-layout="fixed" width="100%" border="solid black 1px">
					<fo:table-column column-width="40mm"/>
					<fo:table-column column-width="15mm"/>
					<fo:table-column column-width="50mm"/>
					<fo:table-column column-width="75mm"/>
						<fo:table-header>
							<fo:table-row>
								<fo:table-cell border="solid black 2px">
									<fo:block font-weight="bold" background-color="#cccccc" text-align="center">Nazwa leku</fo:block>
								</fo:table-cell>
								<fo:table-cell border="solid black 2px">
									<fo:block font-weight="bold" background-color="#cccccc" text-align="center">Dawka</fo:block>
								</fo:table-cell>
								<fo:table-cell border="solid black 2px">
									<fo:block font-weight="bold" background-color="#cccccc" text-align="center">Okres</fo:block>
								</fo:table-cell>
								<fo:table-cell border="solid black 2px">
									<fo:block font-weight="bold" background-color="#cccccc" text-align="center">Uwagi</fo:block>
								</fo:table-cell>
							</fo:table-row>
						</fo:table-header>
						<fo:table-body>
							<xsl:apply-templates select="badanie/leki/lek"/>
						</fo:table-body>
					</fo:table>
				</fo:block>
				
				
				<fo:block font-size="9pt" font-family="Arial" padding-after="0.2cm" text-align="left"> 
					DOLEGLIWOŚCI ZE STRONY NARZĄDÓW I UKŁADÓW:
				</fo:block>
				
				<fo:block font-size="10pt" font-family="Arial" padding-after="1cm">Układ oddechowy: 
					<xsl:apply-templates select="badanie/dolegliwosci/uklad_oddechowy"/>
				</fo:block>
				<fo:block font-size="10pt" font-family="Arial" padding-after="1cm">Układ krążenia: 
					<xsl:apply-templates select="badanie/dolegliwosci/uklad_krazenia"/>
				</fo:block>
				<fo:block font-size="10pt" font-family="Arial" padding-after="1.5cm">Układ trawienny: 
					<xsl:apply-templates select="badanie/dolegliwosci/uklad_trawienny"/>
				</fo:block>
				
				<fo:block font-size="10pt" font-family="Arial">
					<fo:table table-layout="fixed" width="100%" border="solid black 1px">
					<fo:table-column column-width="180mm"/>
						<fo:table-body>
							<fo:table-row>
								<fo:table-cell border-top="solid black 1px" border-left="solid black 1px">
									<fo:block font-size="9pt" font-family="Arial" padding-before="0.1cm" padding-after="0.2cm" text-align="right" font-weight="bold">
									Uzupełnienia, opis stanu miejscowego</fo:block>
								</fo:table-cell>
							</fo:table-row>
							<fo:table-row>
								<fo:table-cell border-top="solid black 1px" border-left="solid black 1px">
									<fo:block font-size="10pt" font-family="Arial"><xsl:apply-templates select="badanie/opis"/></fo:block>
								</fo:table-cell>
							</fo:table-row>
						</fo:table-body>
					</fo:table>
				</fo:block>
				
			</fo:flow>
			
		</fo:page-sequence>

	</fo:root>
		
	</xsl:template>

<xsl:template match="pacjent">
	<fo:block><xsl:value-of select="concat(imie, '&#160;')"/></fo:block>
</xsl:template>

<xsl:template match="lek">
	<fo:table-row>
		
		<fo:table-cell border="solid black 1px">
			<fo:block font-family="Arial" text-align="center"><xsl:value-of select="@nazwa_leku"/></fo:block>
		</fo:table-cell>
		<fo:table-cell border="solid black 1px" >
			<fo:block font-family="Arial" text-align="center"><xsl:value-of select="dawka"/></fo:block>
		</fo:table-cell>
		<fo:table-cell border="solid black 1px" text-align="center">
			<fo:block font-family="Arial" text-align="center"><xsl:value-of select="okres_poczatek"/> - <xsl:value-of select="okres_koniec"/></fo:block>
		</fo:table-cell>
		<fo:table-cell border="solid black 1px">
			<fo:block font-family="Arial"><xsl:value-of select="uwagi"/></fo:block>
		</fo:table-cell>
	</fo:table-row>
</xsl:template>
	
<xsl:template name="dataCzas">
	<xsl:value-of select="java:format(java:java.text.SimpleDateFormat.new('dd MMMM yyyy, HH:mm:ss'), java:java.util.Date.new())"/>
</xsl:template>
	
</xsl:stylesheet>