package com.vzw.selfProvisioning.utils;

//Java imports
import java.util.*;
import java.io.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;

//Xerces imports
import org.apache.log4j.Logger;
import org.w3c.dom.*;
import org.xml.sax.InputSource;

import com.vzw.edr.selfProv.utils.SPProps;

/**
 * @author Ravi Vellanki
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class DOMUtility {
	
	private static Transformer transformer = null;
	private static Logger L = Logger.getLogger(SPProps.getFELogName(DOMUtility.class));		



	/**
	 * Method parse. Reads a file and return a document object.
	 * @param file name to create Document from
	 * @return Document The created document.
	 */
	public static Document parse(String fileName)
	{
		Document document = null;
		try
		{
			DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
			builderFactory.setNamespaceAware(true);
			DocumentBuilder documentBuilder = builderFactory.newDocumentBuilder();
			document = documentBuilder.parse(new FileInputStream(fileName));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return document;
	}	
	
	/**
	 * Method getNewDocument. Create and return a new document object.
	 * @return Document The created document.
	 */
	public static Document getNewDocument()
	{
		Document document = null;
		try
		{
			DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
			builderFactory.setNamespaceAware(true);
			DocumentBuilder documentBuilder = builderFactory.newDocumentBuilder();
			document = documentBuilder.newDocument();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return document;
	}	
	
	
	/**
	 * Method serialize. This will write a given document into the file specified.
	 * @param document the document to write to a file
	 * @param filePath The full path of the output file.
	 */
	public static void serialize(Document document, String filePath)
	{
		try
		{
			if (transformer == null)
			{
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				transformer = transformerFactory.newTransformer();
			}
			
			DOMSource source = new DOMSource(document.getDocumentElement());			
			StreamResult result = new StreamResult(new FileOutputStream(filePath));
			transformer.transform(source,result);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}	
	
	
	/**
	 * Method getDocumentFromString.
	 * @param xmlData to create Document from
	 * @return Document The created document.
	 */
	public static Document getDocumentFromString(String xmlData) throws Exception
	{
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
		builderFactory.setNamespaceAware(true);
		DocumentBuilder documentBuilder = builderFactory.newDocumentBuilder();
		Document document = documentBuilder.parse(new InputSource(new StringReader(xmlData)));		
		
		return document;
	}
		
	
	/**
	 * Method getStringFromDocument.
	 * @param document which needs to be converted to string
	 * @return String the newly contructed string from document.
	 */
	public static String getStringFromDocument(Document document)
	{
		//return document.getDocumentElement().toString();
		
		String xmlData = null;
		try
		{
			if (transformer == null)
			{
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				transformer = transformerFactory.newTransformer();
			}
			
			DOMSource source = new DOMSource(document.getDocumentElement());
			StringWriter writer = new StringWriter();
			StreamResult result = new StreamResult(writer);
			
			transformer.transform(source,result);
			
			writer.flush();
			xmlData = writer.toString();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return xmlData;
		
	}
	
	
	/**
	 * Method getStringFromDocument.
	 * @param document which needs to be converted to string
	 * @return String the newly contructed string from document.
	 */
	public static String getStringFromElement(Element element)
	{
		return doGetStringFromElement(element, false);
		
	}
	
	/**
	 * Method doGetStringFromElement
	 * 
	 * This method will return the string representation of passed Element, but with NO PI i.e. 
	 * <code><?xml version="1.0" encoding="UTF-8" ?> </code>
	 * .
	 * @param document which needs to be converted to string
	 * @return String the newly contructed string from document.
	 */
	private static String doGetStringFromElement(Element element, boolean noPI)
	{
		String xmlData = null;
		try
		{
			if (transformer == null)
			{
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				transformer = transformerFactory.newTransformer();
			}
			
			DOMSource source = new DOMSource(element);
			StringWriter writer = new StringWriter();
			StreamResult result = new StreamResult(writer);
			
			if (noPI == true)
			{
				//set the property for NO PI
				transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			}
			
			transformer.transform(source,result);
			
			//clear the output parameter
			transformer.clearParameters();
			
			writer.flush();
			xmlData = writer.toString();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return xmlData;
		
	}
	
	
	
	
	
	/**
	 * Method getStringFromElementWithNoPI
	 * 
	 * This method will return the string representation of passed Element, but with NO PI i.e. 
	 * <code><?xml version="1.0" encoding="UTF-8" ?> </code>
	 * .
	 * @param document which needs to be converted to string
	 * @return String the newly contructed string from document.
	 */
	public static String getStringFromElementWithNoPI(Element element)
	{
		return doGetStringFromElement(element, true);
		
	}
	
	
	
	/**
	 * Method getNodeValue.
	 * @param element
	 * @return String
	 */
	public static String getNodeValue(Element element)
	{
		String returnValue = null;
		
		Node child = element.getFirstChild();
		if (child != null)
		{
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
	public static String getChildNodeValue(Element parentElement,String childNodeName)
	{
		String nodeValue = null;
		NodeList nlChildNodes = parentElement.getElementsByTagName(childNodeName);
		if (nlChildNodes != null && nlChildNodes.getLength() != 0)
		{
			Element childElement = (Element)nlChildNodes.item(0);
			Node firstChild = childElement.getFirstChild();
			if(firstChild != null)
			{
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
	public static String getNextChildValue(Element parentElement, String childElementName)
	{
		String returnValue = null;
		Node nextNode = parentElement.getNextSibling();
		if (nextNode == null || nextNode.getNodeType() != Node.ELEMENT_NODE)
		{
			return returnValue;
		}
		
		Element nextElement = (Element)nextNode;
		if (!nextElement.getNodeName().equalsIgnoreCase(childElementName))
		{
			return returnValue;
		}
		
		Node valueNode = nextElement.getFirstChild();
		if (valueNode != null)
		{
			return valueNode.getNodeValue();
		}		
		
		return returnValue;
	}
    
    
    /**
     *  This method creates an element and adds attributes to that element.
     */
    public static Element createElementWithAttrs(Document doc, String elementName, HashMap attrs)
    {
        Element newElement = null;
        
        if (doc == null || elementName == null)
        {
            return null;
        }
        
        newElement = doc.createElement(elementName);
        
        if (attrs == null || attrs.isEmpty())
        {
            return newElement;
        }
        
        Iterator keys = attrs.keySet().iterator();
        while(keys.hasNext())
        {
            String attrName = (String)keys.next();
            String attrValue = (String)attrs.get(attrName);
            newElement.setAttribute(attrName,attrValue);
        }
        
        return newElement;
    }
	
    public static Element createElement(Document doc, String elementName, String elementValue)
    {
        Element newElement = null;
        
        newElement = doc.createElement(elementName);
        
        if (elementValue == null)
        {
            return newElement;
        }
        
        //add the value to it
        Node text = doc.createTextNode(elementValue);
        newElement.appendChild(text);
        
        return newElement;
    }
    
    public static Element createElement(Document doc, String elementName)
    {
        return createElement(doc, elementName, null);
    }
    
	//NOTE: For Testing ONLY
	
	public static void main(String args[])
	{
		try
		{
			//String xmlData = "testing for invalid xml format";
			//getDocumentFromString(xmlData);
			
			Document doc = parse("C:\\verizon\\WDS\\tech_docs\\ResendResp1.0.xml");
			NodeList nl = doc.getElementsByTagName("Element_Name");
			Element elementNameElement = (Element)nl.item(0);
			String msg = getNextChildValue(elementNameElement,"Msg");
			
			L.debug("msg="+msg);
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
		
	

}//class
