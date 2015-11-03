package com.openwave.developer.multipartfilter;

// for logger and property objects
import com.vzw.edr.selfProv.utils.SPProps;
import org.apache.log4j.Logger;

/*
 * a MultipartPiece object knows everything about how to get itself into a multipart chunk 
 */
class MultipartPiece {
	private static Logger L =
		Logger.getLogger(SPProps.getFELogName(MultipartPiece.class));
	private String url;
	private String absolute_url;
	//private String pageurl;

	private String urlbase;
	private String host;

	private String mimetype;
	private byte[] bytes;
	private boolean complete = false;
	private boolean error = false;

	public MultipartPiece(String url, String pageurl) {

		this.url = url;

		L.debug("URL " + url + "-  PageURL " + pageurl);
		//figure out URL to containing directory and host
		if (pageurl.startsWith("http://") || pageurl.startsWith("https://")) {
			int index = pageurl.lastIndexOf("/");
			if (index == 6 || index == 7) {
				urlbase = pageurl;
				//url may be of the form "http://www.dom.com"
			} else {
				urlbase = pageurl.substring(0, index);
			}
			L.debug("URLBase = " + urlbase);

			//now let's go for host
			if (pageurl.startsWith("http://")) {
				String nohttp = pageurl.substring(7);
				index = nohttp.indexOf("/");
				if (index != -1) {
					host = "http://" + nohttp.substring(0, index);
				} else {
					host = pageurl;
				}
				L.debug("host for HTTP = " + host);
			}
			if (pageurl.startsWith("https://")) {
				String nohttp = pageurl.substring(8);
				index = nohttp.indexOf("/");
				if (index != -1) {
					host = "https://" + nohttp.substring(0, index);
				} else {
					host = pageurl;
				}

				L.debug("host for HTTPS= " + host);
			}

		} else {
			L.error("ERROR: PageURL = " + pageurl);
		}

		//find if it's absolute "http://server/img.gif"
		if (url.indexOf("http://") != -1 || url.indexOf("https://") != -1) {
			absolute_url = url;

		} else {
			//find if it's "root" relative url "/imgs/img.gif"
			if (url.indexOf("/") == 0) {
				absolute_url = host + url;
			} else {
				//it must be relative to current directory ("img.gif")
				absolute_url = urlbase + "/" + url;
			}
		}
		L.debug("Absolute URL:  " + absolute_url);
	}

	public String getUrl() {
		return url;
	}
	public String getAbsolute_url() {
		return absolute_url;
	}

	public String getMimetype() {
		return mimetype;
	}

	public void setMimetype(String newMimetype) {
		this.mimetype = newMimetype;
	}

	public byte[] getBytes() {
		return bytes;
	}

	public void setBytes(byte[] newBytes) {
		this.bytes = newBytes;
	}

	public boolean isComplete() {
		return complete;
	}

	public void setComplete(boolean newComplete) {
		this.complete = newComplete;
	}

}
