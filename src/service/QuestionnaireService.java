package service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import javax.xml.XMLConstants;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import grafi.ArcoPesato;
import grafi.Grafi;
import grafi.Grafo;
import grafi.GrafoOrientatoPesatoImpl;
import grafi.Peso;
//import grafo.ArcoPesato;
//import grafo.Grafi;
//import grafo.Grafo;
//import grafo.GrafoOrientatoPesatoImpl;
//import grafo.Peso;
import model.Domanda;

public class QuestionnaireService extends Service{

	private Stack<Memento> mementoStack;
//	private boolean hannoLoStessoContenuto = true;
	
	public QuestionnaireService(String contextPath) {
		
		super(contextPath);
		this.mementoStack = new Stack<>();
		
	}
	
	public List<String> createQuestionnaire(String username, String fileXml){
		
		List<String> errors = new ArrayList<>();
		boolean validato = true;
		
		InputSource is = new InputSource();
	    is.setCharacterStream(new StringReader(fileXml));
	    try {
			this.doc = this.builder.parse(is);
		} catch (SAXException | IOException e) {
			validato = false;
			errors.add(e.getMessage());
		}
		
	    Node nameQuestionnaire = null;
		try {
			nameQuestionnaire = (Node) path.evaluate("questionario/@nome", doc, XPathConstants.NODE);
		} catch (XPathExpressionException e) {
			validato = false;
			errors.add(e.getMessage());
		}
		
		String nomeQuestionario = nameQuestionnaire.getNodeValue();
	    if(nomeQuestionario.equals("")){
	    	validato = false;
	    	errors.add("Il nome del questionario e' mancante");
	    }
	    
		File dir = new File(this.contextPath + "/xml/" + username + "/");
		File[] directoryListing = dir.listFiles();
		for (File child : directoryListing) {
			if(child.getName().equals(nomeQuestionario + ".xml")){
				validato = false;
				errors.add("Esiste gia' un questionario con il nome " + nomeQuestionario);
			}
		}
		
		NodeList nomiClassiAppartenenza = null;
		NodeList nomiClassiEsclusione = null;
		NodeList codiciDomanda = null;
		NodeList classiAppartenenza = null;
		NodeList classiEsclusione = null;
		NodeList domandeClasseEsclusione = null;
		try {
			nomiClassiAppartenenza = (NodeList) path.evaluate("questionario/classeAppartenenza/@nome", doc, XPathConstants.NODESET);
			nomiClassiEsclusione = (NodeList) path.evaluate("questionario/classeEsclusione/@nome", doc, XPathConstants.NODESET);
			codiciDomanda = (NodeList) path.evaluate("questionario/domanda/@codiceDomanda", doc, XPathConstants.NODESET);
			classiAppartenenza = (NodeList) path.evaluate("questionario/domanda/risposta/classeAppartenenza/@nome", doc, XPathConstants.NODESET);
			classiEsclusione = (NodeList) path.evaluate("questionario/domanda/risposta/classeEsclusione/@nome", doc, XPathConstants.NODESET);
			domandeClasseEsclusione = (NodeList) path.evaluate("questionario/classeEsclusione/domanda/@nome", doc, XPathConstants.NODESET);
		} catch (XPathExpressionException e) {
			validato = false;
			errors.add(e.getMessage());
		}
		
		int indiceClasseAppartenenza = 0;
		for(int i = 0; i < classiAppartenenza.getLength(); i++){
			indiceClasseAppartenenza = 0;
			for(int j = 0; j < nomiClassiAppartenenza.getLength(); j++){
				if(!classiAppartenenza.item(i).getNodeValue().equals(nomiClassiAppartenenza.item(j).getNodeValue()))
					indiceClasseAppartenenza++;
			}
			if(indiceClasseAppartenenza == nomiClassiAppartenenza.getLength()){
				validato = false;
				errors.add("La classe di appartenenza " + classiAppartenenza.item(i).getNodeValue() + " non e' stata dichiarata in precedenza");
			}
		}
		
		int indiceClasseEsclusione = 0;
		for(int i = 0; i < classiEsclusione.getLength(); i++){
			indiceClasseEsclusione = 0;
			for(int j = 0; j < nomiClassiEsclusione.getLength(); j++){
				if(!classiEsclusione.item(i).getNodeValue().equals(nomiClassiEsclusione.item(j).getNodeValue()))
					indiceClasseEsclusione++;
			}
			if(indiceClasseEsclusione == nomiClassiEsclusione.getLength()){
				validato = false;
				errors.add("La classe di esclusione " + classiEsclusione.item(i).getNodeValue() + " non e' stata dichiarata in precedenza");
			}
		}
		
		int indiceDomandaClasseEsclusione = 0;
		for(int i = 0; i < domandeClasseEsclusione.getLength(); i++){
			indiceDomandaClasseEsclusione = 0;
			for(int j = 0; j < codiciDomanda.getLength(); j++){
				if(!domandeClasseEsclusione.item(i).getNodeValue().equals(codiciDomanda.item(j).getNodeValue()))
					indiceDomandaClasseEsclusione++;
			}
			if(indiceDomandaClasseEsclusione == codiciDomanda.getLength()){
				validato = false;
				errors.add("La domanda " + domandeClasseEsclusione.item(i).getNodeValue() + " non e' una domanda dichiarata in precedenza");
			}
		}
		
		
		StringReader reader = new StringReader(fileXml);
		SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        
		Schema schema = null;
		try {
			schema = schemaFactory.newSchema(new File(this.contextPath + "/xsd/questionario.xsd"));
		} catch (SAXException e) {
			validato = false;
			errors.add(e.getMessage());
		}
        
		Validator validator = schema.newValidator();
		try {
			validator.validate(new StreamSource(reader));
		} catch (SAXException | IOException e1) {
			validato = false;
			errors.add(e1.toString());
		}
		
		if(validato)
			if(!eRaggiungibileNodoTerminale(username,null,nomeQuestionario)){
				validato = false;
				errors.add("La risposta non e' raggiungibile per il questionario " + nomeQuestionario);
			}
		
        if(validato){
        	File file = new File(this.contextPath + "/xml/" + username + "/" + nomeQuestionario + ".xml");
        	BufferedWriter bw;
			try {
				bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
				bw.write(fileXml);
				bw.flush();
				bw.close();
			} catch (IOException e) {
				errors.add(e.getMessage());
			}
			errors.add("Il Questionario e' stato creato con successo");
        }
        
        return errors;
        
	}
	
	public List<String> getQuestionnaires(String username){
		
		List<String> listQuestionnaires = new ArrayList<>();
		
		File dir = new File(this.contextPath + "/xml/" + username + "/");
		File[] directoryListing = dir.listFiles();
		for (File child : directoryListing) {
			if(child.getName().contains(".xml")){
				String[] split = child.getName().split(".xml");
				listQuestionnaires.add(split[0]);
			}
		}
		
		return listQuestionnaires;
		
	}
	
	public String getQuestionnaire(String nomeQuestionario, String username){
		
		String questionnarie = null;
		
		Path pathQuestionnaire = Paths.get(this.contextPath + "/xml/" + username + "/", nomeQuestionario + ".xml");

		Charset charset = Charset.forName("UTF-8");
	    try {
	      List<String> lines = Files.readAllLines(pathQuestionnaire, charset);

	      for (String line : lines) {
	    	  if(questionnarie == null)
	    		  questionnarie = line;
	    	  else
	    		  questionnarie += line + "\n";
	      }
	    } catch (IOException e) {
	      System.out.println(e);
	    }
	    
		return questionnarie;
	}
	
	public HashMap<String,Boolean> checkQuestionnaire(String username){
		
		HashMap<String,Boolean> checkQuestionnaire = new HashMap<>();
		
		File dir = new File(this.contextPath + "/xml/" + username + "/");
		File[] directoryListing = dir.listFiles();
		for (File child : directoryListing) {
			if(child.getName().contains(".xml")){
				String[] split = child.getName().split(".xml");
				Node abilita = null;
				try {
					this.doc = this.builder.parse(child);
					abilita = (Node) path.evaluate("questionario/@abilita", doc, XPathConstants.NODE);
				} catch (SAXException | IOException | XPathExpressionException e) {
					e.printStackTrace();
				}
				checkQuestionnaire.put(split[0], Boolean.parseBoolean(abilita.getNodeValue()));
			}
		}
		
		return checkQuestionnaire;
		
	}
	
	public boolean checkUnckeckQuestionnaire(String username, String nameQuestionnaire){
		
		File questionnaire = new File(this.contextPath + "/xml/" + username + "/" + nameQuestionnaire + ".xml");
		
		Node abilita = null;
		boolean ab = false;
		try {
			this.doc = this.builder.parse(questionnaire);
			abilita = (Node) path.evaluate("questionario/@abilita", doc, XPathConstants.NODE);
			ab = !Boolean.parseBoolean(abilita.getNodeValue());
			abilita.setTextContent(String.valueOf(ab));
		} catch (SAXException | IOException | XPathExpressionException e) {
			e.printStackTrace();
		}
		
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
		StreamResult result = new StreamResult(questionnaire);
		try {
			transformer.transform(source, result);
		} catch (TransformerException e) {
			e.printStackTrace();
		}
		
		return ab;
		
	}
	
	public List<String> modifyQuestionnaire(String username, String fileXml, String nomeFileXml){
		
		List<String> errors = new ArrayList<>();
		boolean validato = true;
		
		InputSource is = new InputSource();
	    is.setCharacterStream(new StringReader(fileXml));
	    try {
			this.doc = this.builder.parse(is);
		} catch (SAXException | IOException e) {
			validato = false;
			errors.add(e.getMessage());
		}
		
	    Node nameQuestionnaire = null;
		try {
			nameQuestionnaire = (Node) path.evaluate("questionario/@nome", doc, XPathConstants.NODE);
		} catch (XPathExpressionException e) {
			validato = false;
			errors.add(e.getMessage());
		}
		
		String nomeQuestionario = nameQuestionnaire.getNodeValue();
	    if(nomeQuestionario.equals("")){
	    	validato = false;
	    	errors.add("Il nome del questionario e' mancante");
	    }
	    
	    File dir = new File(this.contextPath + "/xml/" + username + "/");
		File[] directoryListing = dir.listFiles();
		// SE I FILE SORGENTE E DESTINAZIONE HANNO DIVERSO NOME, CONTROLLARE SE QUELLO DI DESTINAZIONE ESISTE GIA'.
		// IN CASO AFFERMATIVO DARE L'ERRORE CHE ESISTE GIA' UN QUESTIONARIO CON IL NOME NOME_QUESTIONARIO DI DESTINAZIONE
		if(!nomeFileXml.equals(nomeQuestionario))
			for (File child : directoryListing) {
				if(child.getName().equals(nomeQuestionario + ".xml")){
					validato = false;
					errors.add("Esiste gia' un questionario con il nome " + nomeQuestionario);
					break;
				}
			}
		
		NodeList nomiClassiAppartenenza = null;
		NodeList nomiClassiEsclusione = null;
		NodeList codiciDomanda = null;
		NodeList classiAppartenenza = null;
		NodeList classiEsclusione = null;
		NodeList domandeClasseEsclusione = null;
		try {
			nomiClassiAppartenenza = (NodeList) path.evaluate("questionario/classeAppartenenza/@nome", doc, XPathConstants.NODESET);
			nomiClassiEsclusione = (NodeList) path.evaluate("questionario/classeEsclusione/@nome", doc, XPathConstants.NODESET);
			codiciDomanda = (NodeList) path.evaluate("questionario/domanda/@codiceDomanda", doc, XPathConstants.NODESET);
			classiAppartenenza = (NodeList) path.evaluate("questionario/domanda/risposta/classeAppartenenza/@nome", doc, XPathConstants.NODESET);
			classiEsclusione = (NodeList) path.evaluate("questionario/domanda/risposta/classeEsclusione/@nome", doc, XPathConstants.NODESET);
			domandeClasseEsclusione = (NodeList) path.evaluate("questionario/classeEsclusione/domanda/@nome", doc, XPathConstants.NODESET);
		} catch (XPathExpressionException e) {
			validato = false;
			errors.add(e.getMessage());
		}
		
		int indiceClasseAppartenenza = 0;
		for(int i = 0; i < classiAppartenenza.getLength(); i++){
			indiceClasseAppartenenza = 0;
			for(int j = 0; j < nomiClassiAppartenenza.getLength(); j++){
				if(!classiAppartenenza.item(i).getNodeValue().equals(nomiClassiAppartenenza.item(j).getNodeValue()))
					indiceClasseAppartenenza++;
			}
			if(indiceClasseAppartenenza == nomiClassiAppartenenza.getLength()){
				validato = false;
				errors.add("La classe di appartenenza " + classiAppartenenza.item(i).getNodeValue() + " non e' stata dichiarata in precedenza");
			}
		}
		
		int indiceClasseEsclusione = 0;
		for(int i = 0; i < classiEsclusione.getLength(); i++){
			indiceClasseEsclusione = 0;
			for(int j = 0; j < nomiClassiEsclusione.getLength(); j++){
				if(!classiEsclusione.item(i).getNodeValue().equals(nomiClassiEsclusione.item(j).getNodeValue()))
					indiceClasseEsclusione++;
			}
			if(indiceClasseEsclusione == nomiClassiEsclusione.getLength()){
				validato = false;
				errors.add("La classe di esclusione " + classiEsclusione.item(i).getNodeValue() + " non e' stata dichiarata in precedenza");
			}
		}
		
		int indiceDomandaClasseEsclusione = 0;
		for(int i = 0; i < domandeClasseEsclusione.getLength(); i++){
			indiceDomandaClasseEsclusione = 0;
			for(int j = 0; j < codiciDomanda.getLength(); j++){
				if(!domandeClasseEsclusione.item(i).getNodeValue().equals(codiciDomanda.item(j).getNodeValue()))
					indiceDomandaClasseEsclusione++;
			}
			if(indiceDomandaClasseEsclusione == codiciDomanda.getLength()){
				validato = false;
				errors.add("La domanda " + domandeClasseEsclusione.item(i).getNodeValue() + " non e' una domanda dichiarata in precedenza");
			}
		}
		
		StringReader reader = new StringReader(fileXml);
		SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        
		Schema schema = null;
		try {
			schema = schemaFactory.newSchema(new File(this.contextPath + "/xsd/questionario.xsd"));
		} catch (SAXException e) {
			validato = false;
			errors.add(e.getMessage());
		}
        
		Validator validator = schema.newValidator();
		try {
			validator.validate(new StreamSource(reader));
		} catch (SAXException | IOException e1) {
			validato = false;
			errors.add(e1.toString());
		}
		
		if(validato)
			if(!eRaggiungibileNodoTerminale(username,nomeFileXml,nomeQuestionario)){
				validato = false;
				errors.add("La risposta non e' raggiungibile per il questionario " + nomeFileXml);
			}
		
        if(validato){
        	File oldFile = new File(this.contextPath + "/xml/" + username + "/" + nomeFileXml + ".xml");
        	File newFile = new File(this.contextPath + "/xml/" + username + "/" + nomeQuestionario + ".xml");
        	
        	oldFile.renameTo(newFile);
        	
        	BufferedWriter bw;
			try {
				bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(newFile)));
				bw.write(fileXml);
				bw.flush();
				bw.close();
			} catch (IOException e) {
				errors.add(e.getMessage());
			}
			
			errors.add("Il Questionario e' stato modificato con successo");
        }
        
        return errors;
		
	}
	
	public String fileCSS(String nomeFileCSS, String username){
		
		if(nomeFileCSS.equals("Nuovo CSS"))
			return "";

		String css = null;
		
		Path pathCSS = Paths.get(this.contextPath + "/xml/" + username + "/", nomeFileCSS);

		Charset charset = Charset.forName("UTF-8");
	    try {
	      List<String> lines = Files.readAllLines(pathCSS, charset);

	      for (String line : lines) {
	    	  if(css == null)
	    		  css = line;
	    	  else
	    		  css += line + "\n";
	      }
	    } catch (IOException e) {
	      System.out.println(e);
	    }
	    
		return css;
	}
	
	public void selectedCSS(String username, String nameQuestionnaire, String optionSelected, String contextPath){
		
		File questionnaire = new File(this.contextPath + "/xml/" + username + "/" + nameQuestionnaire + ".xml");
		
		Node css = null;
		try {
			this.doc = this.builder.parse(questionnaire);
			css = (Node) path.evaluate("questionario/@css", doc, XPathConstants.NODE);
			css.setTextContent(contextPath + "/xml/" + username + "/" + optionSelected);
		} catch (SAXException | IOException | XPathExpressionException e) {
			e.printStackTrace();
		}
		
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = null;
		try {
			transformer = transformerFactory.newTransformer();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		}
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		
		
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(questionnaire);
		try {
			transformer.transform(source, result);
		} catch (TransformerException e) {
			e.printStackTrace();
		}
		
	}
	
	public String getPathCSS(String username, String nameQuestionnaire){
		
		File questionnaire = new File(this.contextPath + "/xml/" + username + "/" + nameQuestionnaire + ".xml");
		
		Node css = null;
		try {
			this.doc = this.builder.parse(questionnaire);
			css = (Node) path.evaluate("questionario/@css", doc, XPathConstants.NODE);
		} catch (SAXException | IOException | XPathExpressionException e) {
			e.printStackTrace();
		}
		
		return css.getTextContent();
		
	}
	
	public String saveCSS(String username, String nomeFileCSS, String contentCSS){
		
		String risultato = "";
		
		File file = new File(this.contextPath + "/xml/" + username + "/" + nomeFileCSS + ".css");
		
		if(file.exists())
			risultato = "esiste";
		else{
			risultato = "non esiste";
			BufferedWriter bw;
			try {
				bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
				bw.write(contentCSS);
				bw.flush();
				bw.close();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return risultato;
		
	}
	
	public boolean overwriteFileCSS(String username, String nomeFileCSS, String contentCSS){
		
		boolean fileSalvato = true;
		
		File fold=new File(this.contextPath + "/xml/" + username + "/" + nomeFileCSS);
		fold.delete();
		File fnew=new File(this.contextPath + "/xml/" + username + "/" + nomeFileCSS);

		try {
		    FileWriter f2 = new FileWriter(fnew, false);
		    f2.write(contentCSS);
		    f2.close();
		} catch (IOException e) {
			fileSalvato = false;
		}
		
		return fileSalvato;
		
	}
	
	public boolean deleteCSS(String username, String nomeFileCSS){
		System.out.println(username + " " + nomeFileCSS);
		File file = new File(this.contextPath + "/xml/" + username + "/" + nomeFileCSS);
		return file.delete();
		
	}
	
	public boolean removeQuestionnaire(String username, String nomeQuestionario){
		
		File file = new File(this.contextPath + "/xml/" + username + "/" + nomeQuestionario + ".xml");
		File fileDat = new File(this.contextPath + "/xml/" + username + "/" + nomeQuestionario + ".dat");
		return file.delete() && fileDat.delete();
		
	}
	
	private boolean eRaggiungibileNodoTerminale(String username, String oldNameQuestionnaire, String newNameQuestionnaire){
		
		boolean eRaggiungibileNodoTerminale = false;
		
		Map<String, List<String>> classiAppartenenzaOggetto = new HashMap<>();
		Map<String, Integer> pesiCorrenti = new HashMap<>();
		List<String> output = new ArrayList<>();
		Domanda domandaCorrente = new Domanda();
		
//		String contentFileXml = getQuestionnaire(nomeFileXml, username);
//		
//		InputSource is = new InputSource();
//	    is.setCharacterStream(new StringReader(contentFileXml));
//	    try {
//			this.doc = this.builder.parse(is);
//		} catch (SAXException | IOException e) {
//			e.printStackTrace();
//		}
	    
	    // costruzione mappa classiAppartenenzaOggetto
	    NodeList classiAppartenenza = null;
	    NodeList nomiClassiAppartenenza = null;
	    NodeList oggettiDiUnaClasseAppartenenza = null;
	    List<String> oggettiAssociatiAClasseAppartenenza = null;
		try {
			
			classiAppartenenza = (NodeList) path.evaluate("questionario/classeAppartenenza", doc, XPathConstants.NODESET);
			nomiClassiAppartenenza = (NodeList) path.evaluate("questionario/classeAppartenenza/@nome", doc, XPathConstants.NODESET);
			
			for(int i = 0; i < classiAppartenenza.getLength(); i++){
				
				oggettiAssociatiAClasseAppartenenza = new ArrayList<>();
				oggettiDiUnaClasseAppartenenza = (NodeList) path.evaluate("oggetto/@nome", classiAppartenenza.item(i), XPathConstants.NODESET);
				
				for(int j = 0; j < oggettiDiUnaClasseAppartenenza.getLength(); j++){
					
					oggettiAssociatiAClasseAppartenenza.add(oggettiDiUnaClasseAppartenenza.item(j).getNodeValue());
					
				}
				classiAppartenenzaOggetto.put(nomiClassiAppartenenza.item(i).getNodeValue(), oggettiAssociatiAClasseAppartenenza);
			}
			
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
		
		//prendo tutte le domande disponibili dal file xml del questionario e li inserisco nella lista "listaDomandeDisponibili"
		NodeList domandeDisponibili = null;
		Node precedenzaDomandaDisponibile = null;
		Node contenutoDomandaDisponibile = null;
		NodeList risposteDomandaDisponibile = null;
		Node contenutoRispostaDomandaDisponibile = null;
		List<String> contenutoRisposteDomandaDisponibile = new ArrayList<>();
		List<Domanda> listaDomandeDisponibili = new ArrayList<>();
		try{
			
			domandeDisponibili = (NodeList) path.evaluate("questionario/domanda", doc, XPathConstants.NODESET);
			for(int i = 0; i < domandeDisponibili.getLength(); i++){
				String codiceDomandaTmp = domandeDisponibili.item(i).getAttributes().getNamedItem("codiceDomanda").getNodeValue();
				
				contenutoRisposteDomandaDisponibile.clear();
				Domanda d = new Domanda();
				
				precedenzaDomandaDisponibile = (Node) path.evaluate("@precedenza", domandeDisponibili.item(i), XPathConstants.NODE);
				contenutoDomandaDisponibile = (Node) path.evaluate("contenutoDomanda/text()", domandeDisponibili.item(i), XPathConstants.NODE);
				risposteDomandaDisponibile = (NodeList) path.evaluate("risposta", domandeDisponibili.item(i), XPathConstants.NODESET);
				
				d.setCodiceDomanda(codiceDomandaTmp);
				d.setPrecedenza(Integer.parseInt(precedenzaDomandaDisponibile.getNodeValue()));
				d.setContenuto(contenutoDomandaDisponibile.getTextContent());
				
				for(int j = 0; j < risposteDomandaDisponibile.getLength(); j++){
					
					contenutoRispostaDomandaDisponibile = (Node) path.evaluate("contenutoRisposta/text()", risposteDomandaDisponibile.item(j), XPathConstants.NODE);
					contenutoRisposteDomandaDisponibile.add(contenutoRispostaDomandaDisponibile.getTextContent());
					
				}
				
				d.setRisposte(contenutoRisposteDomandaDisponibile);
				
				listaDomandeDisponibili.add(d);
			}
			
		} catch(XPathExpressionException e){
			e.printStackTrace();
		}
		
		//creo una copia di domande disponibili
		List<Domanda> copiaDomandeDisponibili = new ArrayList<>();
		for(Domanda d : listaDomandeDisponibili)
			copiaDomandeDisponibili.add(d);
		
		//costruzione prima domanda
		NodeList precedenze = null;
		NodeList domande = null;
		Node primaDomanda = null;
		Node codiceDomanda = null;
		Node precedenzaDomanda = null;
		Node contenutoDomanda = null;
		NodeList risposte = null;
		Node contenutoRisposta = null;
		List<String> contenutoRisposte = new ArrayList<>();
		HashMap<NodoDomanda,HashMap<NodoDomanda,Double>> distaneMinimeTraOgniCoppiaDiNodi = new HashMap<>();
		GrafoOrientatoPesatoImpl<NodoDomanda> grafoMin = new GrafoOrientatoPesatoImpl<>();
		try{
			
			precedenze = (NodeList) path.evaluate("questionario/domanda/@precedenza", doc, XPathConstants.NODESET);
			Set<Integer> precedenzeSet = new HashSet<>();
			for(int i = 0; i < precedenze.getLength(); i++){
				precedenzeSet.add(Integer.parseInt(precedenze.item(i).getNodeValue()));
			}
			
			int minValueInSet = (Collections.min(precedenzeSet));
			domande = (NodeList) path.evaluate("questionario/domanda[@precedenza=" + "'" + minValueInSet + "']", doc, XPathConstants.NODESET);
			
			HashMap<Integer,GrafoOrientatoPesatoImpl<NodoDomanda>> mappaDeiGrafi = new HashMap<>();
			HashMap<Integer,Node> mappaPrimeDomande = new HashMap<>();
			HashMap<Integer,Double> mappaCostiMinimiRispostaFinale = new HashMap<>();
			for(int i = 0; i < domande.getLength(); i++){
				NodoDomanda nodoIniziale = new NodoDomanda();
				Node codiceDomandaIniziale = (Node) path.evaluate("@codiceDomanda", domande.item(i), XPathConstants.NODE);
				Node precedenzaDomandaIniziale = (Node) path.evaluate("@precedenza", domande.item(i), XPathConstants.NODE);
				Node contenutoDomandaIniziale = (Node) path.evaluate("contenutoDomanda/text()", domande.item(i), XPathConstants.NODE);
				nodoIniziale.setCodiceDomanda(codiceDomandaIniziale.getNodeValue());
				nodoIniziale.setPrecedenza(Integer.parseInt(precedenzaDomandaIniziale.getNodeValue()));
				nodoIniziale.setContenutoDomanda(contenutoDomandaIniziale.getTextContent());
				GrafoOrientatoPesatoImpl<NodoDomanda> grafo = (GrafoOrientatoPesatoImpl<NodoDomanda>) this.creaGrafo(nodoIniziale,copiaDomandeDisponibili,new ArrayList<Domanda>());
				System.out.println(grafo);
				distaneMinimeTraOgniCoppiaDiNodi = Grafi.distaneMinimeTraOgniCoppiaDiNodi(grafo);
				HashMap<NodoDomanda,Double> distanzeMinimeDaUnNodoIniziale = distaneMinimeTraOgniCoppiaDiNodi.get(nodoIniziale);
				List<NodoDomanda> nodiTerminali = new ArrayList<>();
				for(NodoDomanda n : grafo){
					if(n.isNodoTerminale())
						nodiTerminali.add(n);
				}
				List<Double> costiCamminiMinimiDaNodoInizialeANodoTerminale = new ArrayList<>();
				for(NodoDomanda terminale : nodiTerminali)
					costiCamminiMinimiDaNodoInizialeANodoTerminale.add(distanzeMinimeDaUnNodoIniziale.get(terminale));
				
				double costoMinimoRispostaFinale = Collections.min(costiCamminiMinimiDaNodoInizialeANodoTerminale);
				mappaDeiGrafi.put(i, grafo);
				mappaPrimeDomande.put(i, domande.item(i));
				mappaCostiMinimiRispostaFinale.put(i, costoMinimoRispostaFinale);
			}
			
			double min = Double.POSITIVE_INFINITY;
			int indiceMin = -1;
			for(Integer key : mappaCostiMinimiRispostaFinale.keySet()){
				if(mappaCostiMinimiRispostaFinale.get(key) < min){
					min = mappaCostiMinimiRispostaFinale.get(key);
					indiceMin = key;
				}
			}
			
			primaDomanda = mappaPrimeDomande.get(indiceMin);
			grafoMin = mappaDeiGrafi.get(indiceMin);
			
			if(primaDomanda != null){
				eRaggiungibileNodoTerminale = true;
				codiceDomanda = (Node) path.evaluate("@codiceDomanda", primaDomanda, XPathConstants.NODE);
				precedenzaDomanda = (Node) path.evaluate("@precedenza", primaDomanda, XPathConstants.NODE);
				contenutoDomanda = (Node) path.evaluate("contenutoDomanda/text()", primaDomanda, XPathConstants.NODE);
				risposte = (NodeList) path.evaluate("risposta", primaDomanda, XPathConstants.NODESET);
				domandaCorrente.setCodiceDomanda(codiceDomanda.getNodeValue());
				domandaCorrente.setPrecedenza(Integer.parseInt(precedenzaDomanda.getNodeValue()));
				domandaCorrente.setContenuto(contenutoDomanda.getTextContent());
				
				for(int i = 0; i < risposte.getLength(); i++){
					
					contenutoRisposta = (Node) path.evaluate("contenutoRisposta/text()", risposte.item(i), XPathConstants.NODE);
					contenutoRisposte.add(contenutoRisposta.getTextContent());
					
				}
				
				domandaCorrente.setRisposte(contenutoRisposte);
			}
			
		} catch(XPathExpressionException e){
			e.printStackTrace();
		}
		
		if(eRaggiungibileNodoTerminale){
			this.mementoStack.push(this.createMemento(classiAppartenenzaOggetto,pesiCorrenti,output,listaDomandeDisponibili,grafoMin,new ArrayList<Domanda>(),domandaCorrente));
			if(oldNameQuestionnaire != null){
				File oldDat = new File(this.contextPath + "/xml/" + username + "/" + oldNameQuestionnaire + ".dat");
				File newDat = new File(this.contextPath + "/xml/" + username + "/" + newNameQuestionnaire + ".dat");
				
				oldDat.renameTo(newDat);
			}
			try {
		         FileOutputStream fileOut = new FileOutputStream(this.contextPath + "/xml/" + username + "/" + newNameQuestionnaire + ".dat");
		         ObjectOutputStream out = new ObjectOutputStream(fileOut);
		         out.writeObject(mementoStack);
		         out.close();
		         fileOut.close();
		    }catch(IOException e) {
		         e.printStackTrace();
		    }
		}
		
		return eRaggiungibileNodoTerminale;
		
	}
	
	public Domanda initQuestionnaire(String username, String nameQuestionnaire){
		
		try {
	         FileInputStream fileIn = new FileInputStream(this.contextPath + "/xml/" + username + "/" + nameQuestionnaire + ".dat");
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         this.mementoStack = (Stack<Memento>) in.readObject();
	         in.close();
	         fileIn.close();
	    }catch(IOException i) {
		     i.printStackTrace();
	    }catch(ClassNotFoundException c) {
	         c.printStackTrace();
	    }
		
		return this.mementoStack.get(0).getDomandaCorrente();
		
	}
	
	public Domanda execute(String username, String nameQuestioannaire, String contentResponse, Stack<Memento> mementoStack){
		
		this.mementoStack = mementoStack;

		//prossimaDomanda
	    Memento m = this.mementoStack.get(this.mementoStack.size()-1);
	    
	    Domanda domandaCorrente = m.getDomandaCorrente();
	    Map<String, List<String>> classiAppartenenzaOggetto = m.getClassiAppartenenzaOggetto();
	    Map<String, Integer> pesiCorrenti = m.getPesiCorrenti();
	    List<Domanda> listaDomandeDisponibiliCorrente = m.getListaDomandeDisponibili();
	    GrafoOrientatoPesatoImpl<NodoDomanda> grafo = m.getGrafo();
//	    HashMap<NodoDomanda,HashMap<NodoDomanda,Double>> distaneMinimeTraOgniCoppiaDiNodi = m.getDistaneMinimeTraOgniCoppiaDiNodi();
	    List<Domanda> listaDomandeGiaRisposteCorrente = m.getListaDomandeGiaRisposte();
	    
	    Domanda prossimaDomandaCorrente = new Domanda();
	    Map<String, Integer> prossimiPesiCorrenti = new HashMap<>();
	    for (Entry<String, Integer> entry : pesiCorrenti.entrySet()){
	    	prossimiPesiCorrenti.put(entry.getKey(), entry.getValue());
	    }
	    List<Domanda> listaDomandeDisponibili = new ArrayList<>();
	    for(Domanda domanda : listaDomandeDisponibiliCorrente){
	    	listaDomandeDisponibili.add(domanda);
	    }
	    List<Domanda> listaDomandeGiaRisposte = new ArrayList<>();
	    if(listaDomandeGiaRisposteCorrente == null)
	    	listaDomandeGiaRisposteCorrente = new ArrayList<>();
	    listaDomandeGiaRisposteCorrente.add(domandaCorrente);
	    System.out.println(listaDomandeGiaRisposteCorrente);
	    for(Domanda domanda : listaDomandeGiaRisposteCorrente){
	    	listaDomandeGiaRisposte.add(domanda);
	    }
	    List<String> prossimoOutput = new ArrayList<>();
	    
	    String contentFileXml = getQuestionnaire(nameQuestioannaire, username);
		
		InputSource is = new InputSource();
	    is.setCharacterStream(new StringReader(contentFileXml));
	    try {
			this.doc = this.builder.parse(is);
		} catch (SAXException | IOException e) {
			e.printStackTrace();
		}
	    
	    String codiceDomanda = domandaCorrente.getCodiceDomanda();
	    
	    //calcolo pesi
	    Node domanda = null;
	    Node risposta = null;
	    NodeList classiAppartenenza = null;
	    try{
	    	
	    	domanda = (Node) path.evaluate("questionario/domanda[@codiceDomanda=" + "'" + codiceDomanda + "']", doc, XPathConstants.NODE);
	    	risposta = (Node) path.evaluate("risposta[contenutoRisposta/text()=" + "'" + contentResponse + "']", domanda, XPathConstants.NODE);
	    	classiAppartenenza = (NodeList) path.evaluate("classeAppartenenza/@nome", risposta, XPathConstants.NODESET);
	    	
	    } catch(XPathExpressionException e){
	    	e.printStackTrace();
	    }

	    int pesoRisposta = Integer.parseInt(risposta.getAttributes().getNamedItem("peso").getNodeValue());
	    
	    for(int i = 0; i < classiAppartenenza.getLength(); i++){
	    	List<String> oggettiAppatenentiAClasseDiAppartenenza = classiAppartenenzaOggetto.get(classiAppartenenza.item(i).getNodeValue());
	    	for(String oggetto : oggettiAppatenentiAClasseDiAppartenenza){
	    		if(!prossimiPesiCorrenti.containsKey(oggetto)){
	    			prossimiPesiCorrenti.put(oggetto, 0);
	    			int pesoCorrente = prossimiPesiCorrenti.get(oggetto) + pesoRisposta;
	    			prossimiPesiCorrenti.put(oggetto, pesoCorrente);
	    		}
	    		else{
	    			int pesoCorrente = prossimiPesiCorrenti.get(oggetto) + pesoRisposta;
	    			prossimiPesiCorrenti.put(oggetto, pesoCorrente);
	    		}
	    	}
	    	
    	}
	    
	    //costruzione della lista output
	    prossimoOutput.clear();
	    int maxValueInMap=(Collections.max(prossimiPesiCorrenti.values())); 
        for (Entry<String, Integer> entry : prossimiPesiCorrenti.entrySet()){
            if (entry.getValue().equals(maxValueInMap)){
            	prossimoOutput.add(entry.getKey());
            }
        }
	    
	    
	    NodoDomanda nodoDomandaCorrente = new NodoDomanda();
	    for(NodoDomanda n : grafo)
	    	if(n.getCodiceDomanda().equals(codiceDomanda)){
	    		nodoDomandaCorrente = n;
	    		break;
	    	}
	    
	    Iterator<ArcoPesato<NodoDomanda>> iterator = grafo.adiacenti(nodoDomandaCorrente);
	    List<NodoDomanda> nodiAdiacenti = new ArrayList<>();
		while(iterator.hasNext()){
			ArcoPesato<NodoDomanda> arco = iterator.next();
			if(arco.getPeso().getContenutoRisposta().equals(contentResponse))
				nodiAdiacenti.add(arco.getDestinazione());
		}
		
		//tra gli adiacenti rimuovo le dommande gia eseguite
		List<NodoDomanda> copiaNodiAdiacenti = copiaNodiAdiacenti(nodiAdiacenti);
		for(NodoDomanda adiacente : copiaNodiAdiacenti){
			boolean esiste = false;
			for(Domanda d : listaDomandeGiaRisposte){
				if(d.getCodiceDomanda().equals(adiacente.getCodiceDomanda())){
					esiste = true;
					break;
				}	
			}
			if(esiste)
				nodiAdiacenti.remove(adiacente);
		}
		
		//tra gli adiacenti rimuovo le domande con precedenza non minima
		List<NodoDomanda> copiaNodiAdiacenti1 = copiaNodiAdiacenti(nodiAdiacenti);
		Set<Integer> precedenze = new HashSet<>();
		for(NodoDomanda adiacente : copiaNodiAdiacenti1){
			precedenze.add(adiacente.getPrecedenza());
		}
		int minValueInSet = 0;
		try{
			minValueInSet = (Collections.min(precedenze));
		} catch(NoSuchElementException e){
			minValueInSet = -1;
		}
		if(minValueInSet != -1)
			for(NodoDomanda adiacente : copiaNodiAdiacenti1){
				if(adiacente.getPrecedenza() != minValueInSet)
					nodiAdiacenti.remove(adiacente);
			}
		
		//tra gli adiacenti rimuovo quelli per cui la lista di output non cambia
//		List<String> copiaProssimoOutput = copiaProssimoOutput(prossimoOutput);
//		Map<String, Integer> copiaProssimiPesiCorrenti = copiaProssimiPesiCorrenti(prossimiPesiCorrenti);
//		List<NodoDomanda> copiaNodiAdiacenti2 = copiaNodiAdiacenti(nodiAdiacenti);
//		Set<NodoDomanda> visitato = new HashSet<>();
//		for(NodoDomanda adiacente : copiaNodiAdiacenti2){
//			rimuoviSeListaDiOutPutNonCambia(grafo,visitato,copiaProssimoOutput,copiaProssimiPesiCorrenti,classiAppartenenzaOggetto,adiacente);
//			if(this.hannoLoStessoContenuto)
//				nodiAdiacenti.remove(adiacente);
//			this.hannoLoStessoContenuto = false;
//		}
		
		//ogni volta che dai una risposta ad una domanda dovresti modificare il grafo perché il nodo adiacente che prima aveva almeno un collegamento
		//ora protrebbe non più averne
		
		/*
		 * Per ogni nodo adiacente costruisco un grafo a partire dall'i-esimo nodo adiacente facendo attenzione alle domande già eseguite
		 * e ne calcolo il cammino minimo dal nodo adiacente al terminale;
		 * Successivamente prendo il nodo adiacente che ha il cammino minimo più basso.
		 * RENDERE UNICA LA FUNZIONE DI SCELTA DELLA PRIMA DOMANDA CON QUELLA DELLA SCELTA DELLE DOMANDE INTERMEDIE, SE POSSIBILE.
		 * REINSERIRE, SE POSSIBILE, LA FUNZIONE CHE CONTROLLA CHE LA LISTA DI OUTPUT NON CAMBIA SINO AL NODO TERMINALE.
		 */
		
		
		
		NodoDomanda nodoProssimaDomandaCorrente = new NodoDomanda();
		GrafoOrientatoPesatoImpl<NodoDomanda> grafoMin = new GrafoOrientatoPesatoImpl<>();
		if(!nodiAdiacenti.isEmpty()){
//			if(nodiAdiacenti.size() > 1){
			System.err.println(nodiAdiacenti);
			HashMap<Integer,Double> mappaCostiMinimiRispostaFinale = new HashMap<>();
			HashMap<Integer,NodoDomanda> mappaDomandeAdiacenti = new HashMap<>();
			HashMap<NodoDomanda,HashMap<NodoDomanda,Double>> distaneMinimeTraOgniCoppiaDiNodi = new HashMap<>();
			HashMap<Integer,GrafoOrientatoPesatoImpl<NodoDomanda>> mappaDeiGrafi = new HashMap<>();
			for(int i = 0; i < nodiAdiacenti.size(); i++){
				NodoDomanda nodoAdiacente = nodiAdiacenti.get(i);
				GrafoOrientatoPesatoImpl<NodoDomanda> grafoTmp = (GrafoOrientatoPesatoImpl<NodoDomanda>) this.creaGrafo(nodoAdiacente,listaDomandeDisponibili,listaDomandeGiaRisposte);
				if(grafoTmp.numNodi() != 0){
					System.out.println(grafoTmp);
					distaneMinimeTraOgniCoppiaDiNodi = Grafi.distaneMinimeTraOgniCoppiaDiNodi(grafoTmp);
					HashMap<NodoDomanda,Double> distanzeMinimeDaUnNodoAdiacente = distaneMinimeTraOgniCoppiaDiNodi.get(nodoAdiacente);
					List<NodoDomanda> nodiTerminali = new ArrayList<>();
					for(NodoDomanda n : grafoTmp){
						if(n.isNodoTerminale())
							nodiTerminali.add(n);
					}
					List<Double> costiCamminiMinimiDaNodoAdiacenteANodoTerminale = new ArrayList<>();
					for(NodoDomanda terminale : nodiTerminali)
						costiCamminiMinimiDaNodoAdiacenteANodoTerminale.add(distanzeMinimeDaUnNodoAdiacente.get(terminale));
					
					double costoMinimoRispostaFinale = Collections.min(costiCamminiMinimiDaNodoAdiacenteANodoTerminale);
					mappaDeiGrafi.put(i, grafoTmp);
					mappaDomandeAdiacenti.put(i, nodoAdiacente);
					mappaCostiMinimiRispostaFinale.put(i, costoMinimoRispostaFinale);
				}
			}
			double min = Double.POSITIVE_INFINITY;
			int indiceMin = -1;
			for(Integer key : mappaCostiMinimiRispostaFinale.keySet()){
				if(mappaCostiMinimiRispostaFinale.get(key) < min){
					min = mappaCostiMinimiRispostaFinale.get(key);
					indiceMin = key;
				}
			}
			if(indiceMin != -1){
				nodoProssimaDomandaCorrente = mappaDomandeAdiacenti.get(indiceMin);
				grafoMin = mappaDeiGrafi.get(indiceMin);
				System.err.println("SCELTA DEL NODO ADIACENTE: " + nodoProssimaDomandaCorrente.getCodiceDomanda());
			}
		}
		
	    try {
	    	if(nodoProssimaDomandaCorrente.getCodiceDomanda() != null){
				Node nodeProssimaDomandaCorrente = (Node) path.evaluate("questionario/domanda[@codiceDomanda=" + "'" + nodoProssimaDomandaCorrente.getCodiceDomanda() + "']", doc, XPathConstants.NODE);
				if(nodeProssimaDomandaCorrente != null){
					Node codiceProssimaDomandaCorrente = (Node) path.evaluate("@codiceDomanda", nodeProssimaDomandaCorrente, XPathConstants.NODE);
					Node precedenzaProssimaDomandaCorrente = (Node) path.evaluate("@precedenza", nodeProssimaDomandaCorrente, XPathConstants.NODE);
					Node contenutoProssimaDomandaCorrente = (Node) path.evaluate("contenutoDomanda/text()", nodeProssimaDomandaCorrente, XPathConstants.NODE);
					NodeList risposteProssimaDomandaCorrente = (NodeList) path.evaluate("risposta", nodeProssimaDomandaCorrente, XPathConstants.NODESET);
					
					prossimaDomandaCorrente.setCodiceDomanda(codiceProssimaDomandaCorrente.getNodeValue());
					prossimaDomandaCorrente.setPrecedenza(Integer.parseInt(precedenzaProssimaDomandaCorrente.getNodeValue()));
					prossimaDomandaCorrente.setContenuto(contenutoProssimaDomandaCorrente.getTextContent());
					
					Node contenutoRispostaDomandaCorrente = null;
					List<String> contenutoRisposteDomandaCorrente = new ArrayList<>();
					for(int i = 0; i < risposteProssimaDomandaCorrente.getLength(); i++){
						
						contenutoRispostaDomandaCorrente = (Node) path.evaluate("contenutoRisposta/text()", risposteProssimaDomandaCorrente.item(i), XPathConstants.NODE);
						contenutoRisposteDomandaCorrente.add(contenutoRispostaDomandaCorrente.getTextContent());
						
					}
					
					prossimaDomandaCorrente.setRisposte(contenutoRisposteDomandaCorrente);
				}
	    	}
		} catch (XPathExpressionException e1) {
			e1.printStackTrace();
		}
	    
//	    listaDomandeGiaRisposte.add(domandaCorrente);
	    boolean prossimaDomandaCorrenteGiaEsistente = false;
	    for(Memento memento : this.mementoStack){
	    	if(memento.getDomandaCorrente().equals(prossimaDomandaCorrente)){
	    		prossimaDomandaCorrenteGiaEsistente = true;
	    		break;
	    	}
	    }
	    if(!prossimaDomandaCorrenteGiaEsistente)
	    	this.mementoStack.push(createMemento(classiAppartenenzaOggetto,prossimiPesiCorrenti,prossimoOutput,listaDomandeDisponibili,grafoMin,listaDomandeGiaRisposte,prossimaDomandaCorrente));
		
	    return prossimaDomandaCorrente;
	}
	
//	private void rimuoviSeListaDiOutPutNonCambia(GrafoOrientatoPesatoImpl<NodoDomanda> grafo,Set<NodoDomanda> visitato,List<String> prossimoOutput, Map<String, Integer> prossimiPesiCorrenti, Map<String, List<String>> classiAppartenenzaOggetto, NodoDomanda adiacente) {
//		//calcolo pesi
//	    Node domanda = null;
//	    Node risposta = null;
//	    NodeList classiAppartenenza = null;
//	    String codiceDomanda = adiacente.getCodiceDomanda();
//	    try{
//	    	
//	    	domanda = (Node) path.evaluate("questionario/domanda[@codiceDomanda=" + "'" + codiceDomanda + "']", doc, XPathConstants.NODE);
//	    	
//	    	visitato.add(adiacente);
//	    	Iterator<ArcoPesato<NodoDomanda>> iterator = grafo.adiacenti(adiacente);
//	    	while(iterator.hasNext()){
//	    		ArcoPesato<NodoDomanda> arco = iterator.next();
//	    		String contentResponse = arco.getPeso().getContenutoRisposta();
//	    		risposta = (Node) path.evaluate("risposta[contenutoRisposta/text()=" + "'" + contentResponse + "']", domanda, XPathConstants.NODE);
//		    	classiAppartenenza = (NodeList) path.evaluate("classeAppartenenza/@nome", risposta, XPathConstants.NODESET);
//		    	
//		    	int pesoRisposta = Integer.parseInt(risposta.getAttributes().getNamedItem("peso").getNodeValue());
//			    
//			    for(int i = 0; i < classiAppartenenza.getLength(); i++){
//			    	List<String> oggettiAppatenentiAClasseDiAppartenenza = classiAppartenenzaOggetto.get(classiAppartenenza.item(i).getNodeValue());
//			    	for(String oggetto : oggettiAppatenentiAClasseDiAppartenenza){
//			    		if(!prossimiPesiCorrenti.containsKey(oggetto)){
//			    			prossimiPesiCorrenti.put(oggetto, 0);
//			    			int pesoCorrente = prossimiPesiCorrenti.get(oggetto) + pesoRisposta;
//			    			prossimiPesiCorrenti.put(oggetto, pesoCorrente);
//			    		}
//			    		else{
//			    			int pesoCorrente = prossimiPesiCorrenti.get(oggetto) + pesoRisposta;
//			    			prossimiPesiCorrenti.put(oggetto, pesoCorrente);
//			    		}
//			    	}
//			    	
//		    	}
//			    
//			    //costruzione della lista output
//			    List<String> tmpProssimoOutput = new ArrayList<>();
//			    tmpProssimoOutput.clear();
//			    int maxValueInMap=(Collections.max(prossimiPesiCorrenti.values())); 
//		        for (Entry<String, Integer> entry : prossimiPesiCorrenti.entrySet()){
//		            if (entry.getValue().equals(maxValueInMap)){
//		            	tmpProssimoOutput.add(entry.getKey());
//		            }
//		        }
//		        
//		        if(!prossimoOutput.equals(tmpProssimoOutput))
//		        	this.hannoLoStessoContenuto = false;
//		        	
//		        NodoDomanda nodoAdiacente = arco.getDestinazione();
//		        if( !visitato.contains(nodoAdiacente) )
//		        	rimuoviSeListaDiOutPutNonCambia(grafo, visitato, prossimoOutput, prossimiPesiCorrenti, classiAppartenenzaOggetto, arco.getDestinazione());
//		        
//	    	}
//	    	
//	    } catch(XPathExpressionException e){
//	    	e.printStackTrace();
//	    }
//	}
	
	private List<NodoDomanda> copiaNodiAdiacenti(List<NodoDomanda> nodiAdiacenti){
		List<NodoDomanda> copiaNodiAdiacenti = new ArrayList<>();
		for(NodoDomanda nodo : nodiAdiacenti)
			copiaNodiAdiacenti.add(nodo);
		return copiaNodiAdiacenti;
	}
	
//	private List<String> copiaProssimoOutput(List<String> prossimoOutput){
//		List<String> copiaProssimoOutput = new ArrayList<>();
//		for(String oggetto : prossimoOutput)
//			copiaProssimoOutput.add(oggetto);
//		return copiaProssimoOutput;
//	}
//	
//	private  Map<String, Integer> copiaProssimiPesiCorrenti( Map<String, Integer> prossimiPesiCorrenti){
//		Map<String, Integer> copiaProssimiPesiCorrenti = new HashMap<>();
//		for (Entry<String, Integer> entry : prossimiPesiCorrenti.entrySet()){
//			copiaProssimiPesiCorrenti.put(entry.getKey(), entry.getValue());
//	    }
//		return copiaProssimiPesiCorrenti;
//	}

	private Memento createMemento(Map<String, List<String>> classiAppartenenzaOggetto, Map<String, Integer> pesiCorrenti, List<String> output, List<Domanda> listaDomandeDisponibili, GrafoOrientatoPesatoImpl<NodoDomanda> grafo, List<Domanda> listaDomandeGiaRisposte, Domanda domandaCorrente){
		
		Memento memento = new Memento();
		memento.setClassiAppartenenzaOggetto(classiAppartenenzaOggetto);
		memento.setPesiCorrenti(pesiCorrenti);
		memento.setOutput(output);
		memento.setListaDomandeDisponibili(listaDomandeDisponibili);
		memento.setGrafo(grafo);
		memento.setListaDomandeGiaRisposte(listaDomandeGiaRisposte);
		memento.setDomandaCorrente(domandaCorrente);
		
		return memento;
		
	}
	
	public Domanda restoreState(Stack<Memento> mementoStack, String codiceDomandaStoricoDomande){
		
		this.mementoStack = mementoStack;
		
		int count = 1;
		for(int i = 0; i < this.mementoStack.size(); i++){
			Domanda domandaCorrente = this.mementoStack.get(i).getDomandaCorrente();
			if(domandaCorrente.getCodiceDomanda().equals(codiceDomandaStoricoDomande))
				break;
			count++;
		}
		
		int size = this.mementoStack.size() - count;
		
		for(int i = 0; i < size; i++){
			this.mementoStack.pop();
		}
		
		return this.mementoStack.get(this.mementoStack.size() - 1).getDomandaCorrente();
		
	}
	
	private Grafo<NodoDomanda> creaGrafo(NodoDomanda nodoIniziale, List<Domanda> listaDomandeDisponibili, List<Domanda> listaDomandeGiaEseguite) {

		GrafoOrientatoPesatoImpl<NodoDomanda> grafo = new GrafoOrientatoPesatoImpl<>();
		
		if(!nodoIniziale.isNodoTerminale()){
			Queue<NodoDomanda> coda = new LinkedList<>();
			NodoDomanda nodoTerminale = new NodoDomanda();
			nodoTerminale.setCodiceDomanda("");
			nodoTerminale.setContenutoDomanda("");
			nodoTerminale.setPrecedenza(-1);
			nodoTerminale.setNodoTerminale(true);
			grafo.insNodo(nodoTerminale);
			coda.offer(nodoIniziale);
			grafo.insNodo(nodoIniziale);
	
			List<Domanda> copiaListaDomandeDisponibili = new ArrayList<>();
			
			while(!coda.isEmpty()){
				NodoDomanda nodoDomanda = coda.poll();
				
				String codiceDomanda = nodoDomanda.getCodiceDomanda();
				
				Node domanda = null;
			    NodeList risposte = null;
			    Node classeEsclusioneRisposta = null;
			    Node classeEsclusione = null;
			    NodeList domandeEscluse = null;
			    try{
			    	
			    	domanda = (Node) path.evaluate("questionario/domanda[@codiceDomanda=" + "'" + codiceDomanda + "']", doc, XPathConstants.NODE);
			    	risposte = (NodeList) path.evaluate("risposta", domanda, XPathConstants.NODESET);
	
			    	for(int i = 0; i < risposte.getLength(); i++){
			    		
			    		domandeEscluse = null;
			    		copiaListaDomandeDisponibili.clear();
			    		for(Domanda d : listaDomandeDisponibili){
			    			copiaListaDomandeDisponibili.add(d);
			    		}
			    			
						Node contenutoRisposta = (Node) path.evaluate("contenutoRisposta/text()", risposte.item(i), XPathConstants.NODE);
						Node pesoRisposta = (Node) path.evaluate("@peso", risposte.item(i), XPathConstants.NODE);
			    		
			    		classeEsclusioneRisposta = (Node) path.evaluate("classeEsclusione", risposte.item(i), XPathConstants.NODE);
			    		
				    	if(classeEsclusioneRisposta != null){
					    	classeEsclusione = (Node) path.evaluate("questionario/classeEsclusione[@nome=" + "'" + classeEsclusioneRisposta.getAttributes().getNamedItem("nome").getNodeValue() + "']", doc, XPathConstants.NODE);
					    	domandeEscluse = (NodeList) path.evaluate("domanda", classeEsclusione, XPathConstants.NODESET);
				    	}
				    	
				    	if(domandeEscluse != null)
						    for(int x = 0; x < domandeEscluse.getLength(); x++){
						    	for(int y = 0; y < copiaListaDomandeDisponibili.size(); y++){
						    		String domanda1 = copiaListaDomandeDisponibili.get(y).getCodiceDomanda();
						    		if(domandeEscluse.item(x).getAttributes().getNamedItem("nome").getNodeValue().equals(domanda1))
						    			copiaListaDomandeDisponibili.remove(y);
						    	}
						    }
				    	
				    	for(int x = 0; x < listaDomandeGiaEseguite.size(); x++){
				    		for(int y = 0; y < copiaListaDomandeDisponibili.size(); y++){
				    			String domanda1 = copiaListaDomandeDisponibili.get(y).getCodiceDomanda();
				    			if(listaDomandeGiaEseguite.get(x).getCodiceDomanda().equals(domanda1))
				    				copiaListaDomandeDisponibili.remove(y);
				    		}
				    	}
	
				    	if(copiaListaDomandeDisponibili.isEmpty())
				    		grafo.insArco(nodoDomanda, nodoTerminale, new Peso(contenutoRisposta.getTextContent(), Integer.parseInt(pesoRisposta.getTextContent()),0));
				    	else{
			    			if(copiaListaDomandeDisponibili != null){
			    				for(Domanda d : copiaListaDomandeDisponibili){
			    					boolean esiste = false;
			    					NodoDomanda nodoDomandaConPrecedenzaMinValueInSet = new NodoDomanda();
			    					nodoDomandaConPrecedenzaMinValueInSet.setCodiceDomanda(d.getCodiceDomanda());
			    					nodoDomandaConPrecedenzaMinValueInSet.setPrecedenza(d.getPrecedenza());
			    					nodoDomandaConPrecedenzaMinValueInSet.setContenutoDomanda(d.getContenuto());
			    					Iterator<NodoDomanda> iterator = grafo.iterator();
			    					while(iterator.hasNext()){
			    						NodoDomanda n = iterator.next();
			    						if(n.equals(nodoDomandaConPrecedenzaMinValueInSet)){
			    							esiste = true;
			    							break;
			    						}
			    					}
			    					if(!esiste){
			    						coda.offer(nodoDomandaConPrecedenzaMinValueInSet);
			    						grafo.insNodo(nodoDomandaConPrecedenzaMinValueInSet);
			    					}
			    					grafo.insArco(nodoDomanda, nodoDomandaConPrecedenzaMinValueInSet, new Peso(contenutoRisposta.getTextContent(),Integer.parseInt(pesoRisposta.getTextContent()),1));
			    				}
			    			}
				    	    
				    	}
				    	
			    	}
			    	
			    } catch(XPathExpressionException e){
			    	e.printStackTrace();
			    }
			}
			
			Iterator<NodoDomanda> iterator = grafo.iterator();
			while(iterator.hasNext()){
				NodoDomanda n = iterator.next();
				if(n.isNodoTerminale()){
					System.out.println("CODICE DOMANDA TERMINALE: " + n.getCodiceDomanda() + " " + n.isNodoTerminale());
				}
			}
		}
			
		return grafo;
	
	}
	
	
	public String toString(){
		
		StringBuffer sb = new StringBuffer();
		sb.append("Pesi correnti: ");
		
		Memento memento = this.mementoStack.get(this.mementoStack.size()-1);
		Map<String, Integer> pesiCorrenti = memento.getPesiCorrenti();
		List<String> output = memento.getOutput();
		
		for (Entry<String, Integer> entry : pesiCorrenti.entrySet()){
			sb.append(" Oggetto: " + entry.getKey() + " peso: " + entry.getValue());
		}
		
		for(String oggetto : output){
			sb.append(" Output: " + oggetto);
		}
		
		return sb.toString();
		
	}
	
	public Stack<Memento> getMementoStack() {
		return mementoStack;
	}

	public void setMementoStack(Stack<Memento> mementoStack) {
		this.mementoStack = mementoStack;
	}
}