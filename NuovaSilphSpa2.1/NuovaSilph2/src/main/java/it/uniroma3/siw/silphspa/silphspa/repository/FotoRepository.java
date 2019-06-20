package it.uniroma3.siw.silphspa.silphspa.repository;

import java.net.URL;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.silphspa.silphspa.model.Foto;

public interface FotoRepository extends CrudRepository<Foto, Long>{
	public List<Foto> findByAutore(String Autore);
	public Foto findByIndirizzo (URL indirizzo);
	public List<Foto> findByAlbum(String album);
	public List<Foto> findByAggiunta(Boolean aggiunta);
}
