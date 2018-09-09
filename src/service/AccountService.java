package service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import model.Utente;

public class AccountService extends Service{

	private String filePath;
	
	public AccountService(String contextPath) {
		
		super(contextPath);
		
		this.filePath = this.contextPath + "/xml/amministratori.xml";
		
		try {
			this.doc = this.builder.parse(new File(filePath));
		} catch (SAXException | IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public Utente loginAmministratore(String username, String password){
		
		boolean successLogin = false;
		try {
			successLogin =  (boolean) path.evaluate("amministratori/amministratore/username/text()=" + "'" + username + "'" + " and amministratori/amministratore/password/text()=" + "'" + password + "'", doc, XPathConstants.BOOLEAN);
			
		} catch (XPathExpressionException e) {
			e.printStackTrace();
			return null;
		}

		if(successLogin){
			Utente amministratore = new Utente();
			
			Node nodeAmministratore = null;
			try {
				nodeAmministratore = (Node) path.evaluate("amministratori/amministratore[username/text()=" + "'" + username + "']", doc, XPathConstants.NODE);
			} catch (XPathExpressionException e) {
				e.printStackTrace();
				return null;
			}
			
			Node nodeUsername = null;
			Node nodePassword = null;
			Node nodeNome = null;
			Node nodeCognome = null;
			Node nodeEmail = null;
			
			NodeList list = nodeAmministratore.getChildNodes();
			for(int i = 0; i < list.getLength(); i++){
				
				try {
					nodeUsername = (Node) path.evaluate("username/text()", list, XPathConstants.NODE);
					nodePassword = (Node) path.evaluate("password/text()", list, XPathConstants.NODE);
					nodeNome = (Node) path.evaluate("nome/text()", list, XPathConstants.NODE);
					nodeCognome = (Node) path.evaluate("cognome/text()", list, XPathConstants.NODE);
					nodeEmail = (Node) path.evaluate("email/text()", list, XPathConstants.NODE);
				} catch (XPathExpressionException e) {
					e.printStackTrace();
					return null;
				}
				
			}
			
			amministratore.setUsername(nodeUsername.getNodeValue());
			amministratore.setPassword(nodePassword.getNodeValue());
			amministratore.setNome(nodeNome.getNodeValue());
			amministratore.setCognome(nodeCognome.getNodeValue());
			amministratore.setEmail(nodeEmail.getNodeValue());
			
			return amministratore;
			
		}
		
		return null;
		
	}
	
	private boolean usernamePresente(String username){
		
		Node elem = null;
		try {
			elem = (Node) path.evaluate("amministratori/amministratore/username[text()=" + "'" + username + "']", doc, XPathConstants.NODE);
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
		
		return elem != null;
		
	}
	
	public boolean registrazioneAmministratore(Utente amministratore){
		if(usernamePresente(amministratore.getUsername()))
			return false;
		
		
		Element root = null;
		try {
			root = (Element) path.evaluate("amministratori", doc, XPathConstants.NODE);
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
		
		Element newAmministratore = doc.createElement("tns:amministratore");

		Element usernameAmministratore = doc.createElement("tns:username");
		usernameAmministratore.setTextContent(amministratore.getUsername());

		Element passwordAmministratore = doc.createElement("tns:password");
		passwordAmministratore.setTextContent(amministratore.getPassword());
		
		Element nomeAmministratore = doc.createElement("tns:nome");
		nomeAmministratore.setTextContent(amministratore.getNome());

		Element cognomeAmministratore = doc.createElement("tns:cognome");
		cognomeAmministratore.setTextContent(amministratore.getCognome());
		
		Element emailAmministratore = doc.createElement("tns:email");
		emailAmministratore.setTextContent(amministratore.getEmail());

		newAmministratore.appendChild(usernameAmministratore);
		newAmministratore.appendChild(passwordAmministratore);
		newAmministratore.appendChild(nomeAmministratore);
		newAmministratore.appendChild(cognomeAmministratore);
		newAmministratore.appendChild(emailAmministratore);
		
		
		root.appendChild(newAmministratore);
		
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = null;
		try {
			transformer = transformerFactory.newTransformer();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		}
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		
		
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File(filePath));
		try {
			transformer.transform(source, result);
		} catch (TransformerException e) {
			e.printStackTrace();
		}
		
		return true;
	}

	public List<String> filesCSS(String username){
		
		List<String> filesCSS = new ArrayList<>();
		
		File dir = new File(this.contextPath + "/xml/" + username + "/");
		File[] directoryListing = dir.listFiles();
		for (File child : directoryListing) {
			if(child.getName().contains(".css")){
				filesCSS.add(child.getName());
			}
		}
		
		return filesCSS;
		
		
	}
	
	public boolean modifyInfoProfile(Utente amministratore) {
		
		Node nodeAmministratore = null;
		try {
			nodeAmministratore = (Node) path.evaluate("amministratori/amministratore[username/text()=" + "'" + amministratore.getUsername() + "']", doc, XPathConstants.NODE);
		} catch (XPathExpressionException e) {
			e.printStackTrace();
			return false;
		}
		
		Node nodeUsername = null;
		Node nodePassword = null;
		Node nodeNome = null;
		Node nodeCognome = null;
		Node nodeEmail = null;
		
		NodeList list = nodeAmministratore.getChildNodes();
		for(int i = 0; i < list.getLength(); i++){
			
			try {
				nodeUsername = (Node) path.evaluate("username/text()", list, XPathConstants.NODE);
				nodePassword = (Node) path.evaluate("password/text()", list, XPathConstants.NODE);
				nodeNome = (Node) path.evaluate("nome/text()", list, XPathConstants.NODE);
				nodeCognome = (Node) path.evaluate("cognome/text()", list, XPathConstants.NODE);
				nodeEmail = (Node) path.evaluate("email/text()", list, XPathConstants.NODE);
			} catch (XPathExpressionException e) {
				e.printStackTrace();
				return false;
			}
			
		}
		
		nodeUsername.setTextContent(amministratore.getUsername());
		nodePassword.setTextContent(amministratore.getPassword());
		nodeNome.setTextContent(amministratore.getNome());
		nodeCognome.setTextContent(amministratore.getCognome());
		nodeEmail.setTextContent(amministratore.getEmail());
		
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = null;
		try {
			transformer = transformerFactory.newTransformer();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
			return false;
		}
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		
		
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File(filePath));
		try {
			transformer.transform(source, result);
		} catch (TransformerException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
}
