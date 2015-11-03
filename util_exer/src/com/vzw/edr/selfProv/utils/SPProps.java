/*
 * Created on Aug 18, 2004
 */
package com.vzw.edr.selfProv.utils;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

public class SPProps{
	
    private static final Logger L          = Logger.getLogger("selfProv.infoLogger");
    public static final Logger  infoLogger = Logger.getLogger("selfProv.infoLogger");
 
    public static final String           feLoggerBase           = "selfProv.FE";
    public static final String           beLoggerBase           = "selfProv.BE";
    public static final String           transLoggerBase        = "selfProv.TA";
    public static final String           dsName                 = "java:comp/env/jdbc/SelfProvDSGeneva";
    private static Properties            props                  = null;

    /** Hold the date formatter */
    public final static SimpleDateFormat dateFormatter          = new SimpleDateFormat(
                                                                        "yyyy-MM-dd'T'HH:mm:ss");
    public static Date                   startDate              = null;
    public static String                 defaultVendorProp      = "defaultVendor";
    public static String                 defaultVendor          = null;
    public static String                 defaultChannelProp     = "defaultChannel";
    public static String                 defaultChannel         = null;
    public static String                 defaultServiceProp     = "defaultService";
    public static String                 defaultService         = null;
    public static String                 wap1xServiceProp       = "wap1xService";
    public static String                 wap1xService           = null;
    public static String                 vcastServiceProp       = "vcastService";
    public static String                 vcastService           = null;
    public static String                 ppusServiceProp        = "ppusService";
    public static String                 ppusService            = null;
	public static String                 v24ServiceProp       	= "v24Service";
	public static String 				 v24Service				= null;
	

    public static String                 vzwServiceVCASTProp    = "VZW_SERVICE_VCAST";
    public static String                 vzwServiceVCAST        = null;
    public static String                 vzwServicePPUSProp     = "VZW_SERVICE_PPUS";
    public static String                 vzwServicePPUS         = null;

    public static String                 backendServletProp     = "backendServlet";
    public static String                 backendServlet         = null;
    public static String                 testingModeProp        = "testingMode";
    public static String                 testingMode            = null;

    private static String                propertiesFileLocation = null;
    public static String                 minHeadProp1x          = "minHeader1x";
    public static String                 minHead1x              = null;
    public static String                 minHeadProp2x          = "minHeader2x";
    public static String                 minHead2x              = null;
    public static String                 service_head           = "serviceHeader";
    public static String                 servHeader             = null;
    public static String                 ipHeadProp             = "minHeader";
    public static String                 ipHead                 = null;

    public static String                 secureURLProp          = "secureUrl";
    public static String                 secureURL              = null;
    public static String                 clipLinkURLProp        = "clipLinkUrl";
    public static String                 clipLinkURL            = null;
    public static String                 selfServeURLProp       = "selfServeUrl";
    public static String                 selfServeURL           = null;
    public static String                 pixPlaceSURLProp       = "pixPlaceSuccessURL";
    public static String                 pixPlaceSURL           = null;
    public static String                 pixPlaceFURLProp       = "pixPlaceFailureURL";
    public static String                 pixPlaceFURL           = null;
    public static String                 pixPlaceDURLProp       = "pixPlaceDeclineURL";
    public static String                 pixPlaceDURL           = null;
    
    public static String                 showSelfServeProp      = "showSelfServe";
    public static String                 showSelfServe          = null;
    
    public static String                 printCookies           = null;
    public static String                 defaultPriceProp       = "defaultPrice";
    public static String                 defaultPrice           = null;
    public static String                 vcastPriceProp         = "VCAST_PRICE";
    public static String                 vcastPrice             = null;
	public static String                 v24PriceProp         	= "V24_PRICE";
	public static String                 v24Price             	= null;
    public static String                 ppusPriceProp          = "PPUS_PRICE";
    public static String                 ppusPrice              = null;

    public static String                 moreTCUrl              = null;
    public static String                 indexUrl               = null;
    public static String                 termsAndConditionsUrl  = null;
    public static String                 cancelUrl              = null;
    public static String                 confirmUrl             = null;
    public static String                 moreInfo1Url           = null;
    public static String                 moreInfo2Url           = null;
    public static String                 selfActivateUrl        = null;

    public static String                 custAgreementTCUrl     = null;
    public static String                 privacyTCUrl           = null;
    public static String                 chargesForServiceTCUrl = null;
    public static String                 billingTCUrl           = null;
    public static String                 monConsentTCUrl        = null;
    public static String                 useOfServicesTCUrl     = null;
    public static String                 premContentTCUrl       = null;
    public static String                 discWarrantiesTCUrl    = null;
    public static String                 limLiabilitiesTCUrl    = null;
    public static String                 copyrightTCUrl         = null;
    public static String                 trademarksTCUrl        = null;
    public static String                 elecTransactionsTCUrl  = null;
    public static String                 confidentialityTCUrl   = null;
    public static String                 entireTCUrl            = null;
    public static String                 pixPlaceRegTCUrl       = null;
    public static String                 picVideoMsgTCUrl       = null;
    
    public static String				 vcastDailyTCUrl		= null;
	public static String				 vcastMonthlyTCUrl		= null;
	public static String				 vcastMoreInfoUrl		= null;

    public static int                    mdnEventTypeId         = 0;
    public static int                    minEventTypeId         = 0;
    
    private static Properties            billProvProps          = null;
    private static Properties            actionHandlerProps     = null;
    private static Properties            vendorUrlProps         = null;
    private static Properties            vbsUrlProps            = null;
    private static Properties            uiDBProps              = null;
    private static Properties            sfoProps               = null;
    private static Properties			 prodAttrProps			= null;			// Attributes used to tell a Prepay|Postpay account
    private static String                propPath               = null;
    public static boolean                loaded                 = false;

	private static HashMap brMap;
    public SPProps()
    {
        try{
            props = new Properties();
            loadDBData();
            loaded = true;
            
            // URLs
            // VCAST Watch Video Link
            clipLinkURL = props.getProperty(clipLinkURLProp);
            if (clipLinkURL == null)
                clipLinkURL = "";
            // Secure URL
            secureURL = props.getProperty(secureURLProp);
            if (secureURL == null)
                secureURL = "";
                
//@:TODO delete the following line, for testing only
//secureURL = "http://10.177.202.75:9080/selfProvisioning/";
  
            // Self Serve URL
            selfServeURL = props.getProperty(selfServeURLProp);
            if (selfServeURL == null)
                selfServeURL = "";
            // Back To Pix Place in Decline Page
            pixPlaceDURL = props.getProperty(pixPlaceDURLProp);
            if (pixPlaceDURL == null)
                pixPlaceDURL = "http://www.vzwpix.com";
            // Back To Pix Place in Error Page
            pixPlaceFURL = props.getProperty(pixPlaceFURLProp);
            if (pixPlaceFURL == null)
                pixPlaceFURL = "http://www.vzwpix.com";
            // Back To Pix Place in Success Page
            pixPlaceSURL = props.getProperty(pixPlaceSURLProp);
            if (pixPlaceSURL == null)
                pixPlaceSURL = "http://www.vzwpix.com";
            
            showSelfServe = props.getProperty(showSelfServeProp);
            if (showSelfServe == null)
                showSelfServe = "true";
            printCookies = props.getProperty("printCookies");
            if (printCookies == null)
                printCookies = "false";
            defaultVendor = props.getProperty(defaultVendorProp);
            if (defaultVendor == null)
                defaultVendor = "999999";
            defaultChannel = props.getProperty(defaultChannelProp);
            if (defaultChannel == null)
                defaultChannel = "1";
            defaultService = props.getProperty(defaultServiceProp);
            if (defaultService == null)
                defaultService = "CD2";
            backendServlet = props.getProperty(backendServletProp);
            testingMode = props.getProperty(testingModeProp);
            if (testingMode == null)
                testingMode = "false";
            minHead1x = props.getProperty(minHeadProp1x);
            if (minHead1x == null)
                minHead1x = "x-up-calling-line-id";
            minHead2x = props.getProperty(minHeadProp2x);
            if (minHead2x == null)
                minHead2x = "VZW_MIN";

            //			added new for service
            servHeader = props.getProperty(service_head);
            if (servHeader == null)
                servHeader = "service";

            wap1xService = props.getProperty(wap1xServiceProp);
            if (wap1xService == null)
                wap1xService = "CSP";
            vcastService = props.getProperty(vcastServiceProp);
            if (vcastService == null)
                vcastService = "VCA";
			v24Service = props.getProperty(v24ServiceProp);
			if (v24Service == null)
				v24Service = "V24";
            ppusService = props.getProperty(ppusServiceProp);
            if (ppusService == null)
                ppusService = "PPUS";

            vzwServiceVCAST = props.getProperty(vzwServiceVCASTProp);
            if (vzwServiceVCAST == null)
                vzwServiceVCAST = "VCAST";
            vzwServicePPUS = props.getProperty(vzwServicePPUSProp);
            if (vzwServicePPUS == null)
                vzwServicePPUS = "PPUS";

            defaultPrice = props.getProperty(defaultPriceProp);
            if (defaultPrice == null)
                defaultPrice = "9.99";
            vcastPrice = props.getProperty(vcastPriceProp);
            if (vcastPrice == null)
                vcastPrice = "10.00";
			v24Price = props.getProperty(v24PriceProp);
			if (v24Price == null)
				v24Price = "3.00";
            ppusPrice = props.getProperty(ppusPriceProp);
            if (ppusPrice == null)
                ppusPrice = "1.99";

            termsAndConditionsUrl = SPProps.secureURL
                    + "SelfProvisioningServlet?actionClass=TermsAndConditions";
            indexUrl = SPProps.secureURL
                    + "SelfProvisioningServlet?actionClass=Index";
            cancelUrl = SPProps.secureURL
                    + "SelfProvisioningServlet?actionClass=Cancel";
            confirmUrl = SPProps.secureURL
                    + "SelfProvisioningServlet?actionClass=Confirm";
            moreInfo1Url = SPProps.secureURL
                    + "SelfProvisioningServlet?actionClass=MobileWebDesc";
            moreInfo2Url = SPProps.secureURL
                    + "SelfProvisioningServlet?actionClass=MoreInfo";
            selfActivateUrl = SPProps.secureURL
                    + "SelfProvisioningServlet?actionClass=SelfActivate";

            moreTCUrl = SPProps.secureURL
                    + "SelfProvisioningServlet?actionClass=TermsAndConditions";

            // Mobile Web 2.0 T&C Urls
            custAgreementTCUrl = SPProps.secureURL
                    + "SelfProvisioningServlet?actionClass=CustAgreementTC";
            privacyTCUrl = SPProps.secureURL
                    + "SelfProvisioningServlet?actionClass=PrivacyTC";
            chargesForServiceTCUrl = SPProps.secureURL
                    + "SelfProvisioningServlet?actionClass=ChargesForServiceTC";
            billingTCUrl = SPProps.secureURL
                    + "SelfProvisioningServlet?actionClass=BillingTC";
            monConsentTCUrl = SPProps.secureURL
                    + "SelfProvisioningServlet?actionClass=MonConsentTC";
            useOfServicesTCUrl = SPProps.secureURL
                    + "SelfProvisioningServlet?actionClass=UseOfServicesTC";
            premContentTCUrl = SPProps.secureURL
                    + "SelfProvisioningServlet?actionClass=PremContentTC";
            discWarrantiesTCUrl = SPProps.secureURL
                    + "SelfProvisioningServlet?actionClass=DiscWarrantiesTC";
            limLiabilitiesTCUrl = SPProps.secureURL
                    + "SelfProvisioningServlet?actionClass=LimLiabilitiesTC";
            copyrightTCUrl = SPProps.secureURL
                    + "SelfProvisioningServlet?actionClass=CopyrightTC";
            trademarksTCUrl = SPProps.secureURL
                    + "SelfProvisioningServlet?actionClass=TrademarksTC";
            elecTransactionsTCUrl = SPProps.secureURL
                    + "SelfProvisioningServlet?actionClass=ElecTransactionsTC";
            confidentialityTCUrl = SPProps.secureURL
                    + "SelfProvisioningServlet?actionClass=ConfidentialityTC";
            entireTCUrl = SPProps.secureURL
                    + "SelfProvisioningServlet?actionClass=EntireTC";
            pixPlaceRegTCUrl = SPProps.secureURL
            		+ "SelfProvisioningServlet?actionClass=PixPlaceRegTC";
            picVideoMsgTCUrl = SPProps.secureURL
            		+ "SelfProvisioningServlet?actionClass=PicVideoMsgTC";
            
            vcastDailyTCUrl = SPProps.secureURL
    				+ "SelfProvisioningServlet?actionClass=VcastDailyTC";
			vcastMonthlyTCUrl = SPProps.secureURL
					+ "SelfProvisioningServlet?actionClass=VcastMonthlyTC";
			vcastMoreInfoUrl = SPProps.secureURL 
					+ "SelfProvisioningServlet?actionClass=VcastMoreInfo";
    	    propPath = System.getProperty("PROPPATH");
            
            try{
                mdnEventTypeId = Integer.parseInt(props.getProperty("MDN_EVENT_TYPE"));
            }catch (Exception e){;}
            
            if (mdnEventTypeId == 0)
                mdnEventTypeId = 8;

            try{
                minEventTypeId = Integer.parseInt(props.getProperty("MIN_EVENT_TYPE"));
            }catch (Exception e){;}
            if (minEventTypeId == 0)
                minEventTypeId = 9;
            
            loadBillProv();
            loadActionHandlerProp();
            loadVBSProp();
            loadvendUrlProp();
            loadUiDBProp();
            loadSfoProps();
            loadProdAttrProps();
        }
        catch (Exception e){
            L.error("Exception while loading properties data. ", e);
        }
    }

    private void loadBillProv(){
        billProvProps = props;
    }

    private void loadActionHandlerProp(){
        actionHandlerProps = props;
    }

    private void loadVBSProp(){
        vbsUrlProps = props;
    }

    private void loadvendUrlProp(){
        vendorUrlProps = props;
    }

    private void loadUiDBProp(){
        uiDBProps = props;
    }
    
	private void loadSfoProps(){		
		sfoProps = props;
	}

    private void loadDBData(){
        InitialContext ic = null;
        DataSource ds = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try{
            ic = new InitialContext();
            ds = (DataSource)ic.lookup(dsName);
            conn = ds.getConnection();
            ps = conn.prepareStatement("SELECT PARAM_NAME, PARAM_VALUE FROM V_HANDSET_PROPS WHERE PARAM_APP_ID='SP'");
            rs = ps.executeQuery();
            while (rs.next()){
                String key = rs.getString(1);
                String value = rs.getString(2);
                props.setProperty(key, value);
            }
            
			ps = conn.prepareStatement("SELECT FEATURE_CODE, SFO_ID FROM V_VISION_FC_SFO_MAPPING");
			rs = ps.executeQuery();
			while (rs.next()){
				String fCode = rs.getString(1);
				String sfoId = "" + rs.getInt(2);
				props.setProperty(fCode, sfoId);
			}
			
			ps = conn.prepareStatement("SELECT PRODUCT_ID FROM PRODUCT where PRODUCT_NAME='DGT'");
			rs = ps.executeQuery();
			String key, val;
			if (rs.next()){
				key = "PROD_DGT";
				val = "" + rs.getInt(1);
				props.setProperty(key, val);
			
				ps = conn.prepareStatement("select product_attribute_subid from productattribute where product_id=" + val +
											" and attribute_bill_name='PREPAY_FLAG'");
				rs = ps.executeQuery();
				if (rs.next()){
					String subName = "SUB_PREPAY";
					String subID = "" + rs.getInt(1);
					props.setProperty(subName, subID);
				}
			}
			
			// Get host-depended data
			java.net.InetAddress in = java.net.InetAddress.getLocalHost();
			String hostname = in.getHostName().toUpperCase();
//			System.err.println("hostname: "+hostname);
			 	
			if (hostname != null && !hostname.trim().equals("")){
				ps = conn.prepareStatement("SELECT PARAM_NAME, PARAM_VALUE FROM V_HANDSET_PROPS WHERE PARAM_APP_ID=?");
				ps.setString(1,"SP_"+hostname);
				rs = ps.executeQuery();
				if(rs!=null){
					while(rs.next()){
						props.setProperty(rs.getString(1),rs.getString(2));		
					}
				}
			}
			
			brMap = getBrowserProperties(conn);
			
        }catch (Exception e){
            L.error ("Failed to load properties data from Database.", e);
        }
        finally{
            try{
                if (rs != null)   rs.close();
                if (ps != null)   ps.close();
                if (conn != null) conn.close();
            }catch (Exception e){;}
            L.info("SelfProvisioning Properties: " + props.toString());
        }
    }

	/**********************************
    public SPProps(Connection con)
    {
        // Load the properties from from database
        props = new Properties();
        loaded = true;

    }
	***********************************/
	
	
    public static String getProperty(String propName)
    {
        if (loaded)
            return props.getProperty(propName);
        else
            return null;
    }

    public static void setProperty(String propName, String propValue)
    {
        if (!loaded && props == null)
            props = new Properties();
        props.setProperty(propName, propValue);
    }

    public static String getFELogName(Class cls)
    {
        if (cls != null)
            return feLoggerBase + "." + cls.getName();
        else
            return feLoggerBase + "." + "unknownClass";
    }

    public static String getBELogName(Class cls)
    {
        if (cls != null)
            return beLoggerBase + "." + cls.getName();
        else
            return beLoggerBase + "." + "unknownClass";
    }

    public static String getTALogName(Class cls)
    {
        if (cls != null)
            return transLoggerBase + "." + cls.getName();
        else
            return transLoggerBase + "." + "unknownClass";
    }

    public static String getLogTag(HttpSession sess)
    {
        String logTag = null;
        if (sess != null)
            logTag = (String) sess.getAttribute("logTag");
        if (logTag == null || logTag.trim().equals(""))
        {
            String mdn = (String) sess.getAttribute("mdn");
            if (mdn == null)
            {
                mdn = "9999999999";
            }
            String eventId = (String) sess.getAttribute("eventId");
            if ((eventId == null) || eventId.equals(""))
                logTag = " [" + mdn + "-" + eventId + "] ";
            else
                logTag = " [" + mdn + "] ";
        }

        return logTag;
    }

    /**
     * Returns the Action.
     * @return String
     */
    public static Properties getPropObject()
    {
        return props;
    }

    /**
     * Returns the billProvProps.
     * @return Property
     */
    public static Properties getBillProvPropObject()
    {
        return billProvProps;
    }

    /**
     * Returns the actionHandlerProps.
     * @return Property
     */
    public static Properties getActionHandlerPropObject()
    {
        return actionHandlerProps;
    }

    /**
     * Returns the vbsUrlProps.
     * @return Property
     */
    public static Properties getVBSUrlPropObject()
    {
        return vbsUrlProps;
    }

    /**
     * Returns the vendorUrlProps.
     * @return Property
     */
    public static Properties getVendUrlPropObject()
    {
        return vendorUrlProps;
    }

    /**
     * Returns the uiDBProps.
     * @return Property
     */

    public static Properties getUIDBPropObject()
    {
        return uiDBProps;
    }
    /**
     * @return Returns the sfoProps.
     */
    public static Properties getSfoProps()
    {
        return sfoProps;
    }
    
    
	private void loadProdAttrProps()
	{
		prodAttrProps = props;
	}

    
	public static Properties getProdAttrProps() {
		return prodAttrProps;
	}
    
    
	public HashMap getBrowserProperties(Connection conn) throws SQLException, NamingException {
	L.debug("getBrowserProperties() : Started.");
	
	PreparedStatement ps = null;
	ResultSet rs = null;
	HashMap brTypes = new HashMap();		
	String sqlQuery="SELECT BROWSER_NAME, WAP_VERSION, NVL(HI_VERSION, -1), " +
					"NVL(LOW_VERSION, -1) FROM V_BROWSER_VERSION " +
					"ORDER BY BROWSER_NAME, WAP_VERSION DESC";
	try{		
		ps = conn.prepareStatement(sqlQuery);
		rs = ps.executeQuery();
		String lastName = null;
		ArrayList bList = null;
		while (	rs.next()){
				String bName = rs.getString(1); 
				if (lastName == null){
					lastName = new String(bName);
					bList = new ArrayList();
				}
				
				if (!lastName.equals(bName)){
					brTypes.put(lastName, bList);
					bList = new ArrayList();
					lastName = new String(bName);
				}
				int wapV = rs.getInt(2);
				int hiV = rs.getInt(3);
				int lowV = rs.getInt(4);
				L.debug("BrowserType : " + lastName + "; " + wapV + "; " + hiV + "; " + lowV);
				BrowserType bType = new BrowserType(lastName, wapV, hiV, lowV);
				bList.add(bType);
		}
		brTypes.put(lastName, bList);

	}catch (Exception e){
			L.error ("Failed to load Browser properties data from Database.", e);
	}finally{
			try{
				if (rs != null)   rs.close();
				if (ps != null)   ps.close();				
			}catch (Exception e){;}
	}
	L.debug("getBrowserProperties() : Finished.");
	return brTypes;
	}
    
	public static HashMap getBrowserMap() {
	return brMap;
	}
}
