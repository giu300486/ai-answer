package service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import grafi.GrafoOrientatoPesatoImpl;
import model.Domanda;

public class Memento implements Serializable{
	
	private static final long serialVersionUID = -6007849571312128881L;

	private Map<String, List<String>> classiAppartenenzaOggetto = new HashMap<>();
	private Map<String, Integer> pesiCorrenti = new HashMap<>();
	private List<String> output = new ArrayList<>();
	private List<Domanda> listaDomandeDisponibili = new ArrayList<>();
	private GrafoOrientatoPesatoImpl<NodoDomanda> grafo;
	private List<Domanda> listaDomandeGiaRisposte = new ArrayList<>();
	private Domanda domandaCorrente = new Domanda();
	
	public Map<String, List<String>> getClassiAppartenenzaOggetto() {
		return classiAppartenenzaOggetto;
	}
	public void setClassiAppartenenzaOggetto(Map<String, List<String>> classiAppartenenzaOggetto) {
		this.classiAppartenenzaOggetto = classiAppartenenzaOggetto;
	}
	public Map<String, Integer> getPesiCorrenti() {
		return pesiCorrenti;
	}
	public void setPesiCorrenti(Map<String, Integer> pesiCorrenti) {
		this.pesiCorrenti = pesiCorrenti;
	}
	public List<String> getOutput() {
		return output;
	}
	public void setOutput(List<String> output) {
		this.output = output;
	}
	public List<Domanda> getListaDomandeDisponibili() {
		return listaDomandeDisponibili;
	}
	public void setListaDomandeDisponibili(List<Domanda> listaDomandeDisponibili) {
		this.listaDomandeDisponibili = listaDomandeDisponibili;
	}
	public Domanda getDomandaCorrente() {
		return domandaCorrente;
	}
	public void setDomandaCorrente(Domanda domandaCorrente) {
		this.domandaCorrente = domandaCorrente;
	}
	public GrafoOrientatoPesatoImpl<NodoDomanda> getGrafo() {
		return grafo;
	}
	public void setGrafo(GrafoOrientatoPesatoImpl<NodoDomanda> grafo) {
		this.grafo = grafo;
	}
	public List<Domanda> getListaDomandeGiaRisposte() {
		return listaDomandeGiaRisposte;
	}
	public void setListaDomandeGiaRisposte(List<Domanda> listaDomandeGiaRisposte) {
		this.listaDomandeGiaRisposte = listaDomandeGiaRisposte;
	}
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("classiAppartenenzaOggetto: \n");
		sb.append(this.classiAppartenenzaOggetto.toString() + "\n\n");
		sb.append("pesiCorrenti: \n");
		sb.append(this.pesiCorrenti.toString() + "\n\n");
		sb.append("output: \n");
		sb.append(this.output.toString() + "\n\n");
		sb.append("listaDomandeDisponibili: \n");
		sb.append(this.listaDomandeDisponibili.toString() + "\n\n");
		sb.append("grafo: \n");
		sb.append(grafo.toString() + "\n");
		sb.append("listaDomandeGiaRisposte: \n");
		sb.append(this.listaDomandeGiaRisposte.toString() + "\n\n");
		sb.append("domandaCorrente: \n");
		sb.append(this.domandaCorrente.toString());
		return sb.toString();
	}
}
