package com.vzw.edr.selfProv.utils;

//Java imports
import java.io.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;

// logger
import org.apache.log4j.Logger;

//Xerces imports
import org.w3c.dom.*;
import org.xml.sax.InputSource;

/**
 * @author Ravi Vellanki, Anil
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class SPDOMUtility {

	private static Logger L =
		Logger.getLogger(SPProps.getBELogName(SPDOMUtility.class));
	/**
	 * Method parse. Reads a file and return a document object.
	 * @param file name to create Document from
	 * @return Document The created document.
	 */
	public static Document parse(String fileName) {
		Document document = null;
		try {
			DocumentBuilderFactory builderFactory =
				DocumentBuilderFactory.newInstance();
			builderFactory.setNamespaceAware(true);
			DocumentBuilder documentBuilder =
				builderFactory.newDocumentBuilder();
			document = documentBuilder.parse(new FileInputStream(fileName));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return document;
	}

	/**
	 * Method parse. Reads a file and return a document object.
	 * @param file name to create Document from
	 * @return Document The created document.
	 */
	public static Document parse(InputStream xmlString) {
		Document document = null;
		try {
			DocumentBuilderFactory factory =
				DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			document = builder.parse(xmlString);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return document;
	}

	/**
	 * Method getNewDocument. Create and return a new document object.
	 * @return Document The created document.
	 */
	public static Document getNewDocument() {
		Document document = null;
		try {
			DocumentBuilderFactory builderFactory =
				DocumentBuilderFactory.newInstance();
			builderFactory.setNamespaceAware(true);
			DocumentBuilder documentBuilder =
				builderFactory.newDocumentBuilder();
			document = documentBuilder.newDocument();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return document;
	}

	/**
	 * Method serialize. This will write a given document into the file specified.
	 * @param document the document to write to a file
	 * @param filePath The full path of the output file.
	 */
	public static void serialize(Document document, String filePath) {
		try {
			TransformerFactory transformerFactory =
				TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(document.getDocumentElement());
			StreamResult result =
				new StreamResult(new FileOutputStream(filePath));
			transformer.transform(source, result);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Method getDocumentFromString.
	 * @param xmlData to create Document from
	 * @return Document The created document.
	 */
	public static Document getDocumentFromString(String xmlData)
		throws Exception {
		DocumentBuilderFactory builderFactory =
			DocumentBuilderFactory.newInstance();
		builderFactory.setNamespaceAware(true);
		DocumentBuilder documentBuilder = builderFactory.newDocumentBuilder();
		Document document =
			documentBuilder.parse(new InputSource(new StringReader(xmlData)));

		return document;
	}

	/**
	 * Method getStringFromDocument.
	 * @param document which needs to be converted to string
	 * @return String the newly contructed string from document.
	 */
	public static String getStringFromDocument(Document document) {
		//return document.getDocumentElement().toString();

		String xmlData = null;
		try {
			TransformerFactory transformerFactory =
				TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(document.getDocumentElement());
			StringWriter writer = new StringWriter();
			StreamResult result = new StreamResult(writer);

			transformer.transform(source, result);

			writer.flush();
			xmlData = writer.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return xmlData;

	}

	/**
	 * Method getNodeValue.
	 * @param element
	 * @return String
	 */
	public static String getNodeValue(Element element) {
		String returnValue = null;

		Node child = element.getFirstChild();
		if (child != null) {
			returnValue = child.getNodeValue();
		}

		return returnValue;
	}

	/**
	 * Method getChildNodeValue.
	 * @param parentElement
	 * @param childNodeName
	 * @return String
	 */
	public static String getChildNodeValue(
		Element parentElement,
		String childNodeName) {
		String nodeValue = null;
		NodeList nlChildNodes =
			parentElement.getElementsByTagName(childNodeName);
		if (nlChildNodes != null && nlChildNodes.getLength() != 0) {
			Element childElement = (Element) nlChildNodes.item(0);
			Node firstChild = childElement.getFirstChild();
			if (firstChild != null) {
				nodeValue = firstChild.getNodeValue();
			}
		}

		return nodeValue;

	}

	/**
	 * Method getNextChildValue.
	 * @param parentElement
	 * @param childElementName
	 * @return String
	 */
	public static String getNextChildValue(
		Element parentElement,
		String childElementName) {
		String returnValue = null;
		Node nextNode = parentElement.getNextSibling();
		if (nextNode == null || nextNode.getNodeType() != Node.ELEMENT_NODE) {
			return returnValue;
		}

		Element nextElement = (Element) nextNode;
		if (!nextElement.getNodeName().equalsIgnoreCase(childElementName)) {
			return returnValue;
		}

		Node valueNode = nextElement.getFirstChild();
		if (valueNode != null) {
			return valueNode.getNodeValue();
		}

		return returnValue;
	}

	/*
	 * this method is used if the node list for the tag  is expected
	 * to have only one element
	 * The retrieved value is added to the hashmap
	 */

	public static String getElementText(
		Element elemt,
		String tag,
		int condition)
		throws Exception {
		NodeList nl = elemt.getElementsByTagName(tag);
		String nodeValue = "";

		if (nl.getLength() != 1) {
			if (condition == RequestFieldConstants.REQUIRED) {
				throw new Exception("xml request parsing error - bad " + tag);
			} else {
				return null;
			}
		}
		Node elementNode = nl.item(0);
		NodeList textNodeList = elementNode.getChildNodes();
		Node textNode = null;

		for (int j = 0; j < textNodeList.getLength(); j++) {
			textNode = textNodeList.item(j);
			if (textNode.getNodeType() == Node.TEXT_NODE) {
				nodeValue = nodeValue + textNode.getNodeValue();
				L.debug("tag: " + tag + "; Node value: " + nodeValue);
			}
		}
		// return the value 
		return nodeValue;

	}
	public static String getElementAttributeText(
		Element elemt,
		String tag,
		String attribute)
		throws Exception {
		NodeList nl = elemt.getElementsByTagName(tag);
		String attriValue = "";
		Node elementNode = null;
		String action = "";

		if ((nl != null) && (nl.getLength() > 0)) {
			elementNode = nl.item(0);
			// get the element node
			if (elementNode.getNodeType() == Node.ELEMENT_NODE) {
				attriValue = ((Element) elementNode).getAttribute(attribute);
				L.debug("Attribute:" + attribute + ",= " + action);
			} else {
				// throw an exception that non element node encountered
				throw new Exception("xml parsing error:non element node encountered");
			}
		} else {
			// throw an exception that non element node encountered
			throw new Exception("xml parsing error:nodlist is null or no node in it");
		}

		return attriValue;
	}
	
	
	public static String getElementAttributeTextWithCondition(Element elemt, String tag, String attribute, int condition )
		throws Exception {
		NodeList nl = elemt.getElementsByTagName(tag);
		String attriValue = "";
		Node elementNode = null;
		String action = "";

		if ((nl != null) && (nl.getLength() > 0)) {
			elementNode = nl.item(0);
			// get the element node
			if (elementNode.getNodeType() == Node.ELEMENT_NODE) {
				attriValue = ((Element) elementNode).getAttribute(attribute);
				L.debug("Attribute:" + attribute + ",= " + action);
			} else {
				// throw an exception that non element node encountered
				throw new Exception("xml parsing error:non element node encountered");
			}
		} else {
			
			if (condition == RequestFieldConstants.REQUIRED) {
				// throw an exception that non element node encountered
				throw new Exception("xml parsing error:nodlist is null or no node in it");
			} else {
					return null;
			}			
		}

		return attriValue;
	}


	//NOTE: For Testing ONLY

	//	public static void main(String args[])
	//	{
	//		try
	//		{
	//			//String xmlData = "testing for invalid xml format";
	//			//getDocumentFromString(xmlData);
	//			
	//			Document doc = parse("C:\\verizon\\WDS\\tech_docs\\ResendResp1.0.xml");
	//			NodeList nl = doc.getElementsByTagName("Element_Name");
	//			Element elementNameElement = (Element)nl.item(0);
	//			String msg = getNextChildValue(elementNameElement,"Msg");
	//			
	//			System.out.println("msg="+msg);
	//			
	//			
	//		}
	//		catch(Exception e)
	//		{
	//			e.printStackTrace();
	//		}
	//		
	//	}

} //class
