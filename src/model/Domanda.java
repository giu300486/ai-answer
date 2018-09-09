package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Domanda implements Serializable{

	private static final long serialVersionUID = -2456169719148835514L;

	private String codiceDomanda;
	private int precedenza;
	private String contenuto;
	private List<String> risposte;
	
	public Domanda() {}

	public String getCodiceDomanda() {
		return codiceDomanda;
	}

	public void setCodiceDomanda(String codiceDomanda) {
		this.codiceDomanda = codiceDomanda;
	}
	
	public int getPrecedenza() {
		return precedenza;
	}

	public void setPrecedenza(int precedenza) {
		this.precedenza = precedenza;
	}
	
	public String getContenuto() {
		return contenuto;
	}

	public void setContenuto(String contenuto) {
		this.contenuto = contenuto;
	}

	public List<String> getRisposte() {
		return risposte;
	}

	public void setRisposte(List<String> risposte) {
		if(this.risposte != null)
			this.risposte.clear();
		else
			this.risposte = new ArrayList<>();
		for(String risposta : risposte){
			this.risposte.add(risposta);
		}
	}
	
	@Override
	public boolean equals(Object obj) {
		
		Domanda d = (Domanda) obj;
		
		return this.codiceDomanda.equals(d.codiceDomanda);
		
	}
	
	@Override
	public String toString() {
		
		StringBuffer sb = new StringBuffer();
		
		sb.append("Codice domanda: " + codiceDomanda + "\n");
		sb.append("Precedenza domanda: " + precedenza + "\n");
		sb.append("Contenuto domanda: " + contenuto + "\n");
		sb.append("Risposte: \n");
		
		for(String risposta : risposte){
			sb.append(risposta + "\n");
		}
		
		return sb.toString();
		
	}
}
