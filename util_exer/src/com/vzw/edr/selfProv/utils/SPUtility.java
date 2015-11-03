package com.vzw.edr.selfProv.utils;

import java.util.Iterator;
import java.util.Vector;
import java.util.StringTokenizer;
import javax.servlet.ServletInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;


/**
 * @author Anil
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class SPUtility {

	public SPUtility() {
	}

	public SelfProvDTO convertToDTO(
		RequestVO reqVO,
		AccountVO accVO,
		String status,
		String errorCode,
		long seqId,
		String logTag) {

		SelfProvDTO selfProvReqData = new SelfProvDTO();
		selfProvReqData.setEventDate(reqVO.eventDate.replace('T', ' '));
		selfProvReqData.setEventId(reqVO.reqEventId);
		selfProvReqData.setAddFeatureCode(getHypFC(reqVO.addServiceIds));
		selfProvReqData.setDeleteFeatureCode(getHypFC(reqVO.deleteServiceIds));

		selfProvReqData.setMdn((new Long(accVO.mdn)).longValue());
		selfProvReqData.setMin((new Long(accVO.min)).longValue());
		selfProvReqData.setPrepay_ind(accVO.prepay_ind);
		selfProvReqData.setPopId(accVO.billSysId);
		// set the seqId after getting DTO
		selfProvReqData.setEdrEventId(seqId);
		selfProvReqData.setVendorId((new Long(reqVO.vendorId)).longValue());
		selfProvReqData.setChannelId(new Long(reqVO.channelId).longValue());
		if ((accVO.unid != null) && (accVO.unid.compareTo("") != 0)) {
			selfProvReqData.setUnid((new Long(accVO.unid)).longValue());
		}
		selfProvReqData.setStatus(status);
		selfProvReqData.setErrorCode(errorCode);
		selfProvReqData.setLogTag(logTag);
		return selfProvReqData;
	}

	public String getHypFC(Vector serviceIds) {
		String fc = "";
		Iterator it = serviceIds.iterator();
		if (it.hasNext())
			fc = (String) it.next();

		while (it.hasNext()) {
			fc = fc + "-" + (String) it.next();
		}
		return fc;
	} // end getHypFC

	public static int getErrorCode(String errMsg) {
		StringTokenizer tok = new StringTokenizer(errMsg, ":");
		String errorCode = "";
		if (tok.hasMoreTokens()) {
			errorCode = tok.nextToken();
		} else {
			errorCode = "0";
		}
		int eCode = 0;
		try {
			eCode = new Integer(errorCode).intValue();
		} catch (Exception e) {
			eCode = 0;
		}
		return eCode;
	}
	public static String getErrorMsg(String errMsg) {
		StringTokenizer tok = new StringTokenizer(errMsg, ":");
		if (tok.countTokens() > 1) {
			tok.nextToken();
			return tok.nextToken();
		} else
			return "";
	}

	public static String getRespDesc(int errCode) {
		if (errCode < 200 || errCode > 209)					// PrePay RBT Change
			return "Response description is not yet defined";
		return SPConstants.RESP_DESC[errCode - 200];
	}
	public static String getStringFromInputStream(ServletInputStream inputStream){
		StringBuffer sb = new StringBuffer(); 
		String 	inputLine = null;	
		try{
			BufferedReader in =
				new BufferedReader(
					new InputStreamReader(inputStream));
			while ((inputLine = in.readLine()) != null) {
				sb.append(inputLine);
			}
			in.close();
		}
		catch(Exception e){
		}
		return sb.toString();
	}
}
