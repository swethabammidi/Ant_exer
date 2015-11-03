package com.vzw.edr.selfProv.utils;

public class MobileWebException extends Exception {
	private String errorCode = null;
	private String errorMessage = null;

	/**
	 * Constructor forMobileWebException .
	 * @param String
	 * @param String	  
	 */
	public MobileWebException(String errorCode, String errorMessage) {
		super();
		setErrorCode(errorCode);
		setErrorCode(errorMessage);
	}

	/**
	 * Returns the errorCode.
	 * @return String
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * Returns the errorMessage.
	 * @return String
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * Sets the errorCode.
	 * @param errorCode The errorCode to set
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * Sets the errorMessage.
	 * @param errorMessage The errorMessage to set
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
