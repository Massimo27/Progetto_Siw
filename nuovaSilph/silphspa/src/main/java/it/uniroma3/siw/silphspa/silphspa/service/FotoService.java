package it.uniroma3.siw.silphspa.silphspa.service;

import java.net.URL;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.silphspa.silphspa.model.Foto;
import it.uniroma3.siw.silphspa.silphspa.repository.FotoRepository;

@Service
public class FotoService {
	@Autowired
	private FotoRepository fotoRepository;
	
	@Transactional
	public void inserisci (Foto foto) {
		this.fotoRepository.save(foto);
	}
	@Transactional
	public List<Foto> tutte(){
		return (List<Foto>) this.fotoRepository.findAll();
	}

	@Transactional
	public Foto trova(URL indirizzo) {
		return this.fotoRepository.findByIndirizzo(indirizzo);
	}
	
	@Transactional
	public List<Foto> fotoPerAutore(String autore){
		return this.fotoRepository.findByAutore(autore);
	}
	
	@Transactional
	public List<Foto> fotoPerAlbum(String album){
		return this.fotoRepository.findByAlbum(album);
	}
	@Transactional
	public Foto fotoPerId(Long id) {
		return this.fotoRepository.findById(id).get();
	}
	@Transactional 
	public List<Foto> fotoAggiunte() {
		return this.fotoRepository.findByAggiunta(true);
	}
}
