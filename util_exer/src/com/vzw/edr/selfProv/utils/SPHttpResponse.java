package com.vzw.edr.selfProv.utils;

//Java imports
import java.io.Serializable;

/**
 * @author Ravi Vellanki, Jacob Metelitsa
 * 
 * This class will be used as a value object for holding
 * message responses. It stores the information such as if the
 * response processing has been successful, the sucess message, if any.
 */
public class SPHttpResponse implements Serializable {
	private boolean isResponseSuccess = true;

	/** Store the response message */
	private String responseMessage = null;

	/** Store the response error code */
	private String responseErrorCode = null;

	/** Store the response error message */
	private String responseErrorMessage = null;

	/** Store the response account number */
	private String responseAcctNumber = null;

	/** Store the response account number */
	private String responseMktId = null;

	/** Store the response region code */
	private int regionCode = 0;

	/** Store the customer id */
	private String visionCustomerId = null;

	/** Store the bill account number */
	private String visionBillAcctNumber = null;

	/** Store the bill type code */
	private String visionBillTypeCode = null;

	/**
	 * SPXmlResponseMsg Constructor.
	 */
	public SPHttpResponse() {
		responseErrorCode = "0";
	}

	/**
	 * SPXmlResponseMsg Constructor.
	 * @param responseMessage
	 */
	public SPHttpResponse(String responseMessage) throws SPFeatureException {
		this.responseMessage = responseMessage;
		extractResponse();
		//extractResponseErrorMessage();			
	}

	/**
	 *SPXmlResponseMsg Constructor.
	 * @param responseErrorCode
	 * @param responseErrorMessage	  
	 */
	public SPHttpResponse(
		String responseErrorCode,
		String responseErrorMessage) {
		isResponseSuccess = false;
		this.responseErrorCode = responseErrorCode;
		this.responseErrorMessage = responseErrorMessage;
//		System.out.println(responseErrorMessage);
	}

	/**
	 * Sets the responseMessage.
	 * @param responseMessage The responseMessage to set
	 */
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	/**
	 * Sets the responseMktId.
	 * @return void
	 */
	public void setResponseMktId() throws SPFeatureException {
		String mktidLit = "mktid=";
		int i = responseMessage.indexOf(mktidLit) + mktidLit.length();
		int j = responseMessage.indexOf(';', i);
		if (i < 0 || j < 0) {
			throw new SPFeatureException(
				"S0110",
				"Invalid Response from I2K Query: Market Id missing");
		}
		responseMktId = (responseMessage.substring(i, j)).trim();
	}

	/**
	 * Sets the responseAcctNumber.
	 * @return void
	 */
	public void setResponseAcctNumber() throws SPFeatureException {
		String acctLit = "custid=";
		int i = responseMessage.indexOf(acctLit) + acctLit.length();
		int j = responseMessage.indexOf(';', i);
		if (i < 0 || j < 0) {
			throw new SPFeatureException(
				"S0120",
				"Invalid Response from I2K Query: Account Number Missing");
		}
		responseAcctNumber = (responseMessage.substring(i, j)).trim();
	}

	/**
	 * Sets the regionCode.
	 * @return void
	 */
	public void setRegionCode() throws SPFeatureException {
		String regionLit1 = "<bgsaCode>";			// Common API
		String regionLit2 = "</bgsaCode>";			// Common API
		int i = responseMessage.indexOf(regionLit1) + regionLit1.length();
		int j = responseMessage.indexOf(regionLit2, i);
		if (i < 0 || j < 0) {
			throw new SPFeatureException(
				"S0130",
				"Invalid Response from Region Request: Region code missing");
		}

		String s = (responseMessage.substring(i, j)).trim();
		try {
			regionCode = Integer.parseInt(s);
		} catch (NumberFormatException e) {
		}
	}

	/**
	 * Sets the customer id for Vision
	 * @return void
	 */
	public void setVisionCustomerId() throws SPFeatureException {
		String regionLit1 = "<accountNo>";					// Common API
		String regionLit2 = "</accountNo>";					// Common API
		String accountNo = null;							// Common API
		int i = responseMessage.indexOf(regionLit1) + regionLit1.length();
		int j = responseMessage.indexOf(regionLit2, i);
		if (i < 0 || j < 0) {
			throw new SPFeatureException(
				"S0140",
				"Invalid Response from Region Request: Customer ID missing");
		}
		else {
			accountNo = responseMessage.substring(i, j).trim();
//System.err.println("accountNo: "+accountNo);
		}
		// Common API change - <accountNo>= <customer id>-<bill account number> e.g. 900826471-2
		int k = accountNo.indexOf("-");
		if (k<0)
			visionCustomerId = (accountNo.substring(0)).trim();
		else
			visionCustomerId = (accountNo.substring(0, k)).trim();
			
//System.err.println("visionCustomerId: "+visionCustomerId);
	}

	/**
	 * Sets the bill type code
	 * @return void
	 */
	public void setVisionBillTypeCode() throws SPFeatureException {
		String regionLit1 = "<billTypeCode>";		// Common API
		String regionLit2 = "</billTypeCode>";		// Common API
		int i = responseMessage.indexOf(regionLit1) + regionLit1.length();
		int j = responseMessage.indexOf(regionLit2, i);
		if (i < 0 || j < 0) {
			throw new SPFeatureException(
				"S0150",
				"Invalid Response from Region Request: Bill Type Code missing");
		}
		visionBillTypeCode = (responseMessage.substring(i, j)).trim();
	}

	/**
	 * Sets the bill account number
	 * @return void
	 */
	public void setVisionBillAcctNumber() throws SPFeatureException {
		String regionLit1 = "<accountNo>";					// Common API
		String regionLit2 = "</accountNo>";					// Common API
		String accountNo = null;							// Common API
		int i = responseMessage.indexOf(regionLit1) + regionLit1.length();
		int j = responseMessage.indexOf(regionLit2, i);
		if (i < 0 || j < 0) {
			throw new SPFeatureException(
				"S0160",
				"Invalid Response from Region Request: Bill Account Number missing");
		}
		else {
			accountNo = responseMessage.substring(i, j).trim();
//System.err.println("accountNo: "+accountNo);
		}
		
		// Common API change - <accountNo>= <customer id>-<bill account number> e.g. 900826471-2
		int k = accountNo.indexOf("-");
		if (k<0) {
			throw new SPFeatureException(
				"S0160",
				"Invalid Response from Region Request: Bill Account Number missing");
		}
		else
			visionBillAcctNumber = (accountNo.substring(k+1)).trim();
			
//System.err.println("visionBillAcctNumber: "+visionBillAcctNumber);

	}

	/**
	 * Returns the isResponseSuccess.
	 * @return boolean
	 */
	public boolean isResponseSuccess() {
		return isResponseSuccess;
	}

	/**
	 * Returns the responseErrorCode.
	 * @return String
	 */
	public String getResponseErrorCode() {
		return responseErrorCode;
	}

	/**
	 * Returns the responseMessage.
	 * @return String
	 */
	public String getResponseErrorMessage() {
		return responseErrorMessage;
	}

	/**
	 * Returns the responseMktId.
	 * @return String
	 */
	public String getResponseMktId() {
		return responseMktId;
	}

	/**
	 * Returns the responseAcctNumber.
	 * @return String
	 */
	public String getResponseAcctNumber() {
		return responseAcctNumber;
	}

	/**
	 * Returns the regionCode.
	 * @return int
	 */
	public int getRegionCode() {
		return regionCode;
	}

	/**
	 * Returns the visionCustomerId.
	 * @return String
	 */
	public String getVisionCustomerId() {
		return visionCustomerId;
	}

	/**
	 * Returns the visionBillAcctNumber.
	 * @return String
	 */
	public String getVisionBillAcctNumber() {
		return visionBillAcctNumber;
	}

	/**
	 * Returns the visionBillTypeCode.
	 * @return String
	 */
	public String getVisionBillTypeCode() {
		return visionBillTypeCode;
	}

	/**
	 * Sets the responseErrorCode.
	 * @return void
	 */
	private void extractResponse() throws SPFeatureException {
		if (responseMessage.startsWith("EDR ")) {
			extractEdrResponse();
		} else if (responseMessage.startsWith("<?xml") || responseMessage.startsWith("<service>")) {	// Common API
			extractXmlResponse();
		} else {
			extractValuePairResponse();
		}
		/*
		String errorCodeLit = "errorCode=";
		int i = responseMessage.indexOf(errorCodeLit) + errorCodeLit.length();
		int j = responseMessage.indexOf(';', i);
		responseErrorCode = (responseMessage.substring(i, j)).trim(); 
		if (!responseErrorCode.equals("0"))
		{
			isResponseSuccess = false;
		}
		*/
	}

	/**
	 * Sets the responseErrorCode and responseErrorMessage
	 * @return void
	 */
	private void extractValuePairResponse() throws SPFeatureException {
		String errorCodeLit = "errorCode=";
		int i = responseMessage.indexOf(errorCodeLit) + errorCodeLit.length();
		int j = responseMessage.indexOf(';', i);
		if (i < 0 || j < 0) {
			throw new SPFeatureException(
				"S0170",
				"Invalid Response from I2K: Error Code missing");
		}
		responseErrorCode = (responseMessage.substring(i, j)).trim();
		if (!responseErrorCode.equals("0")) {
			isResponseSuccess = false;
			String errorMsgLit = "errorMsg=";
			i = responseMessage.indexOf(errorMsgLit) + errorMsgLit.length();
			j = responseMessage.indexOf(';', i);
			responseErrorMessage = responseMessage.substring(i, j);
		}
	}

	/**
	 * Sets the responseErrorCode and responseErrorMessage
	 * @return void
	 */
	private void extractEdrResponse() {
		int i = responseMessage.indexOf(':', 4);
		if (i == -1) {
			isResponseSuccess = false;
			responseErrorMessage = "Bad response message from EDR";
			return;
		}

		responseErrorCode = (responseMessage.substring(4, i)).trim();
		if (!responseErrorCode.equals("0")) {
			isResponseSuccess = false;
			responseErrorMessage = (responseMessage.substring(i + 1)).trim();
		}
	}

	/**
	 * Sets the responseErrorCode and responseErrorMessage
	 * @return void
	 */
	private void extractXmlResponse() throws SPFeatureException {
		// Common API Change
		//String codeLit1 = "<ns:appl_response_code>";
		//String codeLit2 = "</ns:appl_response_code>";
		String codeLit1 = "<errorCode>";
		String codeLit2 = "</errorCode>";
		int i = responseMessage.indexOf(codeLit1) + codeLit1.length();
		int j = responseMessage.indexOf(codeLit2, i);
		if (i < 0 || j < 0) {
			throw new SPFeatureException(
				"S0180",
				"Invalid Response from VISION: Response Code missing");
		}
		responseErrorCode = (responseMessage.substring(i, j)).trim();
		int errCode = -1;
		try {
			errCode = Integer.parseInt(responseErrorCode);
		} catch (NumberFormatException e) {
		}

		if (errCode != 0) {
			isResponseSuccess = false;
			// Common API Change
			//String descLit1 = "<ns:appl_response_desc>";
			//String descLit2 = "</ns:appl_response_desc>";
			String descLit1 = "<errorMsg>";
			String descLit2 = "</errorMsg>";
			i = responseMessage.indexOf(descLit1, j) + descLit1.length();
			j = responseMessage.indexOf(descLit2, i);
			if (i < 0 || j < 0) {
				throw new SPFeatureException(
					"S0190",
					"Invalid Response from VISION: Response Description missing");
			}
			responseErrorMessage = (responseMessage.substring(i, j)).trim();
		}
	}
}
