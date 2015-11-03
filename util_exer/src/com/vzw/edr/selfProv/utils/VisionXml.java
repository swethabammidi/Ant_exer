package com.vzw.edr.selfProv.utils;

public class VisionXml {
	
	/*
	 * Region request XML
	 * 		
	  <?xml version="1.0" encoding="UTF-8" ?> 
	- <remote_service xmlns="http://verizonwireless.com/vision/service/edr" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
	- <service_header>
	  <system_name>VISION_EAST</system_name> 
	  <service_name>mdn_info</service_name> 
	  <sub_service_name /> 
	  <edit_type>Retrieve</edit_type> 
	  <carrier_id>VZW-EDR</carrier_id> 
	  <transaction_status_code /> 
	  <appl_response_code /> 
	  <appl_response_desc /> 
	  <appl_response_type /> 
	  <user_id>00007890</user_id> 
	  <password>secret</password> 
	  </service_header>
	- <service_body>
	- <mdn_info>
	  <mdn>7322974838</mdn> 
	  </mdn_info>
	  </service_body>
	  </remote_service>	
	*/

	/*
	 * Region response XML
	 * 	
	  <?xml version="1.0" encoding="UTF-8" ?> 
	- <remote_service xmlns="http://verizonwireless.com/vision/service/edr">
	- <service_header>
	  <system_name>VISION_EAST</system_name> 
	  <service_name>mdn_info</service_name> 
	  <sub_service_name /> 
	  <edit_type>Retrieve</edit_type> 
	  <carrier_id>VZW-EDR</carrier_id> 
	  <transaction_status_code>00</transaction_status_code> 
	  <appl_response_code>00</appl_response_code> 
	  <appl_response_desc>Success</appl_response_desc> 
	  <appl_response_type /> 
	  <user_id>00007890</user_id> 
	  <password>secret</password> 
	  </service_header>
	- <service_body>
	- <mdn_info>
	  <mdn>7322974838</mdn> 
	  <customer_id>240678</customer_id> 
	  <bill_account_number>2</bill_account_number> 
	  <bill_type_code>C</bill_type_code> 
	  <bgsa_id>240</bgsa_id> 
	  </mdn_info>
	  </service_body>
	  </remote_service>  
	*/

/**********************************************************************************************
	public static final String headerXml =
		"<?xml version=\"1.0\" encoding=\"UTF-8\" ?>"
			+ "<remote_service xmlns=\"http://verizonwireless.com/vision/service/edr\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">"
			+ "<service_header>"
			+ "<system_name>";
	
	public static final String regionXmlUser =
		"</system_name> "
			+ "<service_name>mdn_info</service_name>"
			+ "<sub_service_name />"
			+ "<edit_type>Retrieve</edit_type>"
			+ "<carrier_id>VZW-EDR</carrier_id>"
			+ "<transaction_status_code />"
			+ "<appl_response_code />"
			+ "<appl_response_desc />"
			+ "<appl_response_type />"
			+ "<user_id>";

	public static final String regionXmlPsw = "</user_id>" + "<password>";

	public static final String regionXmlMdn =
		"</password>"
			+ "</service_header>"
			+ "<service_body>"
			+ "<mdn_info>"
			+ "<mdn>";

	public static final String regionXmlFooter =
		"</mdn>" + "</mdn_info>" + "</service_body>" + "</remote_service>";
		
*********************************************************************************************/

	/*
	* Feature XML
	* 	      
	<?xml version="1.0" encoding="UTF-8"?>
	<remote_service xmlns="http://verizonwireless.com/vision/service/edr" 
		xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
		<service_header>
			<system_name>VISION_EAST</system_name>
			<service_name>save_features</service_name>
			<sub_service_name/>
			<edit_type>create</edit_type>
			<carrier_id>VZW-EDR</carrier_id>
			<transaction_status_code/>
			<appl_response_code/>
			<appl_response_desc/>
			<appl_response_type/>
			<user_id>00007890</user_id>
			<password>secret</password>
		</service_header>
		<service_body>
			<save_features>
				<mdn>7322974838</mdn>
				<customer_id>240678</customer_id>
				<bill_account_number>2</bill_account_number>
	           		<bill_type_code>C</bill_type_code>
	           		<feature>
	           		 	<sfo_id>12345</sfo_id>
	           		 	<sfo_indicator>A</sfo_indicator>
				 	<effective_date>08/07/2003</effective_date>
	           		</feature>
			</save_features>
		</service_body>
	</remote_service>
	
	*/
/********************************************************************************************
	public static final String featureXmlUser =
		"</system_name>"
			+ "<service_name>save_features</service_name>"
			+ "<sub_service_name /> "
			+ "<edit_type>create</edit_type>"
			+ "<carrier_id>VZW-EDR</carrier_id>"
			+ "<transaction_status_code />"
			+ "<appl_response_code />"
			+ "<appl_response_desc />"
			+ "<appl_response_type />"
			+ "<user_id>";

	public static final String featureXmlPsw = "</user_id>" + "<password>";

	public static final String featureXmlMdn =
		"</password>"
			+ "</service_header>"
			+ "<service_body>"
			+ "<save_features>"
			+ "<mdn>";

	public static final String featureXmlCustomerId =
		"</mdn>" + "<customer_id>";

	public static final String featureXmlBillAccountNumber =
		"</customer_id>" + "<bill_account_number>";

	public static final String featureXmlBillTypeCode =
		"</bill_account_number>" + "<bill_type_code>";

	public static final String featureXmlSfo =
		"</bill_type_code>" + "<feature>" + "<sfo_id>";

	public static final String featureXmlDate =
		"</sfo_id>" + "<sfo_indicator>A</sfo_indicator>" + "<effective_date>";

	public static final String featureXmlFooter =
		"</effective_date>"
			+ "</feature>"
			+ "</save_features>"
			+ "</service_body>"
			+ "</remote_service>";

*******************************************************************************************/



	/*************************************************************************
	 * NEW Common API
	 * -- serviceName: mtnDetailInquiry
	 * -- subServiceName: retrieveBasicMtnInfo (Not Required)
	 * 
	 * <service>
	 * 	<serviceHeader>
	 * 		<billingSys>VISION_EAST</billingSys>
	 * 		<clientId>VZW-EDR</clientId>
	 * 		<userId>00100625</userId>
	 * 		<password>DIGITAL</password>
	 * 		<serviceName>mtnDetailInquiry</serviceName>
	 * 	</serviceHeader>
	 * 	<serviceBody>
	 * 		<serviceRequest>
	 * 			<subServiceName>retrieveBasicMtnInfo</subServiceName>
	 * 			<mtn>8436552742</mtn>
	 * 		</serviceRequest>
	 * 	</serviceBody>
	 * </service>
	 * 
	 **************************************************************************/
	
	public static String getMtnDetailInquiryXML(String billSysNm, String userID, String passwd, String mtn,String visionClientId ) {
		
		
		StringBuffer sb = new StringBuffer();
		
		
		
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>");
		sb.append("<service>");
		sb.append("<serviceHeader>");
		sb.append("<billingSys>");
		sb.append(billSysNm);
		sb.append("</billingSys>");
//		sb.append("<clientId>VZW-EDR</clientId>");
		sb.append("<clientId>");
		sb.append(visionClientId);
		sb.append("</clientId>");
		sb.append("<userId>");
		sb.append(userID);
		sb.append("</userId>");
		sb.append("<password>");
		sb.append(passwd);
		sb.append("</password>");
		sb.append("<serviceName>mtnDetailInquiry</serviceName>");
		sb.append("</serviceHeader>");
		sb.append("<serviceBody>");
		sb.append("<serviceRequest>");
		sb.append("<subServiceName>retrieveBasicMtnInfo</subServiceName>");
		sb.append("<mtn>");
		sb.append(mtn);
		sb.append("</mtn>");
		sb.append("</serviceRequest>");
		sb.append("</serviceBody>");
		sb.append("</service>");
		
		return sb.toString();
		
	}
	
			
	/**********************************************************************
	 * New Common API 
	 * -- serviceName: pricePlanFeatureUpgrade
	 * -- subServiceName: saveOrderLineItem
	 * 
	 * <service>
	 * 	<serviceHeader>
	 * 		<billingSys>VISION_EAST</billingSys>
	 * 		<clientId>VZW-EDR</clientId>
	 * 		<userId>00109450</userId>
	 * 		<password>OLYMPICS</password>
	 * 		<serviceName>pricePlanFeatureUpgrade</serviceName>
	 * 	</serviceHeader>
	 * 	<serviceBody>
	 * 		<serviceRequest>
	 * 			<subServiceName>saveOrderLineItem</subServiceName>
	 * 			<accountNo>203824480-1</accountNo>
	 * 			<billTypeCode>C</billTypeCode>
	 * 			<effectivedate>08/14/2005</effectivedate>
	 * 			<orderLineItemList>
	 * 				<currentMtn>7045647946</currentMtn>
	 * 				<features>
	 * 					<specialFeatureOfferingId>48553</specialFeatureOfferingId>
	 * 					<featureSave_ind>D</featureSave_ind>
	 * 				</features>
	 * 			</orderLineItemList>
	 * 		</serviceRequest>
	 * 	</serviceBody>
	 * </service>
	 * 
	 ***********************************************************************/
			
	public static String getPricePlanFeatureUpgradeXML(String billSysNm, String userID, String passwd, String currentMtn, 
							String effectDate, String acctNo, String billingTypeCode, String sfo_id, String featureSaveInd, String visionClientId) {
		
		StringBuffer sb = new StringBuffer();
		
		
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>");
		sb.append("<service>");
		sb.append("<serviceHeader>");
		sb.append("<billingSys>");
		sb.append(billSysNm);
		sb.append("</billingSys>");
//		sb.append("<clientId>VZW-EDR</clientId>");
		sb.append("<clientId>");
		sb.append(visionClientId);
		sb.append("</clientId>");
		sb.append("<userId>");
		sb.append(userID);
		sb.append("</userId>");
		sb.append("<password>");
		sb.append(passwd);
		sb.append("</password>");
		sb.append("<serviceName>pricePlanFeatureUpgrade</serviceName>");
		sb.append("</serviceHeader>");
		sb.append("<serviceBody>");
		sb.append("<serviceRequest>");
		sb.append("<subServiceName>saveOrderLineItem</subServiceName>");
		sb.append("<accountNo>");
		sb.append(acctNo);
		sb.append("</accountNo>");
		sb.append("<billTypeCode>");
		sb.append(billingTypeCode);
		sb.append("</billTypeCode>");
		sb.append("<effectivedate>");
		sb.append(effectDate);
		sb.append("</effectivedate>");
		sb.append("<orderLineItemList>");
		sb.append("<currentMtn>");
		sb.append(currentMtn);
		sb.append("</currentMtn>");
// Start possible loop	
		sb.append("<features>");
		sb.append("<specialFeatureOfferingId>");
		sb.append(sfo_id);
		sb.append("</specialFeatureOfferingId>");
		sb.append("<featureSave_ind>");
		sb.append(featureSaveInd);
		sb.append("</featureSave_ind>");
		sb.append("</features>");
// End possible loop 
		sb.append("</orderLineItemList>");
		sb.append("</serviceRequest>");
		sb.append("</serviceBody>");
		sb.append("</service>");
		
		return sb.toString();
		
	}
			
			
}
