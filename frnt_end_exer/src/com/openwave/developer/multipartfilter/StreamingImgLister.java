package com.openwave.developer.multipartfilter;

import java.util.ArrayList;
import nu.xom.*;

public class StreamingImgLister extends NodeFactory {

	int depth = 0;
	Nodes empty = new Nodes();
	ArrayList urls = new ArrayList(20);
	//String base_tag_href = null;

	// We don't need the comments.     
	public Nodes makeComment(String data) {
		return empty;
	}

	// We don't need text nodes at all    
	public Nodes makeText(String data) {
		return empty;
	}

	public Element startMakingElement(String name, String namespace) {
		return new Element(name, namespace);
	}

	public Nodes finishMakingElement(Element element) {

		//get picture url
		if (element.getLocalName().equals("img")) {
			if (element.getAttributeValue("src") != null) {
//				System.out.println(element.toXML());
				urls.add(element.getAttributeValue("src"));
			}
		}
		//get CSS url
		if (element.getLocalName().equals("link")) {
			if ("stylesheet".equals(element.getAttributeValue("rel"))
				&& element.getAttributeValue("href") != null) {

//				System.out.println(element.toXML());
				urls.add(element.getAttributeValue("href"));
			}
		}
		/*
		//get URL if 'base' tag is specified
		if (element.getLocalName().equals("base")) {
		    if (element.getAttributeValue("href") != null) {
			System.out.println(element.toXML());
			base_tag_href = element.getAttributeValue("href");
		    }
		}
		*/
		if (element.getParent() instanceof Document) {
			return new Nodes(element);
		} else
			return empty;
	}

	public Nodes makeAttribute(
		String name,
		String URI,
		String value,
		Attribute.Type type) {
		if (name.equals("src") || name.equals("href") || name.equals("rel")) {
			return new Nodes(new Attribute(name, URI, value, type));
		}
		return empty;
	}

	public Nodes makeDocType(
		String rootElementName,
		String publicID,
		String systemID) {
		return empty;
	}

	public Nodes makeProcessingInstruction(String target, String data) {
		return empty;
	}

	/* export urls */
	public ArrayList getUrls() {
		return urls;
	}

	/* export URL base tag */
	//public String getBase_tag_href() {
	//return base_tag_href;
	//}
}
