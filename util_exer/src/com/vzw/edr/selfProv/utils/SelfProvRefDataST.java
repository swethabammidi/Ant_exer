package com.vzw.edr.selfProv.utils;

import java.util.Properties;

/**
 * @author gopi
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class SelfProvRefDataST {

	protected SelfProvRefDataST() {
	}

	static private SelfProvRefDataST _instance = null;

	static public SelfProvRefDataST instance() {
		if (_instance == null) {
			_instance = new SelfProvRefDataST();
			return _instance;
		} else {
			return _instance;
		}
	}

	private Properties _vbsUrl = null;
	private Properties _vendorUrl = null;
	/**
	 * Returns the _vbsUrl.
	 * @return Properties
	 */
	public Properties getVbsUrl() {
		return _vbsUrl;
	}

	/**
	 * Sets the _vbsUrl.
	 * @param _vbsUrl The _vbsUrl to set
	 */
	public void setVbsUrl(Properties _vbsUrl) {
		this._vbsUrl = _vbsUrl;
	}

	/**
	 * Returns the _vbsUrl.
	 * @return Properties
	 */
	public Properties getVendorUrl() {
		return _vendorUrl;
	}

	/**
	 * Sets the _vbsUrl.
	 * @param _vbsUrl The _vbsUrl to set
	 */
	public void setVendorUrl(Properties _vendorUrl) {
		this._vendorUrl = _vendorUrl;
	}

}
