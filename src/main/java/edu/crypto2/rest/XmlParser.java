/**
 * Boris Damjanovic, 230/08, FON, Belgrade - crypto2 - 2009
 */
package edu.crypto2.rest;

import java.io.*;
import org.w3c.dom.*;
import javax.xml.parsers.*;

 

public class XmlParser {
	private String ResultString;

	public XmlParser(String element_name)
		 throws Exception {
		        File file = new File("values.xml");
		        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		        DocumentBuilder db = dbf.newDocumentBuilder();
		        Document doc = db.parse(file);
		        GettingText(doc, element_name);
		    }
		 
    public void GettingText(Document doc, String element_name) {
         Element e = doc.getDocumentElement();
         NodeList nodeList = doc.getElementsByTagName("input_for_transformation");
         for (int i = 0; i < nodeList.getLength(); i++) {
             Node node = nodeList.item(i);
             if (node.getNodeType() == Node.ELEMENT_NODE) {
                 Element element = (Element) node;
                 NodeList nodelist = element.getElementsByTagName(element_name);
                 Element element1 = (Element) nodelist.item(0);
                 NodeList fstNm = element1.getChildNodes();
                 ResultString = (fstNm.item(0)).getNodeValue();
                 /*System.out.print("First Name : " + ResultString);*/
                }
         }
    }

	public String getResultString() {
		return ResultString;
	}

}
