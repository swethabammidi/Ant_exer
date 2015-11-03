package com.vzw.edr.selfProv.utils;

public class BrowserType {
	private String brName;
	private int    brType;
	private int    hiVer;
	private int    lowVer;
	private boolean checkVer;
	
	public BrowserType(String name, int btype, int hi, int low){
		brName = name;
		brType = btype;
		hiVer  = hi;
		lowVer = low;
		if (hiVer == -1 && lowVer == -1)
			checkVer = false;
		else
			checkVer = true;
	}
	
	
	public int getBrowserType(int ver){
//		System.err.println ("name = " + brName + "; btype = " + brType + "; hi = " + hiVer + "; low = " + lowVer + "; ver = " + ver);
		if (!checkVer)
			return brType;
		if (ver == -1 && !checkVer)
			return brType;
		if (ver == -1 && checkVer)
			return -1;
		if (hiVer != -1 && lowVer == -1 && ver <= hiVer){
			return brType;
		}
		if (hiVer == -1 && lowVer != -1 && ver >= lowVer){
			return brType;
		}
		if (ver >= lowVer && ver <= hiVer){
			return brType;
		}
		return -1;
	}
	
	
	public boolean isVersionCheckRequired(){
		return checkVer;
	}

}
