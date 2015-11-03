package com.vzw.selfProvisioning.utils;

import java.util.StringTokenizer;
import java.util.HashMap;
import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.vzw.edr.selfProv.utils.SPProps;
import com.vzw.edr.selfProv.utils.BrowserType;
import com.vzw.edr.selfProv.utils.BrowserVersion;

/**
 * @author Roy Kachouh
 * @creation date: Jul 25, 2003
 * File name: Util.java
 */
public final class Util {
	private static Logger L = Logger.getLogger(SPProps.getFELogName(Util.class));
	public final static  int  WAP1X = 1;
	public final static  int  WAP2X = 2;
	public final static  int  CSP = 1;
	public final static  int  WSP = 2;
	public final static  int  VCAST = 3;
	public final static  int  PPUS = 4;

	public final static  String  CSP_SERVICE = "CSP";
	public final static  String  WSP_SERVICE = "WSP";
	
	public final static  int  unKnowClient = -1;


	public static int getBrowserType(HttpServletRequest req) {
			String value = null;
			value = req.getHeader("user-agent");
			int ret = getBrowserType(value);
			L.debug ("getBrowserType() : req.getHeader(user-agent): " + value + " ;BTYPE: " + ret);
			return (ret);
	}

	/*
	public static int getBrowserType(String str) {
	int ver = BrowserVersion.getWapVersion(str);
	//	System.err.println ("UA: " + str + "; Version = " + ver);
	
	if (ver != WAP2X)
	{
		return unKnowClient;
	}
	else
		return ver;
	}
	*/
	
	public static int getBrowserType(String str) {
	// Testing ONLY!!!		
	//str = "VZW Media Player (Version: 3.02.001)";
	//System.err.println ("UA: str = " + str);
		
	if (str == null || str.trim().equals(""))
		return unKnowClient;
	if (str != null && str.toLowerCase().startsWith("vzw media player"))
		return VCAST;
	//if(str != null && str.toLowerCase().startsWith("mozilla"))
	//	return WAP2X;
		
	//int idx = str.indexOf("UP.Browser");
	//if (idx == -1){
	//	return unKnowClient;
	//}
		
	int ver = BrowserVersion.getWapVersion(str);
//	System.out.println ("Version = " + ver);
				
	if (ver != WAP2X && ver != WAP1X){
		return unKnowClient;
	}
	else
		return ver;
	}
	
	/*
	public static int getBrowserType (String str){
	
		if (str == null || str.trim().equals(""))
			return unKnowClient;
		if(str != null && str.toLowerCase().startsWith("vzw media player"))
			return VCAST;
		if(str != null && str.toLowerCase().startsWith("mozilla"))
			return WAP2X;
		int idx = str.indexOf("UP.Browser");
		if (idx == -1)
		{
			return unKnowClient;
		}
		if (str.length() > (idx + "UP.Browser".length()))
		{
			String nStr = str.substring(idx+"UP.Browser".length());
			idx = nStr.indexOf("/");
			if (idx == -1 || nStr.length() == idx + 1)
			{
				return unKnowClient;
			}
			else
			{
				nStr = nStr.substring(idx+1);
				StringTokenizer tok = new StringTokenizer(nStr, ".");
				if (tok.hasMoreTokens())
				{
					int ver = 0;
					try
					{
						ver = new Integer(tok.nextToken()).intValue();
					}
					catch (NumberFormatException e)
					{
						return unKnowClient;
					}
					if (ver == 4 || ver == 5)
						return WAP1X;
					else if (ver > 5)
						return WAP2X;
					else
						return unKnowClient;
				}
				return WAP2X;
			}
		}
		else
		{
			return unKnowClient;
		}
	}
	*/
	
	
	
	
	
		

	public static int getService(HttpServletRequest req) {
	String value = null;
	int ret = -1;
	try{
		HttpSession sess = req.getSession(false);
		value = (String)sess.getAttribute("service");
		L.debug ("getService :SERVICE:" + value);
		if(value != null){
			if(value.equalsIgnoreCase(WSP_SERVICE))
					ret = WSP;
			else if(value.equalsIgnoreCase(CSP_SERVICE))
					ret = CSP;
			else if(value.equalsIgnoreCase(SPProps.vzwServiceVCAST))
					ret = VCAST;
			else if(value.equalsIgnoreCase(SPProps.vzwServicePPUS))
					ret = PPUS;
		}
	}catch(Exception e){
			L.error("Error in getting service from seesion" + e.getMessage());	
	}
			
	return (ret);
	}
	
	
	
	/*
	public static int getBrowserType (String str){
	
		if (str == null || str.trim().equals(""))
			return unKnowClient;
		if(str != null && str.toLowerCase().startsWith("vzw media player"))
			return VCAST;
		if(str != null && str.toLowerCase().startsWith("mozilla"))
			return WAP2X;
		int idx = str.indexOf("UP.Browser");
		if (idx == -1)
		{
			return unKnowClient;
		}
		if (str.length() > (idx + "UP.Browser".length()))
		{
			String nStr = str.substring(idx+"UP.Browser".length());
			idx = nStr.indexOf("/");
			if (idx == -1 || nStr.length() == idx + 1)
			{
				return unKnowClient;
			}
			else
			{
				nStr = nStr.substring(idx+1);
				StringTokenizer tok = new StringTokenizer(nStr, ".");
				if (tok.hasMoreTokens())
				{
					int ver = 0;
					try
					{
						ver = new Integer(tok.nextToken()).intValue();
					}
					catch (NumberFormatException e)
					{
						return unKnowClient;
					}
					if (ver == 4 || ver == 5)
						return WAP1X;
					else if (ver > 5)
						return WAP2X;
					else
						return unKnowClient;
				}
				return WAP2X;
			}
		}
		else
		{
			return unKnowClient;
		}
	}
	*/


	public static HashMap getDisplayMap(String uiStr, String logTag)
	{
		L.debug(logTag + "getDisplayMap():uiStr = " + uiStr);
		String tuiStr = uiStr.trim();
		StringTokenizer tok = new StringTokenizer(uiStr, " ");
		HashMap hmDP = new HashMap();
		String dispSize = "D";
		try
		{
			if(tok.hasMoreTokens())
			{
				String ua = tok.nextToken("/");
				Enumeration en = SPProps.getUIDBPropObject().propertyNames();				
				while (en.hasMoreElements())
				{
					String pName = (String)en.nextElement();
					if (ua.toUpperCase().matches(pName))
					{
						dispSize = SPProps.getUIDBPropObject().getProperty(pName);
						L.debug(logTag + "getDisplayMap():Matched Device Pattern: " + pName);
						break;
					}
				}
//				dispSize = SPProps.getUIDBPropObject().getProperty(ua.toUpperCase());
				L.debug("Display size from Properties:" + dispSize);
				if(dispSize == null || dispSize.trim().equals(""))
					dispSize = "D";
			}
		}
		catch(Exception e)
		{
			L.error(logTag + "Exception = ", e);
		}
		String mainImg = SPProps.getUIDBPropObject().getProperty("VZW_SERVICES_" + dispSize);
		String mwImg  = SPProps.getUIDBPropObject().getProperty("MOBILE_WEB_BUTTON_"  + dispSize);
		String mwDescImg  = SPProps.getUIDBPropObject().getProperty("MOBILE_WEB_DESC_"  + dispSize);
		String myAcctImg = SPProps.getUIDBPropObject().getProperty("MYACCT_BUTTON_" + dispSize);
		String myAcctDescImg = SPProps.getUIDBPropObject().getProperty("MYACCT_DESC_" + dispSize);
		if (mainImg == null || mainImg.trim().equals(""))
		{
			mainImg = SPProps.getUIDBPropObject().getProperty("VZW_SERVICES_D");
			if (mainImg == null || mainImg.trim().equals(""))
			{
				mainImg = "spw_services_s.gif";
			}
		}
		if (mwImg == null || mwImg.trim().equals(""))
		{
			mwImg = SPProps.getUIDBPropObject().getProperty("MOBILE_WEB_BUTTON_D");
			if (mwImg == null || mwImg.trim().equals(""))
			{
				mwImg = "services_mobileweb_button_s.gif";
			}
		}
		if (mwDescImg == null || mwDescImg.trim().equals(""))
		{
			mwDescImg = SPProps.getUIDBPropObject().getProperty("MOBILE_WEB_DESC_D");
			if (mwDescImg == null || mwDescImg.trim().equals(""))
			{
				mwDescImg = "services_mobileweb_descript_s.gif";
			}
		}
		if (myAcctImg == null || myAcctImg.trim().equals(""))
		{
			myAcctImg = SPProps.getUIDBPropObject().getProperty("MYACCT_BUTTON_D");
			if (myAcctImg == null || myAcctImg.trim().equals(""))
			{
				myAcctImg = "services_myaccount_button_s.gif";
			}
		}
		if (myAcctDescImg == null || myAcctDescImg.trim().equals(""))
		{
			myAcctDescImg = SPProps.getUIDBPropObject().getProperty("MYACCT_DESC_D");
			if (myAcctDescImg == null || myAcctDescImg.trim().equals(""))
			{
				myAcctDescImg = "services_myaccount_descript_s.gif";
			}
		}
		L.debug("Finally display size:" + dispSize);
		L.debug("mainImg:" + mainImg);
		L.debug("mwImg:" +   mwImg);
		L.debug("mwDescImg:" +  mwDescImg);
		L.debug("myAcctImg:" + myAcctImg);
		L.debug("myAcctDescImg:" +  myAcctDescImg);		
		
		hmDP.put("mainImg", mainImg);
		hmDP.put("mwImg",  mwImg);
		hmDP.put("mwDescImg",  mwDescImg);
		hmDP.put("myAcctImg", myAcctImg);
		hmDP.put("myAcctDescImg", myAcctDescImg);		
		return hmDP;
	}
	public static String getContextURL(HttpServletRequest req)
	{
		String urlPath = "";
		int port = req.getServerPort();
		if ((port == 80) || (port == 443))
			urlPath = "http://" + req.getServerName() + req.getContextPath() + "/";
		else
			urlPath = req.getScheme() + "://" + req.getServerName() + ":" + port + req.getContextPath() + "/";
		return 	urlPath;
	}		
	
	public static void main (String [] args) {
		String b = "OPWV-SDK/62 UP.Browser/6.2.2.1.208 (GUI) MMP/2.0";
		System.out.println(b + "----" + getBrowserType(b));
		b = "SCH-A790 UP.Browser/5.2.2.4 (GUI) MMP/2.0";
		System.out.println(b + "----" + getBrowserType(b));
		b = "LGE-VX7000/1.0 UP.Browser/6.2.3.1.174 (GUI) MMP/2.0";
		System.out.println(b + "----" + getBrowserType(b));
		String vod = "VZW Media Player (Version: 3.02.001)";
		System.out.println(vod + "----" + getBrowserType(vod));
	}
}

