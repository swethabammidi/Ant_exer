package com.vzw.edr.selfProv.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;

public class BrowserVersion {
	
	// Must be the same as in Util.java of com.vzw.selfProvisioning.utils Package.
	public final static int unKnowClient = -1;
	
	public static int getWapVersion(String ua){
		if (ua == null || ua.trim().equals(""))
			return unKnowClient;

		Iterator it = SPProps.getBrowserMap().keySet().iterator();
		ArrayList bList = null;
		String brName = null;
		while (it.hasNext())
		{
			String bName = (String)it.next();
			if (ua.toLowerCase().startsWith(bName.toLowerCase()) || ua.indexOf(bName) != -1)
			{
				bList = (ArrayList)SPProps.getBrowserMap().get(bName);
				brName = new String(bName);
				break;
			}
		}
		if (brName == null || brName.trim().equals(""))
			return unKnowClient; 
		if (bList != null && bList.size() > 0)
		{
			for (int i = 0; i < bList.size(); i++)
			{
				BrowserType bType = (BrowserType)bList.get(i);
				if (!bType.isVersionCheckRequired())
				{
					return bType.getBrowserType(-1);
				}
				else
				{
					int aVer = bType.getBrowserType(getVersionFromUA(ua, brName));
					if (aVer != -1)
					{
						return aVer;
					}
				}
			}
		}
		return unKnowClient;
	}
	
	private static int getVersionFromUA(String ua, String bName)
	{
		int ver = -1;
		int idx = 0;
		if (!ua.toLowerCase().startsWith(bName.toLowerCase()))
		{
			idx = ua.indexOf(bName);
			if (idx == -1)
				return -1;
		}
		if (ua.length() > idx + bName.length())
		{
			String nStr = ua.substring(idx + bName.length());
			idx = nStr.indexOf("/");
			if (idx == -1 || nStr.length() == idx + 1)
				return -1;
			nStr = nStr.substring(idx + 1);
			StringTokenizer tok = new StringTokenizer(nStr, ".");
			if (tok.hasMoreTokens()) {
				try {
					ver = (new Integer(tok.nextToken())).intValue();
				}
				catch (NumberFormatException e) {
					return -1;
				}
			}
		}
		return ver;
	}
}

