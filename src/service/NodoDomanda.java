package service;

import java.io.Serializable;

public class NodoDomanda implements Serializable{

	private static final long serialVersionUID = -5289824983758938144L;

	private String codiceDomanda;
	private int precedenza;
	private String contenutoDomanda;
	private boolean nodoTerminale;
	
	public NodoDomanda(){}

	public String getCodiceDomanda() {
		return codiceDomanda;
	}

	public void setCodiceDomanda(String codiceDomanda) {
		this.codiceDomanda = codiceDomanda;
	}

	public boolean isNodoTerminale() {
		return nodoTerminale;
	}

	public void setNodoTerminale(boolean nodoTerminale) {
		this.nodoTerminale = nodoTerminale;
	}

	public int getPrecedenza() {
		return precedenza;
	}

	public void setPrecedenza(int precedenza) {
		this.precedenza = precedenza;
	}
	
	public String getContenutoDomanda() {
		return contenutoDomanda;
	}

	public void setContenutoDomanda(String contenutoDomanda) {
		this.contenutoDomanda = contenutoDomanda;
	}
	
	@Override
	public boolean equals(Object o) {
		if( o==null || !(o instanceof NodoDomanda) ) return false;
		if( o==this ) return true;
		NodoDomanda nodoDomanda = (NodoDomanda) o;
		return this.codiceDomanda.equals(nodoDomanda.codiceDomanda);
	}
	
	@Override
	public int hashCode() {
		return this.codiceDomanda.hashCode();
	}
	
	@Override
	public String toString() {
		return this.codiceDomanda;
	}

}
