package com.vzw.edr.selfProv.utils;

import java.io.Serializable;

/**
 * @author db2admin
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class SelfProvDTO implements Serializable {
	private String eventId = "";
	private long unid = 0;
	private long mdn = 0;
	private long min = 0;
	private String prepay_ind = null;		// PrePay RBT Change
	private String addFeatureCode = null;
	private String deleteFeatureCode = null;
	private long vendorId = 0;
	private long channelId = 0;
	private String eventDate = null;
	private String popId = null;
	private String dateSent = null;
	private String status = null;
	private String errorCode = null;
	private long edrEventId = 0;
	private String logTag = null;
	private String addContentIds = null;
	private String defaults = null;
	private String deleteContentIds = null;

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("[logTag=" + logTag);
		sb.append(", eventId=" + eventId);
		sb.append(", unid=" + unid);
		sb.append(", mdn=" + mdn);
		sb.append(", min=" + min);
		sb.append(", prepay_ind=" + prepay_ind);		// PrePay RBT Change
		sb.append(", addFeatureCode=" + addFeatureCode);
		sb.append(", deleteFeatureCode=" + deleteFeatureCode);
		sb.append(", vendorId=" + vendorId);
		sb.append(", channelId=" + channelId);
		sb.append(", eventDate=" + eventDate);
		sb.append(", popId=" + popId);
		sb.append(", dateSent=" + dateSent);
		sb.append(", status=" + status);
		sb.append(", errorCode=" + errorCode);
		sb.append(", edrEventId=" + edrEventId);
		sb.append(", addContentIds=" + addContentIds);
		sb.append(", defaults=" + defaults);
		sb.append(", deleteContentIds" + deleteContentIds);
		sb.append("]");
		return sb.toString();
	}
	/**
	 * Constructor for SelfProvDTO.
	 */
	public SelfProvDTO() {
		super();
	}

	/**
	 * Returns the eventId.
	 * @return eventId
	 */
	public String getEventId() {
		return eventId;
	}

	/**
	 * Returns the unid.
	 * @return long
	 */
	public long getUnid() {
		return unid;
	}

	/**
	 * Returns the mdn.
	 * @return long
	 */
	public long getMdn() {
		return mdn;
	}

	/**
	 * Returns the min.
	 * @return long
	 */
	public long getMin() {
		return min;
	}
	
	/**
	 * Returns the prepay_ind.
	 * @return String
	 */
	public String getPrepay_ind() {		// PrePay RBT Change
		return prepay_ind;
	}

	/**
	 * Returns the addFeatureCode.
	 * @return String
	 */
	public String getAddFeatureCode() {
		return addFeatureCode;
	}

	/**
	 * Returns the deleteFeatureCode.
	 * @return String
	 */
	public String getDeleteFeatureCode() {
		return deleteFeatureCode;
	}

	/**
	 * Returns the vendorId.
	 * @return String
	 */
	public long getVendorId() {
		return vendorId;
	}

	/**
	 * Returns the eventDate.
	 * @return String
	 */
	public String getEventDate() {
		return eventDate;
	}

	/**
	 * Returns the popId.
	 * @return String
	 */
	public String getPopId() {
		return popId;
	}

	/**
	 * Returns the dateSent.
	 * @return String
	 */
	public String getDateSent() {
		return dateSent;
	}

	/**
	 * Returns the status.
	 * @return String
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Returns the errorCode.
	 * @return String
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * Returns the edrId.
	 * @return edrId
	 */
	public long getEdrEventId() {
		return edrEventId;
	}

	/**
	 * Returns the addContentIds.
	 * @return String
	 */
	public String getAddContentIds() {
		return addContentIds;
	}

	/**
	 * Returns the defaults.
	 * @return String
	 */
	public String getDefaults() {
		return defaults;
	}

	/**
	 * Returns the deleteContentIds.
	 * @return String
	 */
	public String getDeleteContentIds() {
		return deleteContentIds;
	}

	/**
	 * Sets the eventId.
	 * @paramunid The eventId to set
	 */
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	/**
	 * Sets the edrId.
	 * @paramunid The edrId to set
	 */
	public void setEdrEventId(long edrEventId) {
		this.edrEventId = edrEventId;
	}

	/**
	 * Sets the unid.
	 * @paramunid The unid to set
	 */
	public void setUnid(long unid) {
		this.unid = unid;
	}

	/**
	 * Sets the mdn.
	 * @param mdn The mdn to set
	 */
	public void setMdn(long mdn) {
		this.mdn = mdn;
	}

	/**
	 * Sets the min.
	 * @param min The min to set
	 */
	public void setMin(long min) {
		this.min = min;
	}
	
	/**
	 * Set the prepay_ind.
	 * @param String
	 */
	public void setPrepay_ind(String ppi) {		// PrePay RBT Change
		this.prepay_ind = ppi;
	}

	/**
	 * Sets the addFeatureCode.
	 * @param featureCode The addFeatureCode to set
	 */
	public void setAddFeatureCode(String addFeatureCode) {
		this.addFeatureCode = addFeatureCode;
	}

	/**
	 * Sets the deleteFeatureCode.
	 * @param deletefeatureCode The deleteFeatureCode to set
	 */
	public void setDeleteFeatureCode(String deleteFeatureCode) {
		this.deleteFeatureCode = deleteFeatureCode;
	}

	/**
	 * Sets the vendorId.
	 * @param vendorId The vendorId to set
	 */
	public void setVendorId(long vendorId) {
		this.vendorId = vendorId;
	}

	/**
	 * Sets the eventDate.
	 * @param eventDate The eventDate to set
	 */
	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}

	/**
	 * Sets the popId.
	 * @param popId The popId to set
	 */
	public void setPopId(String popId) {
		this.popId = popId;
	}

	/**
	 * Sets the dateSent.
	 * @param dateSent The dateSent to set
	 */
	public void setDateSent(String dateSent) {
		this.dateSent = dateSent;
	}

	/**
	 * Sets the status.
	 * @param  status The status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * Sets the errorCode.
	 * @param errorCode The errorCode to set
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * Sets the addContentIds.
	 * @param addContentIds The addContentIds to set
	 */
	public void setAddContentIds(String addContentIds) {
		this.addContentIds = addContentIds;
	}

	/**
	 * Sets the defaults.
	 * @param defaults The defaults to set
	 */
	public void setDefaults(String defaults) {
		this.defaults = defaults;
	}

	/**
	 * Sets the deleteContentIds.
	 * @param deleteContentIds The deleteContentIds to set
	 */
	public void setDeleteContentIds(String deleteContentIds) {
		this.deleteContentIds = deleteContentIds;
	}

	/**
	 * @return
	 */
	public long getChannelId() {
		return channelId;
	}

	/**
	 * @param string
	 */
	public void setChannelId(long string) {
		channelId = string;
	}

	/**
	 * @return
	 */
	public String getLogTag() {
		return logTag;
	}

	/**
	 * @param string
	 */
	public void setLogTag(String string) {
		logTag = string;
	}

}
