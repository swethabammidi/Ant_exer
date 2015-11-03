package com.vzw.edr.selfProv.utils;

/**
 * @author Anil
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */

public class Content {
	private String service = null;
	private String action = null;
	private String defaults = null;
	private String contentId = null;

	// default constructor
	public Content() {
		super();
	}

	//	constructor
	public Content(
		String service,
		String action,
		String contentId,
		String defaults) {
		super();
		this.service = service;
		this.action = action;
		this.contentId = contentId;
		this.defaults = defaults;
	}

	/**
	 * Returns the service.
	 * @return String
	 */
	public String getService() {
		return service;
	}

	/**
	 * Returns the Action.
	 * @return String
	 */
	public String getAction() {
		return action;
	}

	/**
	 * Returns the content_id.
	 * @return String
	 */
	public String getContentId() {
		return contentId;
	}

	/**
	 * Returns the defaults.
	 * @return boolean
	 */
	public String getDefaults() {
		return defaults;
	}

	/**
		 * Sets the service.
		 * @param service The service to set
		 */
	public void setService(String service) {
		this.service = service;
	}

	/**
		 * Sets the action.
		 * @param action The action to set
		 */
	public void setAction(String action) {
		this.action = action;
	}

	/**
		 * Sets the contentId.
		 * @param contentId The contentId to set
		 */
	public void setContentId(String contentId) {
		this.contentId = contentId;
	}
	/**
		 * Sets the defaults.
		 * @param defaults The defaults to set
		 */
	public void setDefaults(String defaults) {
		this.defaults = defaults;
	}

}
