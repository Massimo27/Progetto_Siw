package it.uniroma3.siw.silphspa.silphspa.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.silphspa.silphspa.model.Funzionario;

public interface FunzionarioRepository extends CrudRepository<Funzionario, Long> {
	public Funzionario findByNickname(String nickname);
}
