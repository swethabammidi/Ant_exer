package com.vzw.edr.selfProv.utils;

/**
 * @author gopi
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class ProcessingException extends Exception {

	public static final int GLOBAL = 204;
	public static final int TEMP = 2;
	public static final int VALIDITY = 203;
	public static final int NOT_AVAILABLE = 205;
	public static final int PARSING_ERROR = 202;
	public static final int BAD_REQ_TYPE = 201;

	public static final String[] ERR_STR =
		{
			"Success",
			"MTN Validation Error.",
			"Temporary Failure.",
			"Global Error." };

	// String _action;
	int _exceptionType; // one of GLOBAL, TEMP or VALITITY
	String _logMsg;
	String _MDN;
	String _MIN;
	String _actStatus;
	String _provId;

	public ProcessingException(int exType, String logMsg) {
		super(logMsg);
		_exceptionType = exType;
		_logMsg = logMsg;

	}

	public int getExceptionType() {
		return _exceptionType;
	}

	public String getExceptionMessage() {

		if (_logMsg == null || _logMsg.trim().equals(""))
			return getMessage();

		return _logMsg;
	}

	public ProcessingException(String msg) {
		super(msg);
	}

}
