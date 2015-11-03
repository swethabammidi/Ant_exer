package com.vzw.selfProvisioning.mobileWeb;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.vzw.edr.selfProv.utils.SPProps;
import com.vzw.selfProvisioning.ActionInterface;
import com.vzw.selfProvisioning.RequestResponse;

/**
 * @author kachoro
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class MoreInfo extends RequestResponse implements ActionInterface {
	private static Logger L = Logger.getLogger(SPProps.getFELogName(MoreInfo.class));

	/**
	 * @see com.vzw.selfProvisioning.ActionInterface#doAction(HttpServletRequest, HttpServletResponse)
	 */
	public String doAction(HttpServletRequest req, HttpServletResponse resp)
		throws Exception {
		set_req(req);
		showCookies();
		//get the min from the http session
		String _mdn = (String) get_session().getAttribute("mdn");
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
			"User has entered the More Info 2 page, with the following session: "+get_session().getId());
		SPProps.infoLogger.info("MoreInfo.doAction():" + logTag + 
			"User has entered the more info 2 page, with the following session: "+get_session().getId());
		
		return "mobileWeb/moreInfo2.jsp";
	}

}
