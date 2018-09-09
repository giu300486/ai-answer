package service;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;

public class Service {
	
	protected DocumentBuilderFactory factory;
	protected DocumentBuilder builder;
	protected Document doc;
	protected XPathFactory xpFactory;
	protected XPath path;
	protected String contextPath;
	
	public Service(String contextPath) {
		
		this.factory = DocumentBuilderFactory.newInstance();
		try {
			this.builder = this.factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		
		this.xpFactory = XPathFactory.newInstance();
		this.path = (XPath) this.xpFactory.newXPath();
		
		this.contextPath = contextPath;
		
	}
	
}
