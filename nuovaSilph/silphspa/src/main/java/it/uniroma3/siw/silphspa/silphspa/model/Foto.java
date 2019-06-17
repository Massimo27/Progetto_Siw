package it.uniroma3.siw.silphspa.silphspa.model;

import java.net.URL;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Foto {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String autore;
	private URL indirizzo;
	private String descrizione;
	private String album;
	private Boolean aggiunta;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAutore() {
		return autore;
	}
	public void setAutore(String autore) {
		this.autore = autore;
	}
	public URL getIndirizzo() {
		return indirizzo;
	}
	public void setIndirizzo(URL indirizzo) {
		this.indirizzo = indirizzo;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public String getAlbum() {
		return album;
	}
	public void setAlbum(String album) {
		this.album = album;
	}
	public Boolean getAggiunta() {
		return aggiunta;
	}
	public void setAggiunta(Boolean aggiunta) {
		this.aggiunta = aggiunta;
	}	
}
