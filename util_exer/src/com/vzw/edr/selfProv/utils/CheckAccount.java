package com.vzw.edr.selfProv.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import org.apache.log4j.Logger;

/**
 * @author Anil
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */

public class CheckAccount {
	private static Logger L =
		Logger.getLogger(SPProps.getBELogName(CheckAccount.class));
	private static int MDN = 1;
	private static int MIN = 2;
	private static final String dsNameG = "java:comp/env/jdbc/SelfProvDSGeneva";

	public static boolean getAccount(
		String logTag,
		String mdn,
		int selParam,
		AccountVO accVO)
		throws ProcessingException {
		// Get the account details from geneva
		if (!getAccountFromGeneva(logTag, mdn, selParam, accVO))
			return false;
		else
			return true;
	}

	private static boolean getAccountFromGeneva(
		String logTag,
		String number,
		int selParam,
		AccountVO acctVO)
		throws ProcessingException {
		L.debug(
			"getAccountFromGeneva():"
				+ logTag
				+ "Entering with number="
				+ number
				+ "; type="
				+ selParam);
		InitialContext ic = null;
		DataSource ds = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		boolean retVal = false;
		String dgtID = SPProps.getProdAttrProps().getProperty("PROD_DGT");
		String subID = SPProps.getProdAttrProps().getProperty("SUB_PREPAY");
		if((dgtID==null || dgtID.trim().length()<1) || (subID==null || subID.trim().length()<1)) {
			L.error("getAccountFromGeneva():"
					+ logTag + "During getAccountFromGeneva(): Cannot get Product ID for DGT or Sub ID for Prepay");
			throw new ProcessingException(ProcessingException.GLOBAL,"During getAccountFromGeneva(): Cannot get Product ID for DGT or Sub ID for Prepay");
		}
		
		String mdnQuery = "select a.customer_ref, a.event_source as mdn, b.account_num, c.bill_handling_code, " +		
				"d.event_source as min from pvcusteventsource4 a, pvaccount11 b, pvaccountdetails4 c, " +
				"pvcusteventsource4 d where a.end_dtm is null and a.event_type_id = ? and " +
				"a.event_source = ? and a.customer_ref = b.customer_ref and b.account_num = c.account_num and " +
				"c.end_dat is null and a.customer_ref = d.customer_ref and d.event_type_id = ? and d.end_dtm is null";
		String minQuery = "select a.customer_ref, a.event_source as min, b.account_num, c.bill_handling_code, " +		
				"d.event_source as mdn from pvcusteventsource4 a, pvaccount11 b, pvaccountdetails4 c, " +
				"pvcusteventsource4 d where a.end_dtm is null and a.event_type_id = ? and " +
				"a.event_source = ? and a.customer_ref = b.customer_ref and b.account_num = c.account_num and " +
				"c.end_dat is null and a.customer_ref = d.customer_ref and d.event_type_id = ? and d.end_dtm is null";
				
		// PrePay RBT Change
		//String checkPrepay = "select attribute_value from custproductattrdetails where product_id=" + dgtID +
		//		" and product_attribute_subid=" + subID + " and customer_ref = ?";
				
		String checkPrepay = "select /*+ INDEX(CUSTPRODUCTATTRDETAILS CUSTPRODUCTATTRDETAILS_PK) */ count(*) from custproductattrdetails where product_id=" + dgtID +
						" and product_attribute_subid=" + subID + " and customer_ref = ? and attribute_value='Y' " +
						"and start_dat<sysdate and (end_dat is null or end_dat>sysdate)";
		
		
		try {
			ic = new InitialContext();
			ds = (DataSource) ic.lookup(dsNameG);
			conn = ds.getConnection();
//			String mdnEventType = SPProps.getProperty("MDN_EVENT_TYPE");
//			String minEventType = SPProps.getProperty("MIN_EVENT_TYPE");
//			if (mdnEventType == null || mdnEventType.equals(""))
//				mdnEventType = "8";
//			if (minEventType == null || minEventType.equals(""))
//				minEventType = "9";
//			StringBuffer sb = new StringBuffer();
//			sb.append("select a.customer_ref, a.event_source as ");
//			if (selParam == MDN)
//				sb.append("mdn,");
//			else
//				sb.append("min,");
//			sb.append(
//				"b.account_num, c.bill_handling_code, d.event_source as ");
//			if (selParam == MDN)
//				sb.append("min");
//			else
//				sb.append("mdn");
//			sb.append(" from pvcusteventsource4 a, pvaccount11 b,");
//			sb.append("pvaccountdetails4 c, pvcusteventsource4 d where ");
//			sb.append("a.end_dtm is null and ");
//			sb.append("a.event_type_id = '");
//			if (selParam == MDN)
//				sb.append(mdnEventType);
//			else
//				sb.append(minEventType);
//			sb.append("' and ");
//			sb.append("a.event_source = '" + number + "' and ");
//			sb.append("a.customer_ref = b.customer_ref and ");
//			sb.append("b.account_num = c.account_num and ");
//			sb.append("c.end_dat is null and ");
//			sb.append("a.customer_ref = d.customer_ref and ");
//			sb.append("d.event_type_id = '");
//			if (selParam == MDN)
//				sb.append(minEventType);
//			else
//				sb.append(mdnEventType);
//			sb.append("' and d.end_dtm is null");

			String query = null;
			if (selParam == MDN)
			    query = mdnQuery;
			else
			    query = minQuery;
			L.debug(
				"getAccountFromGeneva():"
					+ logTag
					+ "SQL Statement is: "
					+ query);

			pstmt = conn.prepareStatement(query);
			if (selParam == MDN)
			    pstmt.setInt(1,SPProps.mdnEventTypeId);
			else
			    pstmt.setInt(1,SPProps.minEventTypeId);
			pstmt.setString(2,number);
			if (selParam == MDN)
			    pstmt.setInt(3,SPProps.minEventTypeId);
			else
			    pstmt.setInt(3,SPProps.mdnEventTypeId);

			rset = pstmt.executeQuery();

			if (rset.next()) {
				String cust_ref = rset.getString(1);
				acctVO.cust_ref = cust_ref;

				if (selParam == MDN)
					acctVO.mdn = rset.getString(2);
				else
					acctVO.min = rset.getString(2);

				acctVO.unid = rset.getString(3);
				acctVO.billSysId = rset.getString(4);			
				if (selParam == MDN)
					acctVO.min = rset.getString(5);				
				else
					acctVO.mdn = rset.getString(5);

				acctVO.esn = "";
				acctVO.marketId = "";
				
				pstmt = conn.prepareStatement(checkPrepay);
				pstmt.setString(1, cust_ref);
				ResultSet rs = pstmt.executeQuery();
				if(rs.next()) {
					if(rs.getInt(1)>0)
						acctVO.prepay_ind = "Y";
					else
						acctVO.prepay_ind = "N";
				}
				
				L.debug(
						"getAccountFromGeneva():"
							+ logTag
							+ "Customer Ref="
							+ cust_ref
							+ ";MDN = "
							+ acctVO.mdn
							+ ";MIN = "
							+ acctVO.min
							+ ";PREPAY_IND = "
							+ acctVO.prepay_ind);				// PrePay RBT Change

				retVal = true;
			}

		} catch (Exception e) {
			L.error(
				"getAccountFromGeneva():"
					+ logTag
					+ "During getAccountFromGeneva(): ",
				e);
			throw new ProcessingException(
				ProcessingException.GLOBAL,
				e.getMessage());
		} finally {
			try {
				if (rset != null)
					rset.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception eat) {
			}
		}
		return retVal;
	}
	public static String getServiceStatusFromGeneva(
		String logTag,
		String custRef,
		String billTariffId)
		throws ProcessingException {
		L.debug(
			"getServiceStatusFromGeneva():"
				+ logTag
				+ "Entering with CustRef="
				+ custRef
				+ "; Billing_Tariff_Id="
				+ billTariffId);
		InitialContext ic = null;
		DataSource ds = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String servStatus = null;
		String query = "SELECT /*+ INDEX(pvcustproducttariffdetails  CUSTPRODUCTTARIFFDETAILS_PK) */ count(*) " +
				"FROM pvcustproducttariffdetails  WHERE customer_ref = ? and tariff_id = ? and end_dat is null";
		try {
			ic = new InitialContext();
			ds = (DataSource) ic.lookup(dsNameG);
			conn = ds.getConnection();
//			StringBuffer sb = new StringBuffer();
//			sb.append(
//				"SELECT /*+ INDEX(pvcustproducttariffdetails  CUSTPRODUCTTARIFFDETAILS_PK) */ " +
//				"count(*) FROM pvcustproducttariffdetails  WHERE customer_ref='");
//			sb.append(custRef);
//			sb.append("' and tariff_id='");
//			sb.append(billTariffId);
//			sb.append("' and end_dat is null");
			L.debug(
				"getServiceStatusFromGeneva():"
					+ logTag
					+ "SQL Statement is: "
					+ query);

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, custRef);
			pstmt.setString(2, billTariffId);
			rset = pstmt.executeQuery();
			int rowCount = 0;
			if (rset.next()) {
				rowCount = rset.getInt(1);
				L.debug(
					"getServiceStatusFromGeneva():"
						+ logTag
						+ "Row Count from pvcustproducttariffdetails="
						+ rowCount);
			} else
				L.debug(
					"getServiceStatusFromGeneva():"
						+ logTag
						+ "Customer Service  is not active, no row found");
			if(rowCount > 0)
				servStatus = "OK";
				
		} catch (Exception e) {
			L.error(
				"getAccountFromGeneva():"
					+ logTag
					+ "During getAccountFromGeneva(): ",
				e);
			throw new ProcessingException(
				ProcessingException.GLOBAL,
				e.getMessage());
		} finally {
			try {
				if (rset != null)
					rset.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception eat) {
			}
		}
		return servStatus;
	}

	public static String chkProvAllowed(String provId, String billSysId) {
		L.info(
			"Entered into CheckAccount.chkProvAllowed(),ProvId="
				+ provId
				+ " ,billSysId="
				+ billSysId);
		String errMsg =
			SPProps.getBillProvPropObject().getProperty(
				provId.trim() + "-" + billSysId.trim());
		if (errMsg == null || errMsg.trim().equals(""))
			return null;
		else
			return errMsg.trim();
	}

}
