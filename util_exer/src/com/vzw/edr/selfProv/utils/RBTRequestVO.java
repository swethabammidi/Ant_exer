package com.vzw.edr.selfProv.utils;

import java.util.Vector;
import java.util.LinkedList;
import java.util.Iterator;

/**
 * @author Anil
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */

public class RBTRequestVO extends RequestVO {
	public Vector statusServiceIds;
	public Vector contents;

	public RBTRequestVO() {
		super();
		statusServiceIds = new Vector();
		contents = new Vector();

	}
	private String getContentStr() {
		StringBuffer cntStr = new StringBuffer();
		Content cntObj = null;
		Iterator cntItr = contents.iterator();
		while (cntItr.hasNext()) {
			cntObj = (Content) cntItr.next();
			cntStr.append("[service=");
			cntStr.append(cntObj.getService());
			cntStr.append(",action=");
			cntStr.append(cntObj.getAction());
			cntStr.append(",default=");
			cntStr.append(cntObj.getDefaults());
			cntStr.append(",contentId=");
			cntStr.append(cntObj.getContentId());
			cntStr.append("]");
		}
		return cntStr.toString();
	}
	public String getDBContentStr(String type) {

		if (type == null
			|| !(type.equalsIgnoreCase("add") || type.equalsIgnoreCase("delete")))
			return null;

		LinkedList cntLst = new LinkedList();
		Content cntObj = null;
		Iterator cntItr = contents.iterator();
		StringBuffer sb = new StringBuffer();
		boolean firstTimeFlag = true;
		while (cntItr.hasNext()) {
			cntObj = (Content) cntItr.next();
			if ((cntObj.getAction() != null)
				&& cntObj.getAction().equalsIgnoreCase(type)) {
				if (firstTimeFlag) {
					cntLst.addFirst(cntObj.getContentId());
					firstTimeFlag = false;
				} else {
					cntLst.addLast(",");
					if ((cntObj.getDefaults() != null)
						&& cntObj.getDefaults().equalsIgnoreCase("true"))
						cntLst.addFirst(cntObj.getContentId());
					else
						cntLst.addLast(cntObj.getContentId());

				}

			}
		}
		cntItr = cntLst.iterator();
		while (cntItr.hasNext())
			sb.append((String) cntItr.next());

		return sb.toString();
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("[mdn=" + mdn);
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
		sb.append(", statusServiceIds=" + statusServiceIds.toString());
		sb.append(",Content=" + getContentStr());
		sb.append(", billSysMap=" + billSysMap.toString());
		sb.append("]");
		return sb.toString();
	}

}
