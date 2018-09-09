package service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class HomeService extends Service{

	private String filePath;
	
	public HomeService(String contextPath) {
		
		super(contextPath);
		
	}
	
	public HashMap<String,List<String>> getQuestionnaires(){
		
		HashMap<String,List<String>> questionnaires = new HashMap<>();
		
		this.filePath = this.contextPath + "/xml/amministratori.xml";
		try {
			this.doc = this.builder.parse(new File(filePath));
		} catch (SAXException | IOException e) {
			e.printStackTrace();
		}
		
		NodeList username = null;
		try {
			username = (NodeList) path.evaluate("amministratori/amministratore/username/text()", doc, XPathConstants.NODESET);
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
		
		for(int i = 0; i < username.getLength(); i++){
			this.filePath = this.contextPath + "/xml/" + username.item(i).getTextContent() + "/";
			
			List<String> questionari = new ArrayList<>();
			
			File dir = new File(this.filePath);
			File[] directoryListing = dir.listFiles();
			for (File child : directoryListing) {
				if(child.getName().contains(".xml")){
					try {
						this.doc = this.builder.parse(child);
					} catch (SAXException | IOException e) {
						e.printStackTrace();
					}
					
					Node abilita = null;
					boolean abilitato = false;
					try {
						abilita = (Node) path.evaluate("questionario/@abilita", doc, XPathConstants.NODE);
						abilitato = Boolean.parseBoolean(abilita.getNodeValue());
					} catch (XPathExpressionException e) {
						e.printStackTrace();
					}
					
					if(abilitato){
						String[] split = child.getName().split(".xml");
						questionari.add(split[0]);
					}
				}
			}
			questionnaires.put(username.item(i).getTextContent(),questionari);
		}
		
		System.out.println(questionnaires);
		
		return questionnaires;
		
	}
	
	public HashMap<String,List<String>> getQuestionnaires(String wordMatch){
		
		HashMap<String,List<String>> questionnaires = new HashMap<>();
		
		this.filePath = this.contextPath + "/xml/amministratori.xml";
		try {
			this.doc = this.builder.parse(new File(filePath));
		} catch (SAXException | IOException e) {
			e.printStackTrace();
		}
		
		NodeList username = null;
		try {
			username = (NodeList) path.evaluate("amministratori/amministratore/username/text()", doc, XPathConstants.NODESET);
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
		
		for(int i = 0; i < username.getLength(); i++){
			this.filePath = this.contextPath + "/xml/" + username.item(i).getTextContent() + "/";
			
			List<String> questionari = new ArrayList<>();
			
			File dir = new File(this.filePath);
			File[] directoryListing = dir.listFiles();
			for (File child : directoryListing) {
				if(child.getName().contains(".xml")){
					try {
						this.doc = this.builder.parse(child);
					} catch (SAXException | IOException e) {
						e.printStackTrace();
					}
					
					Node abilita = null;
					Node nome = null;
					boolean abilitato = false;
					try {
						abilita = (Node) path.evaluate("questionario/@abilita", doc, XPathConstants.NODE);
						nome = (Node) path.evaluate("questionario/@nome", doc, XPathConstants.NODE);
						abilitato = Boolean.parseBoolean(abilita.getNodeValue());
					} catch (XPathExpressionException e) {
						e.printStackTrace();
					}
					
					if(abilitato && nome.getNodeValue().contains(wordMatch)){
						String[] split = child.getName().split(".xml");
						questionari.add(split[0]);
					}
				}
			}
			questionnaires.put(username.item(i).getTextContent(),questionari);
		}
		
		System.out.println(questionnaires);
		
		return questionnaires;
		
	}
}
