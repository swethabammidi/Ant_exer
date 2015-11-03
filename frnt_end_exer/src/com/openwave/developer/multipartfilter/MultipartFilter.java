package com.openwave.developer.multipartfilter;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.activation.*;

//to parse the WML/XHTML response
import nu.xom.*;

// for logger and property objects
import com.vzw.edr.selfProv.utils.SPProps;

import org.apache.log4j.Logger;

/**
 * Filter examines WURFL to figure out if multipart is supported
 * by the device which made the request.
 * <pre>
 * - if it's not, business as usual.
 * - if it is:
 *  . parse WML/XHTML file
 *  . extract img URL
 *  . request all imgs
 *  . package them into a unique Multipart message
 *  . send the multipart instead
 * </pre>
 */
public class MultipartFilter implements Filter {

	private static Logger L =
		Logger.getLogger(SPProps.getFELogName(MultipartFilter.class));
	protected FilterConfig config;
	private static HashMap bytesMap = new HashMap(20);

	public void init(FilterConfig config) throws ServletException {
		this.config = config;

		//initialize the wurfl, unless already initialized
		/*	if ( !ObjectsManager.isWurflInitialized() )  {
			    ObjectsManager.initFromWebApplication(config.getServletContext());
			    L.debug("init(): About to initialize WURFL for use in filter");	
			} */
	}

	/**
	 * Called when the filter is about to be shut down.
	 */
	public void destroy() { /* noop */
	}

	public void doFilter(
		ServletRequest request,
		ServletResponse response,
		FilterChain chain)
		throws IOException, ServletException {

		ArrayList urls; //urls extracted from markup
		ArrayList mp_bits = new ArrayList(20); //multipart bits
		String myPath =
			config.getServletContext().getRealPath("/") + File.separator;

		String UA; //user agent
		boolean do_multipart = false;
		HttpServletRequest http_request = (HttpServletRequest) request;
		String device_id;
		String preferred_mimetype;
		String base_tag_href = null;
		String markup_mime_type = "test/html";

		// try  to see accept header for checking the device capability
		Enumeration headerNames = http_request.getHeaderNames();

		while (headerNames.hasMoreElements()) {
			String headerName = (String) headerNames.nextElement();
			if ((headerName != null)
				&& headerName.equalsIgnoreCase("accept")) {
				L.debug(
					headerName + " : " + http_request.getHeader(headerName));
				String accept_capacity = http_request.getHeader(headerName);
				if ((accept_capacity != null)
					&& (accept_capacity.toLowerCase().indexOf("multipart/mixed") != -1))
					do_multipart = true;
			}

		}
		// Server Response wrapper to buffer response (so we can parse the mark-up)
		 
		//let's go for it and try to do the multipart
		L.debug("doFilter(): Client do multipart capability:" + do_multipart);
		if (do_multipart) {
			
			request.setAttribute("multipart", "true");
			//first, we need to grab the response, extract the mark up
			//and retrieve the img tags
			BufferedResponseWrapper wrappedResponse = 
			new BufferedResponseWrapper(response, config.getServletContext());
	    
			chain.doFilter(request, wrappedResponse);

			String mark_up = wrappedResponse.getBufferAsString();
			//markup_mime_type = wrappedResponse.getContentType(); //not available before V2.4!
			markup_mime_type = "text/html";

			int dtd_idx_wml = mark_up.indexOf("wml");
			int dtd_idx_html = mark_up.indexOf("html");
			if (dtd_idx_wml != -1) {
				markup_mime_type = "text/vnd.wap.wml";
				L.error(
				"doFilter(): Can't do multipart for WML. Abandon Multipart.");
				abandon_multipart(response, mark_up, markup_mime_type);
				return;
			}

			if (dtd_idx_html != -1) {
				markup_mime_type = "text/html";
			}
			if (dtd_idx_wml == -1 && dtd_idx_html == -1) {
				L.debug(
					"doFilter(): Cannot find DTD. This is neither WML nor XHTML or couldn't understand");
				abandon_multipart(response, mark_up, markup_mime_type);
				return;
			}
			//System.out.print(mark_up);

			//Let's use the power of XOM to parse the mark_up and get the URLs of
			//images and CSS. I use the tagsoup parser to avoid problems with
			//DTDs downloading behind the scenes and dangling entities
			StreamingImgLister sil = new StreamingImgLister();
			org.xml.sax.XMLReader parser = new org.ccil.cowan.tagsoup.Parser();
			Builder builder = new Builder(parser, false, sil);

			try {
				Document doc = builder.build(mark_up, "");

				urls = sil.getUrls();
				//base_tag_href = sil.getBase_tag_href();
				L.debug(
					"doFilter(): URLS needs to put in multipart: "
						+ urls.toString());
			}
			// indicates a well-formedness error
			catch (ParsingException ex) {
				L.error("doFilter(): mark-up is not well-formed.");
				L.debug(ex.getMessage());
				//let's give up on multipart and send the mark-up as it is
				abandon_multipart(response, mark_up, markup_mime_type);
				return;
			} catch (IOException ex) {
				L.debug(ex);
				abandon_multipart(response, mark_up, markup_mime_type);
				return;
			}

			//we got the URLs. Let's retrieve them

			//first initialize the "multipart bits"
			String pageurl;
			if (base_tag_href != null) {
				//the mark_up uses the base tag for overriding
				//default mechanism for solving relative URLs
				if (base_tag_href.startsWith("http")) {
					pageurl = base_tag_href;
				} else {
					//relative HREF for base tag, for ex href="/images/"
					//too much parsing, abandon multipart
					abandon_multipart(response, mark_up, markup_mime_type);
					return;
				}
			} else {
				pageurl = http_request.getRequestURL().toString();
			}
			L.debug(pageurl);

			for (int i = 0; i < urls.size(); i++) {
				mp_bits.add(new MultipartPiece((String) urls.get(i), pageurl));
			}

			//we got everything now. It's just time to assemble the Multipart response	
			//let's check that all the content has been retrieved correctly
			try {
				for (int i = 0; i < mp_bits.size(); i++) {
					MultipartPiece mp = (MultipartPiece) mp_bits.get(i);
					String fileRealPath = config.getServletContext().getRealPath(unrelative(mp.getUrl()));
					L.debug("doFilter(): Resource:" + fileRealPath);
					FileDataSource source = new FileDataSource(fileRealPath);
					if (source == null) {
						L.error("doFilter():Source comes null, so abandon multipart");
						abandon_multipart(response, mark_up, markup_mime_type);
						return;
					}
					mp.setMimetype(source.getContentType());
					byte[] sourceBytes = getBytesFromSource(fileRealPath);
					if (sourceBytes == null)
						throw new Exception("No data read for resource: " + unrelative(mp.getUrl()));
					mp.setBytes(sourceBytes);
				}
			} catch (Exception e) {
				L.error("doFilter():Error accessing resources" + e.getMessage());
				abandon_multipart(response, mark_up, markup_mime_type);
				return;
			}

			response.setContentType("multipart/mixed;boundary=\"foo\"");
			ServletOutputStream out = response.getOutputStream();
			out.write("--foo\r\n".getBytes());
			//out.write("Content-Type: text/html\r\n".getBytes());
			out.write("Content-Type: ".getBytes());
			out.write(markup_mime_type.getBytes());
			out.write("\r\n".getBytes());
			out.write("Content-length: ".getBytes());
			out.write(Integer.toString(mark_up.getBytes().length).getBytes());
			out.write("\r\n".getBytes());
			out.write("\r\n".getBytes());
			out.write(mark_up.getBytes());
			out.write("\r\n".getBytes());

			for (int i = 0; i < mp_bits.size(); i++) {

				MultipartPiece mp = (MultipartPiece) mp_bits.get(i);
				//L.debug("mime type for "+i+" = "+mp.getMimetype());
				out.write("--foo\r\n".getBytes());
				out.write("Content-Type: ".getBytes());
				out.write(mp.getMimetype().getBytes());
				out.write("\r\n".getBytes());
				//L.debug(mp.getMimetype());
				out.write("Content-length: ".getBytes());
				out.write(Integer.toString(mp.getBytes().length).getBytes());
				out.write("\r\n".getBytes());
				out.write(("Content-location: " + mp.getUrl()).getBytes());
				out.write("\r\n".getBytes());
				out.write("\r\n".getBytes());
				out.write(mp.getBytes());
				out.write("\r\n".getBytes());
			}
			out.write("--foo--\r\n".getBytes());
			out.write("\r\n".getBytes());
			out.flush();
			out.close();

		} else { //don't do multipart

			// Pass the request on to the next processor in the
			// filter chain.
			L.debug("doFilter(): No Multipart. Normal Response:");
			chain.doFilter(request, response);
		}
		//L.debug("done :"+System.currentTimeMillis());
	}

	void abandon_multipart(
		ServletResponse response,
		String mark_up,
		String markup_mime_type)
		throws IOException {
			
		L.debug("doFilter():Normal Response, Content-Length:" + mark_up.length());		
		response.setContentLength(mark_up.length());
		response.setContentType(markup_mime_type);
		PrintWriter out = response.getWriter();
		out.print(mark_up);
		out.flush();

	}
	private static String unrelative(String url)
	{
		if (url.startsWith("../"))
			return url.substring(3);
		else
			return url;
	}
    
	private static String getName(String url)
	{
		int idx = url.lastIndexOf("/");
		if (idx != -1 && idx < url.length())
		{
			return url.substring(idx+1);
		}
		else
		{
			return url;
		}
	}
	private static byte[] getBytesFromSource(String source)
		throws IOException
	{
		byte[] srcBytes = null;
		if (bytesMap.containsKey(source))
		{
			L.debug ("MultipartFileter(): Found Source in cache. Source = " + source);
			srcBytes = (byte[])bytesMap.get(source);
		}
		else
		{
			File inpSrc = new File(source);
			int srcLen = (int)inpSrc.length();
			srcBytes = new byte[srcLen];
			FileInputStream fis = new FileInputStream(source);
			fis.read(srcBytes);
			L.debug ("getBytesFromSource():srcLen = " + srcLen + ", srcBytes.length=" + srcBytes.length);
			L.debug ("getBytesFromSource(): Read Source and stored in cache. Source = " + source);
			bytesMap.put(source, srcBytes);
		}
		return srcBytes;
	}
	
}
