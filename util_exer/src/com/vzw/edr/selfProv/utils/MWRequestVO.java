package com.vzw.edr.selfProv.utils;

import java.util.Vector;
import java.util.Iterator;

/**
 * @author Anil
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */

public class MWRequestVO extends RequestVO {

	public MWRequestVO() {
		super();
	}

	public String getAddSvcIdStr() {
		return svcIdVecToStr(addServiceIds);

	}

	public String getDeleteSvcIdStr() {
		return svcIdVecToStr(deleteServiceIds);
	}

	private String svcIdVecToStr(Vector vecSvcId) {
		StringBuffer svcIdBfr = new StringBuffer();

		Iterator svcItr = vecSvcId.iterator();
		while (svcItr.hasNext()) {
			if ((svcIdBfr == null)
				|| (svcIdBfr.toString().compareTo("") == 0)) {
				svcIdBfr.append((String) svcItr.next());
			} else {
				svcIdBfr.append("-" + (String) svcItr.next());
			}
		}
		return svcIdBfr.toString();
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("[mdn=" + mdn);
		sb.append(", min=" + min);
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
