package com.vzw.edr.selfProv.utils;

import org.apache.log4j.Logger;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import javax.sql.DataSource;

import javax.naming.InitialContext;
import javax.naming.NamingException;


/**
 * @author Anil
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class SPDBUtility {
	private static Logger L =
		Logger.getLogger(SPProps.getBELogName(SPDBUtility.class));
	//base class extended by two child classes
	private static final String dsNameG = "java:comp/env/jdbc/SelfProvDSGeneva";
	protected String errorMessage = "";
	protected String logTag = null;
	protected java.sql.Connection conn = null;
	private InitialContext context = null;
	private javax.sql.DataSource ds = null;

	/**
	 * Constructor for SelfProv
	 * @param 
	 */
	public SPDBUtility(String logTag) {
		this.logTag = logTag;
	}

	public void insertHistory(SelfProvDTO selfProvDTO)
		throws SPFeatureException {

		PreparedStatement ps = null;

		try {
			conn = getConnection();
			ps =
				conn.prepareStatement(
					"INSERT INTO V_POP_PROVISIONING_HISTORY(POP_ID, UNID, MDN, V_MIN, ADD_FEATURE_CODE, DELETE_FEATURE_CODE, VENDOR_ID, EVENT_ID, EDR_EVENT_ID, STATUS, CHANNEL_ID,ADD_CONTENT_ID,IS_DEFAULT,DELETE_CONTENT_ID,EXTRACT_STATUS) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			ps.setString(1, selfProvDTO.getPopId());
			L.debug(
				"insertHistory():"
					+ logTag
					+ "1: popId = "
					+ selfProvDTO.getPopId());
			ps.setLong(2, selfProvDTO.getUnid());
			L.debug(
				"insertHistory():"
					+ logTag
					+ "2: unid = "
					+ selfProvDTO.getUnid());
			ps.setLong(3, selfProvDTO.getMdn());
			L.debug(
				"insertHistory():"
					+ logTag
					+ "3: Mdn = "
					+ selfProvDTO.getMdn());
			ps.setLong(4, selfProvDTO.getMin());
			L.debug(
				"insertHistory():"
					+ logTag
					+ "4: Min = "
					+ selfProvDTO.getMin());
					
			String addFeatureCode = selfProvDTO.getAddFeatureCode();
			if (addFeatureCode != null)
				ps.setString(5, selfProvDTO.getAddFeatureCode());
			else
				ps.setNull(5, Types.NULL);
			L.debug(
				"insertHistory():"
					+ logTag
					+ "5: getAddFeatureCode = "
					+ selfProvDTO.getAddFeatureCode());
			
			String delFeatureCode = selfProvDTO.getDeleteFeatureCode();
			if (delFeatureCode != null)
				ps.setString(6, selfProvDTO.getDeleteFeatureCode());
			else
				ps.setString(6, "");
			L.debug(
				"insertHistory():"
					+ logTag
					+ "6: getDeleteFeatureCode = "
					+ selfProvDTO.getDeleteFeatureCode());	
			
			ps.setLong(7, selfProvDTO.getVendorId());
			L.debug(
				"insertHistory():"
					+ logTag
					+ "7: vendorId = "
					+ selfProvDTO.getVendorId());
			ps.setString(8, selfProvDTO.getEventId());
			L.debug(
				"insertHistory():"
					+ logTag
					+ "8: eventId = "
					+ selfProvDTO.getEventId());
			ps.setLong(9, selfProvDTO.getEdrEventId());
			L.debug(
				"insertHistory():"
					+ logTag
					+ "9: edrEventId = "
					+ selfProvDTO.getEdrEventId());
			String status = selfProvDTO.getStatus();
			if (status != null)
				ps.setString(10, status);
			else
				ps.setNull(10, Types.NULL);
			L.debug("insertHistory():" + logTag + "10: status = " + status);
			ps.setLong(11, selfProvDTO.getChannelId());
			L.debug(
				"insertHistory():"
					+ logTag
					+ "11: channelId = "
					+ selfProvDTO.getChannelId());
			String addContent = selfProvDTO.getAddContentIds();
			if ((addContent == null) || addContent.trim().equals(""))
				ps.setString(12, "");
			else
				ps.setString(12, addContent);

			L.debug(
				"insertHistory():"
					+ logTag
					+ "12: addContentIds = "
					+ addContent);
			String defaults = selfProvDTO.getDefaults();
			if ((defaults == null) || defaults.trim().equals(""))
				ps.setString(13, "");
			else
				ps.setString(13, defaults);
			L.debug("insertHistory():" + logTag + "13: defaults = " + defaults);
			String delContent = selfProvDTO.getDeleteContentIds();
			if ((delContent == null) || delContent.trim().equals(""))
				ps.setString(14, "");
			else
				ps.setString(14, delContent);

			L.debug(
				"insertHistory():"
					+ logTag
					+ "14: deleteContentIds = "
					+ delContent);
			// this field is for Data miniing people, so no need to put in selfProvDTO
			ps.setString(15, "");
			L.debug("insertHistory():" + logTag + "15: extStatus = " + null);

			ps.executeUpdate();
		} catch (SQLException e) {
			L.error(
				"insertHistory():"
					+ logTag
					+ "S1020: Insert History Failed for EDR_EVENT_ID = "
					+ selfProvDTO.getEdrEventId(),
				e);
			throw new SPFeatureException(
				"S1020",
				"Insert History Failed for EDR_EVENT_ID = "
					+ selfProvDTO.getEdrEventId()
					+ " "
					+ e.getMessage());
		} catch (MobileWebException e) {
			L.error(
				"insertHistory():"
					+ logTag
					+ "S1030: Insert History Failed for EDR_EVENT_ID = "
					+ selfProvDTO.getEdrEventId(),
				e);
			throw new SPFeatureException(
				"S1030",
				"Insert History Failed for EDR_EVENT_ID = "
					+ selfProvDTO.getEdrEventId()
					+ " "
					+ e.getMessage());
			//throw new MobileWebException(resultCode, errorMessage + " " + e.getLocalizedMessage());
		} finally {
			closePrepStatement(ps);
			closeConn(conn);
		}
	}

	/**
	* getConnection
	*/
	protected Connection getConnection() throws MobileWebException {
		if (conn != null) {
			return conn;
		}

		try {
			context = new InitialContext();
			DataSource ds = null;
			ds = (DataSource) context.lookup(dsNameG);
			if (ds == null) {
				L.error(
					"getConnection():"
						+ logTag
						+ "S1190: Unable to get connection. ds = null");
				throw new MobileWebException(
					"S1190",
					"Unable to get connection. ds = null");
			} else {
				conn = ds.getConnection();

				if (conn == null) {
					L.error(
						"getConnection():"
							+ logTag
							+ "S1190: Unable to get connection. conn = null");
					throw new MobileWebException(
						"S1190",
						"Unable to get connection.");
				}
			}
		} catch (NamingException e) {
			L.error(
				"getConnection():"
					+ logTag
					+ "S1080: Unable to get DB Connection ",
				e);
			throw new MobileWebException(
				"S1080",
				"Unable to get DB Connection, Exception: " + e.getMessage());
		} catch (MobileWebException m) {
			throw new MobileWebException(m.getErrorCode(), m.getErrorMessage());
		} catch (SQLException e) {
			L.error(
				"getConnection():"
					+ logTag
					+ "S1090: Unable to get DB Connection ",
				e);
			throw new MobileWebException(
				"S1090",
				"Unable to get DB Connection, Exception: " + e.getMessage());
		}
		return conn;
	}

	/**
	* closeConn
	*/
	private void closeConn(Connection conn) {
		try {
			if (conn != null) {
				conn.close();
				conn = null;
			}
		} catch (Exception e) {
			L.error("closeConn():" + logTag + ":S1100", e);
		}
	}

	/**
	* closePrepStatement
	*/
	private void closePrepStatement(PreparedStatement ps) {
		try {
			if (ps != null) {
				ps.close();
				ps = null;
			}
		} catch (Exception e) {
			L.error("closeConn():" + logTag + ":S1110", e);
		}
	}

	/**
	* closeResultSet
	*/
	private void closeResultSet(ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
				rs = null;
			}
		} catch (Exception e) {
			L.error("closeConn():" + logTag + ":S1120", e);
		}
	}
	
	
	public long getSeqId() throws ProcessingException {
		PreparedStatement ps = null;
		ResultSet rset = null;
		String seqStr = "select V_POP_DISPATCH_QUEUE_SEQ.nextval from dual";
		long seqId = 0;

		try {
			conn = getConnection();

			// select the next sequence from V_POP_DISPATCH_SEQUENCE 
			// for now use V_VENDOR_DISPATCH_QUEUE_SEQ
			// return this sequence after successful insert.

			ps = conn.prepareStatement(seqStr);
			rset = ps.executeQuery();
			if (rset.next()) {
				seqId = rset.getLong(1);
			} else {
				throw new ProcessingException(
					ProcessingException.GLOBAL,
					"Error during getting sequence id.");
			}
		} catch (Exception e) {
			throw new ProcessingException(
				ProcessingException.GLOBAL,
				"Error during getting sequence id.");
		} finally {
			closeResultSet(rset);
			closePrepStatement(ps);
			closeConn(conn);
		}
		return seqId;
	}
	
	public String getStatusActiveSuspendedCanceled(String minMdn, String minMdnType) throws ProcessingException {
		String status = "";
		PreparedStatement ps = null;
		ResultSet rset = null;


		String mdnQuery = "select account_status from pvaccountstatus a , pvaccount11 b, custeventsource c "
						+ "where a.account_num=b.account_num "
						+ "and b.customer_ref=c.customer_ref "
						+ "and event_source='" + minMdn + "' "
						+ "and event_type_id='8' "
						+ "order by effective_dtm desc";
						
		String minQuery = "select account_status from pvaccountstatus a , pvaccount11 b, custeventsource c "
						+ "where a.account_num=b.account_num "
						+ "and b.customer_ref=c.customer_ref "
						+ "and event_source='" + minMdn + "' "
						+ "and event_type_id='9' "
						+ "order by effective_dtm desc";		
		/*
		String mdnQuery = "select account_status from pvaccountstatus a , pvaccount11 b, pvcusteventsource c "
						+ "where a.account_num=b.account_num "
						+ "and b.customer_ref=c.customer_ref "
						+ "and event_source='" + minMdn + "' "
						+ "and event_type_id='8' "
						+ "order by effective_dtm desc";
						
		String minQuery = "select account_status from pvaccountstatus a , pvaccount11 b, pvcusteventsource c "
						+ "where a.account_num=b.account_num "
						+ "and b.customer_ref=c.customer_ref "
						+ "and event_source='" + minMdn + "' "
						+ "and event_type_id='9' "
						+ "order by effective_dtm desc";	
						
						
		String mdnQuery = "select ACCOUNT_STATUS from pvaccountstatus where account_num in(" 
					  + "select account_num from pvaccount11 where customer_ref in("
					  + "select customer_ref from pvcusteventsource where event_source='" + minMdn + "' and event_type_id='8')) "
					  + "order by effective_dtm desc";
					  
		
		String minQuery = "select ACCOUNT_STATUS from pvaccountstatus where account_num in(" 
						+ "select account_num from pvaccount11 where customer_ref in("
						+ "select customer_ref from pvcusteventsource where event_source='" + minMdn + "' and event_type_id='9')) "
						+ "order by effective_dtm desc";
		
		
		SString mdnQuery = "select ACCOUNT_STATUS from pvaccountstatus where account_num in(" 
			  + "select account_num from pvaccount11 where customer_ref in("
			  + "select customer_ref from pvcusteventsource where event_source='555877444745' and event_type_id='8')) "
			  + "order by effective_dtm desc";
					  
		
		String minQuery = "select ACCOUNT_STATUS from pvaccountstatus where account_num in(" 
				+ "select account_num from pvaccount11 where customer_ref in("
				+ "select customer_ref from pvcusteventsource where event_source='555877444745' and event_type_id='9')) "
				+ "order by effective_dtm desc";
		*/
		
		try {
			conn = getConnection();

			String query = null;
			if (minMdnType == "MDN")
				query = mdnQuery;
			else
				query = minQuery;
			L.debug("getStatusActiveSuspendedCanceled():" + logTag + "SQL Statement is: " + query);

			ps = conn.prepareStatement(query);
			rset = ps.executeQuery();
			if (rset.next()) {
				status = rset.getString(1);
			} else {
				throw new ProcessingException(ProcessingException.GLOBAL, "Error during getting ACCOUNT_STATUS.");
			}
		} catch (Exception e) {
			throw new ProcessingException(ProcessingException.GLOBAL, "Error during getting ACCOUNT_STATUS.");
		} finally {
			closeResultSet(rset);
			closePrepStatement(ps);
			closeConn(conn);
		}
		return status;
	}

}
