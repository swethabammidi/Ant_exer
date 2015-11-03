package com.vzw.edr.selfProv.utils;

import java.io.Serializable;

/**
 * @author gopi
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class AccountVO implements Serializable {

	public String unid;
	public String mdn;
	public String min;
	public String prepay_ind;		// PrePay RBT Change
	public String esn;
	public String marketId;
	public String billSysId;
	public String cust_ref;

}
