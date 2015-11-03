package com.vzw.edr.selfProv.utils;

import java.io.Serializable;
import java.util.Vector;
import java.util.HashMap;

/**
 * @author gopi
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class RequestVO implements Serializable {
	public String reqType; 
	public String mdn;
	public String min;
	public String prepay_ind;	// PrePay RBT Change
	public String vendorId;
	public String channelId;
	public String reqEventId;
	public String eventDate;
	public String action;
	public HashMap billSysMap;
	public Vector addServiceIds;
	public Vector deleteServiceIds;

	public String logTag;
	public RequestVO() {
		billSysMap = new HashMap();
		addServiceIds = new Vector();
		deleteServiceIds = new Vector();

	}

	public String getAddSvcIdStr() {
		return null;

	}

	public String getDeleteSvcIdStr() {
		return null;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("[reqType=" + mdn);
		sb.append(", mdn=" + mdn);
		sb.append(", min=" + min);
		sb.append(", prepay_ind=" + prepay_ind);	// PrePay RBT Change
		sb.append(", logTag=" + logTag);
		sb.append(", vendorId=" + vendorId);
		sb.append(", channelId=" + channelId);
		sb.append(", reqEventId=" + reqEventId);
		sb.append(", eventDate=" + eventDate);
		sb.append(", action=" + action);
		sb.append(", addServiceIds=" + addServiceIds.toString());
		sb.append(", deleteServiceIds=" + deleteServiceIds.toString());
		sb.append(", billSysMap=" + billSysMap.toString());
		sb.append("]");
		return sb.toString();
	}
}
