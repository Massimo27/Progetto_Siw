package it.uniroma3.siw.silphspa.silphspa.model;

import java.util.ArrayList;
import java.util.List;

public class Carrello {

	private List<String> listaFoto;
	private String aggiungere;
	
	public Carrello() {
		listaFoto = new ArrayList<String>();
	}
	
	public List<String> getListaFoto() {
		return listaFoto;
	}

	public void setListaFoto(List<String> listaFoto) {
		this.listaFoto = listaFoto;
	}

	public String getAggiungere() {
		return aggiungere;
	}

	public void setAggiungere(String aggiungere) {
		this.listaFoto.add(aggiungere);
		this.aggiungere = aggiungere;
	}
	
	

}
