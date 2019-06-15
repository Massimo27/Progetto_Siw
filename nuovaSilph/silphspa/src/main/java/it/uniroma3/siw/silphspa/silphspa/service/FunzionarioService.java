package it.uniroma3.siw.silphspa.silphspa.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.silphspa.silphspa.model.Funzionario;
import it.uniroma3.siw.silphspa.silphspa.repository.FunzionarioRepository;

@Service
public class FunzionarioService {

	@Autowired
	private FunzionarioRepository funzionarioRepository;
	
	//??? vedi se toglierla non la utilizzi???
	@Transactional
	public Funzionario inserisci(Funzionario funzionario) {
		return funzionarioRepository.save(funzionario);
	}
	public List<Funzionario> tutti(){
		return (List<Funzionario>)funzionarioRepository.findAll();
	}
	public Funzionario funzionarioPerId(Long id) {
		return this.funzionarioRepository.findById(id).get();
	}
	public Funzionario funzionarioPerNickname(String user) {
		return (Funzionario) funzionarioRepository.findByuserName(user);
	}
}

