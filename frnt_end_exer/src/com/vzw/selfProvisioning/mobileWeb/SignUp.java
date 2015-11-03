package com.vzw.selfProvisioning.mobileWeb;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.vzw.edr.selfProv.utils.SPProps;
import com.vzw.selfProvisioning.utils.*;
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
public class SignUp extends RequestResponse implements ActionInterface {
	private static Logger L = Logger.getLogger(SPProps.getFELogName(SignUp.class));
	/**
	 * @see com.vzw.selfProvisioning.ActionInterface#doAction(HttpServletRequest, HttpServletResponse)
	 */
	public String doAction(HttpServletRequest req, HttpServletResponse resp)
		throws Exception
	{
		set_req(req);
		sniffHeader();
		HttpSession session = get_session();
		L.debug ("Adding min: "+getMdn()+" to the session with id: "+session.getId());
		String mdn = getMdn();
		String service = getService();
		if ((mdn == null) || mdn.trim().equals(""))
		{
			L.error("SignUp.doAction, mdn/service is null. Can't continue");
			ActivationFailed af = new ActivationFailed();
			af.setErrorCode("999");
			req.setAttribute("statusBean", af);
			return SPProps.getProperty("errorPage");
		}
		Date now = new Date();
		String eventId = "" + now.getTime();
		String logTag = " [" + mdn + "-" + eventId + "] ";
		session.setAttribute ("mdn", mdn);
		session.setAttribute ("service", service);
		session.setAttribute ("logtag", logTag);
		session.setAttribute ("eventid", eventId);

		L.info("doAction():" + logTag +
			"User has entered the signup page, using user agent " + getUserAgent() + ", with the following session: "+session.getId());
		SPProps.infoLogger.info("SignUp.doAction():" + logTag +
			"User has entered the signup page, using user agent " + getUserAgent() + ", with the following session: "+session.getId());
		int serv = Util.getService(req);
		if (serv == -1)
		{
			L.error(logTag + "Invalid Service. Service = " + service);
			ActivationFailed af = new ActivationFailed();
			af.setErrorCode("999");
			req.setAttribute("statusBean", af);
			return SPProps.getProperty("errorPage");
		}
		else if(Util.getService(req) == Util.WSP)	
			return "mobileWeb/landing.jsp";
		else if(Util.getService(req) == Util.VCAST)
			return "mobileWeb/signUpVOD.jsp";
		else if (Util.getService(req) == Util.PPUS)
		    return "mobileWeb/termsAndConditionsPPUS.jsp";
		else
		    return "mobileWeb/index.jsp";
	}

}

