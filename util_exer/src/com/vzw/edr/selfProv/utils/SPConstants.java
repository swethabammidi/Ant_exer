package com.vzw.edr.selfProv.utils;

public class SPConstants {
	/** The jndi name for data source */
	// public static final String JNDI_NAME_DATA_SOURCE = "java:comp/env/jdbc/mobileWebDS";

	/** The jndi name for Translator Local Ref **/
	// public static final String JNDI_NAME_MOBILEWEB_LOCAL_HOME = "java:comp/env/ejb/MobileWeb";
	// Let's define the error/reposne codes and meesage here, so every one can use it
	public static final int SUCCESS = 200;
	public static final int INVALID_REQUEST_TYPE = 201;
	public static final int INVALID_REQUEST = 202;
	public static final int INVALID_MDN_MIN = 203;
	public static final int EDR_ERROR = 204;
	public static final int SERVICE_NOT_AVAILABLE = 205;
	public static final int VBS_ERROR = 206;
	public static final int VBS_DUP_ERROR = 207;
	public static final int PREPAY_CONFIRMED = 208;				// PrePay RBT Change
	public static final int PREPAY_POSTPAY_MISMATCH = 209;		// PrePay RBT Change

//	Added For BlockPixPlaces

	public static final String RESP_CODE_210 	= "210";	
	public static final String RESP_CODE_211 	= "211";
	public static final String RESP_CODE_212 	= "212";
	public static final String RESP_DESC_210 	= "Active MDN";		// 'OK' or 'PE'
	public static final String RESP_DESC_211 	= "Suspended MDN";	// 'SU'
	public static final String RESP_DESC_212 	= "Canceled MDN";	// 'TX' 'TA'	

	public static final String[] RESP_DESC =
		{
			"SUCCESS",
			"Invalid Request Type received in request",
			"Request XML does not conform to the schema",
			"MDN/MIN Validation Error. MDN/MIN does not exist in EDR",
			"EDR Internal Processing Error",
			"The request service is not available from the service provider supporting this subscriber",
			"Unable to submit the Order",
			"Duplicate Order",
			"Prepay Account Confirmed",							// PrePay RBT Change
			"Prepay-Postpay mismatch for this account" };		// PrePay RBT Change

}
