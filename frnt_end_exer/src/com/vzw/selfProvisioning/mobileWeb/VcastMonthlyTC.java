package com.vzw.selfProvisioning.mobileWeb;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.vzw.edr.selfProv.utils.SPProps;
import com.vzw.selfProvisioning.ActionInterface;
import com.vzw.selfProvisioning.RequestResponse;

/**
 * @author caoeric
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class VcastMonthlyTC extends RequestResponse implements ActionInterface {

	private static Logger L = Logger.getLogger(SPProps.getFELogName(VcastMonthlyTC.class));

	/**
	 * @see com.vzw.selfProvisioning.ActionInterface#doAction(HttpServletRequest, HttpServletResponse)
	 */
	public String doAction(HttpServletRequest req, HttpServletResponse resp)
		throws Exception {
		set_req(req);
		
		//get the min from the http session
		HttpSession session = get_session();
		String _mdn = (String) session.getAttribute("mdn");
		
		if (_mdn == null || _mdn.trim().equals(""))
		{
			L.error("doAction, mdn is null. Can't continue");
			ActivationFailed af = new ActivationFailed();
			af.setErrorCode("999");
			req.setAttribute("statusBean", af);
			return SPProps.getProperty("errorPage");
		}

		String logTag = (String) get_session().getAttribute("logtag");
		if (logTag == null || logTag.trim().equals(""))
		{
			String eventId = (String) get_session().getAttribute("eventid");
			if (eventId == null || eventId.trim().equals(""))
			{
				Date now = new Date();
				eventId = "" + now.getTime();
				get_session().setAttribute("eventid", eventId);
			}
			logTag = " [" + _mdn + "-" + eventId + "] ";
			get_session().setAttribute("logtag", logTag);
		}

		L.info("doAction():" + logTag +
			"User has entered the VCAST monthly T&C page, with the following session: "+get_session().getId());
		SPProps.infoLogger.info("VcastTC.doAction():" + logTag +
			"User has entered the VCAST monthly T&C page, with the following session: "+get_session().getId());

		
		return "mobileWeb/monthlyVODTandC.jsp";
			
	}


}
